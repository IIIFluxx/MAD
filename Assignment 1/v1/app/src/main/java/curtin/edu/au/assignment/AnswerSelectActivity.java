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
 * PURPOSE : Activity launches fragments necessary for answering a question in both phone and tablet
 */


public class AnswerSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerselect);

        // Activity code begins after this line.
        Intent intent = getIntent();
        int questionIndex = intent.getIntExtra("qnIndex", 0);

        // Add Fragments to FlagSelectActivity.
        FragmentManager fm = getSupportFragmentManager(); // Make a new fragment manager. Handles all 3 fragments

        // TASK 01: Load Fragment C
        FragmentC fragC = (FragmentC) fm.findFragmentById(R.id.FrameLayoutC); //Assign fragment C class, to its XML

        if(fragC == null)
        {
            // Create a new FragmentC Object.
            fragC = new FragmentC();
            // Start the Fragment
            fm.beginTransaction().add(R.id.FrameLayoutC,fragC).commit(); // Start.
        }

        // TASK 02: Load Fragment E
        FragmentE fragE = (FragmentE) fm.findFragmentById(R.id.FrameLayoutE); //Assign fragment C class, to its XML

        if(fragE == null)
        {
            // Create a new FragmentB Object.
            fragE = new FragmentE();
            // Start the Fragment
            fm.beginTransaction().add(R.id.FrameLayoutE,fragE).commit(); // Start.
        }

        // Give FragD reference to the index.
        fragC.setFragmentE(fragE);
        fragE.setFragmentC(fragC);
    }
}