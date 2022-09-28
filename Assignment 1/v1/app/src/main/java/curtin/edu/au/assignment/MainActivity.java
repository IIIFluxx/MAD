package curtin.edu.au.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Main activity -- start screen. You press Start button to start playing the game
 */

public class MainActivity extends AppCompatActivity {

    // Set all relevant fields and buttons.
    private TextView seedText,targetText;
    private Button startBtn;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Inflates the XML file - activity_main.xml

        // Initialise buttons on main screen.
        startBtn = (Button)findViewById(R.id.startButton);
        targetText = (TextView)findViewById(R.id.targetText);
        seedText = (TextView) findViewById(R.id.seedText);


        player = new Player(0,0);
        Player.setInstance(player);

        initialisePoints(); // Sets the target and seed points to be displayed on screen.

        // Set targetText and seedText to randomised numbers.
        seedText.setText("Seed points: " + String.valueOf(player.getSeedPoints()));
        targetText.setText("Target point: " + String.valueOf(player.getTargetPoint()));


        // Random number stuff should now be set and active on the device. Now implement Start Btn.

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, FlagSelectActivity.class));
            }
        });
    }


    public void initialisePoints()
    {
        Random rand = new Random();
        int target, seed = 0;

        do  // Make sure the target point is greater than the seed point.
        {
            target = rand.nextInt(100);
            target += 1;

            seed = rand.nextInt(100);
            seed += 1;
        } while (target < seed); // Loop until target is more than seed.

        player.setTargetPoint(target);
        player.setSeedPoints(seed);
    }
}