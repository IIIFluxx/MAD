package curtin.edu.au.assignment;

import java.util.Arrays;
import java.util.List;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : User can select a structure to place on the map from a list provided in a RecycView.
 * Completely from MAD Practical 03 -- Nothing has been changed
 */

public class SelectorFragment extends Fragment
{
    Structure selected;
    StructureData structData;

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        structData = StructureData.get();
    }


    @Override
    // Defining Fragments
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(
                R.layout.fragment_selector, ui, false);
        // Obtain the RecyclerView UI element
        RecyclerView recycView = (RecyclerView) view.findViewById(R.id.selectorRecyclerView);
        // Specify how it should be laid out
        recycView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        SelectAdapter adapter = new SelectAdapter();
        recycView.setAdapter(adapter);
        return view;
    }

    // ========== ADAPTER ===============


    private class SelectAdapter extends RecyclerView.Adapter<SelectViewHolder>
    {
        // The Adapter creates ViewHolders and assigns data to them as needed. Here a VH is made
        // and returned.
        @Override
        public SelectViewHolder onCreateViewHolder( ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new SelectViewHolder(li, parent);
        }

        // called when RecyclerView needs to rewrite a particular list row
        // aka when RecyclerView needs to use a ViewHolder to display a different data element
        @Override
        public void onBindViewHolder( SelectViewHolder vh, int position)
        {
            // bind() method updates the ViewHolder (need to give it data)
            vh.bind(structData.get(position));
        }

        // return the total number of data elements
        @Override
        public int getItemCount()
        {
            return structData.size();
        }
    }


    // ========== VIEW HOLDER ===============

    private class SelectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        // Declaration of variables
        ImageView image;
        TextView text;
        Structure currStruct;

        public SelectViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_selection, parent, false));
            // This is our doing method.

            // Grab all UI elements
            image = itemView.findViewById(R.id.imageView);
            text = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        public void bind(Structure data)
        {
            currStruct = data;
            image.setImageResource(data.getDrawableId());
            text.setText(data.getLabel());
        }


        @Override
        public void onClick(View view) {
            selected = currStruct;
        }
    }



    public Structure getSelected()
    {
        return selected;
    }


}

