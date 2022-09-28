package curtin.edu.au.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Activity launches fragments necessary for selecting a flag - in both phone and tablet
 */

public class FlagSelectActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flagselect);
        // Activity code begins after this line.


        // Add Fragments to FlagSelectActivity.
        FragmentManager fm = getSupportFragmentManager(); // Make a new fragment manager. Handles all 3 fragments

        FragmentA fragA = (FragmentA) fm.findFragmentById(R.id.FrameLayoutA); //Assign fragment A class, to its XML
		


        // If fragments have NOT been loaded:
        if(fragA == null)
        {
            // Create a new FragmentA Object.
            fragA = new FragmentA();
            // Start the Fragment
            fm.beginTransaction().add(R.id.FrameLayoutA,fragA).commit(); // Start.
        }

        FragmentB fragB = (FragmentB) fm.findFragmentById(R.id.FrameLayoutB); //Assign fragment B class, to its XML

        if(fragB == null)
        {
            // Create a new FragmentB Object.
            fragB = new FragmentB();
            // Start the Fragment
            fm.beginTransaction().add(R.id.FrameLayoutB,fragB).commit(); // Start.
        }

        FragmentC fragC = (FragmentC) fm.findFragmentById(R.id.FrameLayoutC); //Assign fragment C class, to its XML

        if(fragC == null)
        {
            // Create a new FragmentB Object.
            fragC = new FragmentC();
            // Start the Fragment
            fm.beginTransaction().add(R.id.FrameLayoutC,fragC).commit(); // Start.
        }

        // Give Fragment A, a reference to Fragment B, so that we can call Fragment B's methods
        // inside Fragment A whenever we like ~ This is for button interaction in Fragment A).
        fragA.setFragmentB(fragB);

        fragC.setFragmentB(fragB);

    }


}