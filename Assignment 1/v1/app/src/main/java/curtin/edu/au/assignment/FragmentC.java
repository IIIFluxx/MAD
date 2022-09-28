package curtin.edu.au.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Fragment that is associated with displaying the number of points and win/loss status
 */

public class FragmentC extends Fragment
{
    // Private class fields
    private View view;
    private TextView pointsTxt;
    private Button returnButton;
    private Player player;

    //Fragment references inside this class
    private FragmentB fb;
    private FragmentD fd;
    private FragmentE fe;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get() methods
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_c, ui, false);
        player = Player.get();

        pointsTxt = (TextView)view.findViewById(R.id.pointsText);
        returnButton = (Button)view.findViewById(R.id.returnButton);

        // TODO: Make it GONE and then VISIBLE onClick.

        returnButton.setVisibility(View.VISIBLE);

        if(fd != null && fd.isVisible() && getUserVisibleHint())
        {
            returnButton.setVisibility(View.VISIBLE);
        }

        if(fe != null && fe.isVisible() && getUserVisibleHint())
        {
            returnButton.setVisibility(View.VISIBLE);
        }


        setPointsTxt();

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(fd != null && fd.isVisible() && getUserVisibleHint())
                {
                    setPointsTxt();
                    startActivity(new Intent(getContext(), FlagSelectActivity.class));
                }
                else if(fe != null && fe.isVisible() && getUserVisibleHint())
                {
                    if(fe.getCurrQuestion().getAnswered() == 0)
                    {
                        setPointsTxt();
                        startActivity(new Intent(getContext(), QuestionSelectActivity.class));
                    }
                    else if(fe.getCurrQuestion().getAnswered() == 1)
                    {
                        if(fe.getCurrQuestion().isSpecial() == true)
                        {
                            setPointsTxt();
                            startActivity(new Intent(getContext(), FlagSelectActivity.class));
                        }
                        else // if normal qn
                        {
                            setPointsTxt();
                            startActivity(new Intent(getContext(), QuestionSelectActivity.class));
                        }
                    }
                }
            }
        });

        setPointsTxt();

        if(player.getSeedPoints() >= player.getTargetPoint())
        {
            pointsTxt.setText("You win!");
            pointsTxt.setTextColor(Color.RED);
        }

        if(player.getSeedPoints() < 0)
        {
            pointsTxt.setText("You lose! :*(");
            pointsTxt.setTextColor(Color.RED);
            //TODO: Send back to first screen.
        }


        return view;
    }

    public void setPointsTxt()
    {
        pointsTxt.setText("Current points: " + String.valueOf(player.getSeedPoints()));
    }


    public void setFragmentD(FragmentD inFragD)
    {
        fd = inFragD;
    }

    public void setFragmentE(FragmentE inFragE)
    {
        fe = inFragE;
    }

    public void setFragmentB(FragmentB inFragB)
    {
        fb = inFragB;
    }


}