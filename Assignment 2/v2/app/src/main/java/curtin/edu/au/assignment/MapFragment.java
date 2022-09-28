package curtin.edu.au.assignment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.Activity.RESULT_OK;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Fragment that displays game map and handles all interaction with it.
 * Primarily made in MAD Practical 03 and modified to meet assignment requirements
 */

public class MapFragment extends Fragment
{
    // =========================
    // Classfields
    private GameData map;
    private GridAdapter adapter;
    private SelectorFragment selectorFrag;
    private TaskbarFragment taskbarFrag;
    private static final int REQUEST_DETAIL = 0;


    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        map = GameData.get();
        adapter = new GridAdapter();
    }


    @Override
    // Defining Fragments
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_map, ui, false);
        // Obtain the RecyclerView UI element
        RecyclerView recycView = (RecyclerView) view.findViewById(R.id.mapRecyclerView);
        // Specify how it should be laid out
        recycView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                GameData.HEIGHT,
                GridLayoutManager.HORIZONTAL,
                false));
        // Have your data ready
        map = GameData.get();
        // Create your adapter
        adapter = new GridAdapter();
        // Hook it up
        recycView.setAdapter(adapter);
        return view;
    }

    // ========== ADAPTER ===============
    // Hasn't been changed since MAD Practical 03
    private class GridAdapter extends RecyclerView.Adapter<GridViewHolder>
    {
        @Override
        public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new GridViewHolder(li, parent);
        } // Done.


        @Override
        public void onBindViewHolder(GridViewHolder vh, int position)
        {
            int row = position % GameData.HEIGHT;
            int col = position / GameData.HEIGHT;
            vh.bind(map.get(row, col)); // Binds ONE map element at a time.
        }

        @Override
        public int getItemCount()
        {
                return GameData.WIDTH * GameData.HEIGHT;
        }
    }

    // ========== VIEW HOLDER ===============
    private class GridViewHolder extends RecyclerView.ViewHolder
    {
        // Declaration of variables
        private MapElement mapElement;

        private ImageView bottomLeft;
        private ImageView bottomRight;
        private ImageView topLeft;
        private ImageView topRight;
        private ImageView structureImg;


        public GridViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.grid_cell, parent, false));
            int size = parent.getMeasuredHeight() / GameData.HEIGHT + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

            // Initialise ImageViews
            bottomLeft = (ImageView) itemView.findViewById(R.id.bottomLeftImage);
            bottomRight = (ImageView) itemView.findViewById(R.id.bottomRightImage);
            topLeft = (ImageView) itemView.findViewById(R.id.topLeftImage);
            topRight = (ImageView) itemView.findViewById(R.id.topRightImage);
            structureImg = (ImageView) itemView.findViewById(R.id.structureImage);

            structureImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int cost = 0;

                    if(selectorFrag.getSelected() != null) // BASED ON THE STRUCTURE, APPLY THE COST.
                    {
                        if (selectorFrag.getSelected() instanceof Commercial)
                        {
                            cost = map.getSettings().getCommBuildingCost();
                        }
                        else if (selectorFrag.getSelected() instanceof Residential)
                        {
                            cost = map.getSettings().getHouseBuildingCost();
                        }
                        else
                        {
                            cost = map.getSettings().getRoadBuildingCost();
                        }
                    }
                    if(selectorFrag.getSelected() != null && mapElement.getStructure() == null && mapElement.isBuildable() && (cost <= map.getMoney()))
                    {
                        if(map.checkAdj(mapElement.getRow(), mapElement.getCol()) || selectorFrag.getSelected() instanceof Road)
                        {
                            // Deduct appropriately
                            map.setMoney(map.getMoney() - cost);
                            // Tally appropriately
                            map.tallyUp(selectorFrag.getSelected());

                            mapElement.setStructure(selectorFrag.getSelected());
                            // call mapelement update method to update its information about this ME
                            map.updateMapElement(mapElement);

                            adapter.notifyItemChanged(getAdapterPosition());

                            taskbarFrag.refresh();
                            map.updateGameData(map);
                            //map.setTime(6); // THIS SETS AFTER THE REFRESH -- WORKING
                        }
                    }

                    else if(mapElement.isBuildable() && map.getMode() == 1) // Demolish
                    //else if(map.getMode() == 1) // Demolish
                    {
                        if(mapElement.hasStructure()) // We don't want to apply the following to blank space
                        {
                            if(mapElement.getImage() != null)
                            {
                                structureImg.setImageBitmap(null);
                                mapElement.setImage(null);
                                structureImg.setImageResource(0);
                            }
                            else if(mapElement.getStructure() != null)
                            {
                                structureImg.setImageResource(0);
                            }
                            map.tallyDown(mapElement.getStructure());
                            mapElement.setStructure(null);
                            // Apply to DB
                            map.updateMapElement(mapElement);
                            adapter.notifyItemChanged(getAdapterPosition());
                            taskbarFrag.refresh();
                            map.updateGameData(map);
                        }
                    }
                    else if(mapElement.getStructure() != null && map.getMode() == 2) // Info
                    { // Launch details activity here.
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra("col", mapElement.getCol());
                        intent.putExtra("row", mapElement.getRow());
                        startActivityForResult(intent, REQUEST_DETAIL);
                        adapter.notifyItemChanged(getAdapterPosition());
                    }
                    /* Refresh the map */
                    adapter.notifyItemChanged(getAdapterPosition());
                }
        });
    }

        public void bind(MapElement mapElement)
        {
            this.mapElement = mapElement;

            bottomLeft.setImageResource(mapElement.getSouthWest());
            bottomRight.setImageResource(mapElement.getSouthEast());
            topLeft.setImageResource(mapElement.getNorthWest());
            topRight.setImageResource(mapElement.getNorthEast());

            if(mapElement.getImage() != null)
            {
                structureImg.setImageBitmap(mapElement.getImage());
            }
            else if(mapElement.getStructure() != null)
            {
                structureImg.setImageResource(mapElement.getStructure().getDrawableId());
            }
            else
            {
                structureImg.setImageResource(0);
            }
        }
    }

    @Override // From Lecture Slides - same implementation.
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_DETAIL)
        {
            MapElement me = map.get(
                    data.getIntExtra("row", -1),
                    data.getIntExtra("col", -1));

            me.setImage((Bitmap)data.getExtras().get("thumbnail"));

            adapter.notifyDataSetChanged();
        }
    }


    public void setSelector(Fragment inSelFrag)
    {
        selectorFrag = (SelectorFragment)inSelFrag;
    }

    public void setTaskbar(Fragment inTaskFrag) {
        taskbarFrag = (TaskbarFragment)inTaskFrag;
    }


    /*  Sajib notes:  So, you need to make it remember. When you implement the “bind()” method (or
    whatever you call it), as well as actually updating the UI, have it take in and store the
    model object in a field for later reference     */
    // Bind method is the method that gets called when we need to update the VIEW. We call it in the adapter (vh.bind)
    // Adapter calls the bind method, and the bind method itself, sets whatever it needs to, in the view.
}


