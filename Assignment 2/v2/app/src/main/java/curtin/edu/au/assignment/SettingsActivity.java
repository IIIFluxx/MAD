package curtin.edu.au.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Activity that allows user to edit certain game settings then saves it to the Settings DB
 * REFERENCE: Toasts code - https://developer.android.com/guide/topics/ui/notifiers/toasts#java
 */

public class SettingsActivity extends AppCompatActivity
{
    // GameData reference
    GameData gd;

    //Setting fields
    private EditText mapWidthField;
    private EditText mapHeightField;
    private EditText initMoneyField;
    private EditText cityField;

    private Button cancelBtn;
    private Button applyBtn;

    private Settings s = Settings.get();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        gd = GameData.get();

        mapWidthField = (EditText)findViewById(R.id.mapWidth);
        mapHeightField = (EditText)findViewById(R.id.mapHeight);
        initMoneyField = (EditText)findViewById(R.id.initFunds);
        cityField = (EditText)findViewById(R.id.cityEdit);

        //Get reference to Buttons
        applyBtn = (Button)findViewById(R.id.applyBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);

        //Show current settings.
        mapWidthField.setText(String.valueOf(gd.getSettings().getMapWidth()));
        mapHeightField.setText(String.valueOf(gd.getSettings().getMapHeight()));
        initMoneyField.setText(String.valueOf(gd.getSettings().getInitialMoney()));
        cityField.setText(String.valueOf(gd.getSettings().getCity()));


        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, "Warning! Changing settings\nwill reset the current game save\nboth the map and data", duration);
        toast.show();


        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int newWidth = Integer.parseInt(String.valueOf(mapWidthField.getText()));
                int newHeight = Integer.parseInt(String.valueOf(mapHeightField.getText()));
                int newMoney = Integer.parseInt(String.valueOf(initMoneyField.getText()));
                String newCity = cityField.getText().toString();

				// =============
				// We set the new values to the Settings obj (singleton).
                s.setInitialMoney(newMoney);
                s.setCity(newCity);
                s.setMapHeight(newHeight);
                s.setMapWidth(newWidth);

				// We update the database with the updated singleton object.
                s.updateSettings(Settings.get());
				
				// Up to here, the Settings singleton object and DB is updated.
				// Now we make sure GD is updated.
				
				// Update gamedata classfields - final ints used to make a new map + init money.
                gd.setMoney(s.getInitialMoney());
                
				gd.setHeight(s.getMapHeight());
                
				gd.setWidth(s.getMapWidth());

                // =========
                gd.deleteDatabases(); // To make sure GameData has the updated settings object, we delete current method then reload.
                // Now the DB is reset/empty.

                gd.reset(); // Now the GD object is empty i.e. we have a new map to work with.

                // Now we reload with new settings as if it's a fresh game.
                gd.setSettings(s); // Sets the singleton obj to s -- now the singleton obj should have new W/H/M.

                //gd.setMoney(5); // Test -- works
                gd.loadDatabases(getApplication());
                gd.addGameData(gd); // Adds the current gamedata obj to the GameDataTable in DB.
                gd.saveEveryElement();

                finish();
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}