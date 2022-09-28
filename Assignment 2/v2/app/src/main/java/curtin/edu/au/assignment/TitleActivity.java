package curtin.edu.au.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Start of the program - launches activity depending on what the user would like.
 * Primarily made in MAD Practical 03 and modified to meet assignment requirements
 */

public class TitleActivity extends AppCompatActivity {

    //Buttons
    private ImageView playButton;
    private ImageView settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        //Set reference to buttons on title
        playButton = (ImageView) findViewById(R.id.playImg);
        settingsButton = (ImageView) findViewById(R.id.settingsImg);

        // Get settings instance
        final Settings settings = Settings.get();
        //  Load settings from DB if exists
        settings.loadSettings(getApplication()); // Loads to DB
        settings.addSettings(settings); // Adds current settings to DB

        GameData.setHeight(settings.getMapHeight()); // Assigns for Map creation (final ints - previously constants)
        GameData.setWidth(settings.getMapWidth()); // Assigns for Map creation (final ints - previously constants)

        GameData gd = GameData.get(); // This would make a new map.
        gd.setSettings(settings); // Sets the local settings obj to DB GameData obj.


        gd.loadDatabases(getApplication());
        gd.addGameData(gd);
        gd.saveEveryElement();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TitleActivity.this, MapActivity.class));
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TitleActivity.this, SettingsActivity.class));
            }
        });
    }


}
