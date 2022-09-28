package curtin.edu.au.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE :  Activity launches fragments necessary for selecting/answering a question
 *  ~ in both phone and tablet
 */

public class QuestionSelectActivity extends AppCompatActivity
{
    private int flagIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionselect);
        // Activity code begins after this line.

        boolean isTabletLand, isPhone;
        isTabletLand = getResources().getBoolean(R.bool.is_tabletLand);
        isPhone = getResources().getBoolean(R.bool.is_phone);

        // if in tablet, don't call activity 4.
        // if in phone, call activity 4.

        // Add Fragments to FlagSelectActivity.
        FragmentManager fm = getSupportFragmentManager(); // Make a new fragment manager. Handles all 3 fragments

        FragmentA fragA = (FragmentA) fm.findFragmentById(R.id.FrameLayoutA); //Assign fragment A class, to its XML
        FragmentD fragD = (FragmentD) fm.findFragmentById(R.id.FrameLayoutD); //Assign fragment D class, to its XML
        FragmentC fragC = (FragmentC) fm.findFragmentById(R.id.FrameLayoutC); //Assign fragment C class, to its XML

        // If fragments have NOT been loaded:
        if(fragA == null)
        {
            // Create a new FragmentA Object.
            fragA = new FragmentA();
            // Start the Fragment
            fm.beginTransaction().add(R.id.FrameLayoutA,fragA).commit(); // Start.
        }



        if(fragD == null)
        {
            // Create a new FragmentD Object.
            fragD = new FragmentD();
            // Start the Fragment
            fm.beginTransaction().add(R.id.FrameLayoutD,fragD).commit(); // Start.
        }



        if(fragC == null)
        {
            // Create a new FragmentB Object.
            fragC = new FragmentC();
            // Start the Fragment
            fm.beginTransaction().add(R.id.FrameLayoutC,fragC).commit(); // Start.
        }

        if(isTabletLand)
        {
            FragmentE fragE = (FragmentE) fm.findFragmentById(R.id.FrameLayoutE); //Assign fragment C class, to its XML
            if(fragE == null)
            {
                // Create a new FragmentB Object.
                fragE = new FragmentE();
                // Start the Fragment
                fm.beginTransaction().add(R.id.FrameLayoutE,fragE).commit(); // Start.
            }
            fragE.setFragmentC(fragC);
            fragC.setFragmentE(fragE);
        }

        // Give Fragment A, a reference to Fragment B, so that we can call Fragment B's methods
        // inside Fragment A whenever we like ~ This is for button interaction in Fragment A).
        /* fragA.setFragments(fragB); */
        fragA.setFragmentD(fragD);

        fragC.setFragmentD(fragD);

        // Give FragD reference to the index.
        //fragD.transferIndex(flagIndex);


    }

}
