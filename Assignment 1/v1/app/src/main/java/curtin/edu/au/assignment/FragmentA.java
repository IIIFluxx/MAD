package curtin.edu.au.assignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Fragment that is associated with changing the number of columns
 * or orientation of the currently visible RecyclerView
 * REFERENCE: Icons from https://materialdesignicons.com/
 */

public class FragmentA extends Fragment
{
    // Private class fields
    private View view;
    private ImageView oneColIcon, twoColIcon, threeColIcon, arrowIcon;

    //Fragment B reference inside this class
    private FragmentB fb;
    private FragmentD fd;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // get() methods
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_a, ui, false);
        oneColIcon = (ImageView)view.findViewById(R.id.oneColumnIcon);
        twoColIcon = (ImageView)view.findViewById(R.id.twoColumnIcon);
        threeColIcon = (ImageView)view.findViewById(R.id.threeColumnIcon);
        arrowIcon = (ImageView)view.findViewById(R.id.arrowIcon);

        // Start the onslaught of onClickListeners for each button.

        oneColIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(fb != null  && fb.isVisible() && getUserVisibleHint())
                {
                    fb.getGlm().setSpanCount(1);
                    fb.setColumns(1);
                }
                else if(fd != null && fd.isVisible() && getUserVisibleHint())
                {
                    fd.getGlm().setSpanCount(1);
                    fd.setColumns(1);
                }
            }
        });

        twoColIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(fb != null  && fb.isVisible() && getUserVisibleHint())
                {
                    fb.getGlm().setSpanCount(2);
                    fb.setColumns(2);
                }
                else if(fd != null && fd.isVisible() && getUserVisibleHint())
                {
                    fd.getGlm().setSpanCount(2);
                    fd.setColumns(2);
                }
            }
        });

        threeColIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(fb != null  && fb.isVisible() && getUserVisibleHint())
                {
                    fb.getGlm().setSpanCount(3);
                    fb.setColumns(3);
                }
                else if(fd != null && fd.isVisible() && getUserVisibleHint())
                {
                    fd.getGlm().setSpanCount(3);
                    fd.setColumns(3);
                }
            }
        });

        arrowIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Step 1. Make it change the RecycView scrolling
                // Step 2. onClick, replace image with alternate arrow

                String direction = String.valueOf(arrowIcon.getTag());
                // Case: When the button is -> clicked <-  on
                if(direction.equals("right")) // By default, the arrow/tag should be "right".
                {
                    arrowIcon.setImageResource(R.drawable.arrowdownbox);
                    // ===============

                    if(fb != null  && fb.isVisible() && getUserVisibleHint())
                    {
                        fb.getGlm().setOrientation(RecyclerView.HORIZONTAL);
                        fb.setOrientation(GridLayoutManager.HORIZONTAL);
                        fb.refresh();
                    }

                    if(fd != null && fd.isVisible() && getUserVisibleHint())
                    {
                        fd.getGlm().setOrientation(RecyclerView.HORIZONTAL);
                        fd.setOrientation(GridLayoutManager.HORIZONTAL);
                        fd.refresh();
                    }

                    // ===============

                    arrowIcon.setTag("down");
                }
                else // As default, or after clicking button twice or 4 times etc.
                {
                    arrowIcon.setImageResource(R.drawable.arrowrightbox);
                    // ===============
                    if(fb != null  && fb.isVisible() && getUserVisibleHint())
                    {
                        fb.getGlm().setOrientation(RecyclerView.VERTICAL);
                        fb.setOrientation(GridLayoutManager.VERTICAL);
                        fb.refresh();
                    }
                    if(fd != null && fd.isVisible() && getUserVisibleHint())
                    {
                        fd.getGlm().setOrientation(RecyclerView.VERTICAL);
                        fd.setOrientation(GridLayoutManager.VERTICAL);
                        fd.refresh();
                    }
                    // ===============
                    arrowIcon.setTag("right");
                }

            }
        });

        return view;
    }

    public void setFragmentB(FragmentB inFragB)
    {
        fb = inFragB;
    }

    public void setFragmentD(FragmentD inFragD)
    {
        fd = inFragD;
    }


}