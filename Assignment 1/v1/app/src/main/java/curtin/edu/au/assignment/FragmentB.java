package curtin.edu.au.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Fragment that is associated with displaying all flags
 */

public class FragmentB extends Fragment {

    private View view;
    private GridAdapter adapter;
    private FlagData flag;
    private GridLayoutManager glm;
    private boolean isActive = false;
    public static int fIndex = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_b, ui, false);

        // Have your data ready.
        flag = FlagData.get();

        glm = new GridLayoutManager(
                getActivity(), flag.getCols(),
                flag.getOrientation(),
                //GridLayoutManager.VERTICAL,
                false);

        // Obtain the RecyclerView UI element
        RecyclerView recycView = (RecyclerView) view.findViewById(R.id.flagRecyclerView);
        // Specify how it should be laid out
        recycView.setLayoutManager(glm);
        adapter = new GridAdapter(flag);
        recycView.setAdapter(adapter);
        return view;
    }

    public void setColumns(int ii) {
        flag.setCols(ii);
        adapter.notifyDataSetChanged();
    }

    public void setOrientation(int ii) {
        flag.setOrientation(ii);
        adapter.notifyDataSetChanged();
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    public GridLayoutManager getGlm() {
        return glm;
    }

    public GridAdapter getAdapter() {
        return adapter;
    }

    // ========== ADAPTER ===============
    private class GridAdapter extends RecyclerView.Adapter<GridViewHolder> {
        private FlagData fd;


        public GridAdapter(FlagData fd) {
            this.fd = fd; //Store reference to FlagData (which will contain all 16 Flags)
        }

        @Override
        public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new GridViewHolder(li, parent);
        } // Done.

        @Override
        public void onBindViewHolder(GridViewHolder vh, int position) {
            vh.bind(fd.get(position));
        }

        @Override
        public int getItemCount() {
            return (fd.size());
        }

    }


    // ========== VIEW HOLDER ===============
    private class GridViewHolder extends RecyclerView.ViewHolder {
        // Declaration of variables
        ImageView flagImage;
        TextView flagText;
        Flag currFlag;


        public GridViewHolder(LayoutInflater li, ViewGroup parent) {
            super(li.inflate(R.layout.list_flag, parent, false));
            flagImage = (ImageView) itemView.findViewById(R.id.flagIcon);
            flagText = (TextView) itemView.findViewById(R.id.flagText);
        }

        public void bind(Flag data) {
            currFlag = data; // Dealing with ONE FLAG AT A TIME - e.g. Australia
            flagImage.setImageResource(data.getDrawableId()); // e.g. Set Aus image
            flagText.setText(data.getLabel()); // e.g. Set Aus text
            // IF TALLY = NUM QUESTIONS, MAKE FLAG IMAGE UNCLICKABLE <here>
            // ELSE -- flagImage.setOnClickListener()....

            flagImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // Click on Aus ONLY - get index to launch appropriate Flag index.

                    // Check if any special points are needing to be redeemed. If so, add onto that flag
                    // STEP 1: Get QuestionData object. See if tally = 10, if so, add

                    // What does this mean? We start an Activity, and PASS it the flag index.
                    fIndex = getAbsoluteAdapterPosition();
                    startActivity(new Intent(getContext(), QuestionSelectActivity.class));


                }
            });

            // getAbsoluteAdapterPosition(1) = Flag 1 in FlagData
            // This means, we can access each flag's unique properties, via index
            // In 3rd Activity class, we access Flag #1 in FlagData.
        }
    }

    public static int getIndex() {
        return fIndex;
    }
}