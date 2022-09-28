package curtin.edu.au.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Activity that kick-starts the game screen. Primarily made in MAD Practical 03
 */

public class MapActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Add Fragments to MapActivity.
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFrag = (MapFragment) fm.findFragmentById(R.id.map);
        SelectorFragment selectorFrag = (SelectorFragment) fm.findFragmentById(R.id.selector);
        TaskbarFragment taskbarFrag = (TaskbarFragment) fm.findFragmentById(R.id.taskbar);

        if (selectorFrag == null)
        {
            selectorFrag = new SelectorFragment();
            fm.beginTransaction()
                    .add(R.id.selector,selectorFrag).commit();
        }

        if (mapFrag == null)
        {
            mapFrag = new MapFragment();
            fm.beginTransaction()
                    .add(R.id.map,mapFrag).commit();
        }

		// Added since the Practical - new taskbar fragment which shows Game Information.
		
        if(taskbarFrag == null)
        {
            taskbarFrag = new TaskbarFragment();
            fm.beginTransaction()
                    .add(R.id.taskbar,taskbarFrag).commit();
        }

        // personal note: Give Fragment A, a reference to Fragment B, so that we can call Fragment B's methods
        // inside Fragment A whenever we like ~ This is for button interaction in Fragment A).
        //fragA.setFragmentB(fragB);
        mapFrag.setTaskbar(taskbarFrag);
        mapFrag.setSelector(selectorFrag); // This extra line -- gives ref to SF

    }
}