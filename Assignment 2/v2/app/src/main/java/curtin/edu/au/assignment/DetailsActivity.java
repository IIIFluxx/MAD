package curtin.edu.au.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 10-11-20
 * PURPOSE : Activity that users can inspect each placed Structure & use the thumbnail functionality
 */

public class DetailsActivity extends AppCompatActivity
{
    private EditText nameText;
    private TextView typeText;
    private TextView coordText;
    private ImageView img;
    private Button cancelBtn;
    private ImageView applyImg;
    private int row, col;
    private MapElement selected;
    private Bitmap thumbnail;
    private Intent ret;

    // From Lec Slides
    private static final int REQUEST_THUMBNAIL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        GameData gd = GameData.get();
        ret = new Intent();

        // Get XML references
        nameText = (EditText)findViewById(R.id.nameEditText); // What user enters for Label
        typeText = (TextView)findViewById(R.id.typeLbl); // Type based on Structure.
        coordText = (TextView)findViewById(R.id.coordLbl); // Row and Col of MapElement/Structure.
        img = (ImageView)findViewById(R.id.structureImg); // Image - either Custom or Drawable ID
        applyImg = (ImageView) findViewById(R.id.saveImg); // Used to apply any changes.
        cancelBtn = (Button)findViewById(R.id.cancelBtn); // Used to revert back to the map screen.

        /*
            Plan:
            Use Intents to get the ROW/COL
            Use gd.get(row,col) to get the Map Element
            Display text according to that map element

            nameText = An editable name. By default, this is the same as the structure type.
         */
        Intent intent = getIntent();
        row = intent.getIntExtra("row", -1);
        col = intent.getIntExtra("col", -1);

        selected = gd.get(row, col);
        Structure struct = selected.getStructure();
        thumbnail = selected.getImage();

        if(struct != null) {
            if (selected.getOwnerName() != null)
            {
                if (selected.getOwnerName().equals("") && struct.getLabel() != null)
                {
                    nameText.setText(struct.getLabel()); // Use the label by default IF ownername empty
                }
                else
                {
                    if(selected != null && selected.getOwnerName() != null) {
                        nameText.setText(selected.getOwnerName()); // Use owner name (from DB/ME).
                    }
                }
            }
            else {
                nameText.setText(struct.getLabel());
            }
        }


        typeText.setText(struct.getLabel());
        // Default: "X, Y"
        coordText.setText(String.valueOf(row + 1) + ", " + String.valueOf(col + 1));


        applyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Set new name
                String newOwner = nameText.getText().toString();
                selected.setOwnerName(newOwner);

                // Update DB with name.
                GameData.get().updateMapElement(selected);

                // Manage thumbnail changes. || Give back the details of the ME with updated info.
                ret.putExtra("row", row);
                ret.putExtra("col", col);
                ret.putExtra("thumbnail", thumbnail);
                // We set it in MapFrag.


                // The data can't be retrieved in MapFragment unless the result is ok i.e. ready.
                // Thus we set RESULT_OK for MapFrag's onActivityResult().
                setResult(RESULT_OK, ret);
                finish();
            }
        });



        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Thumbnail Functionality

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Returning Data: Thumbnail Photos */
                Intent thumbnailPhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(thumbnailPhotoIntent, REQUEST_THUMBNAIL); /**This evokes the Camera App.*/
            }
        });

    }

    // Set bitmap to classfield -- Code from Lecture Slides
    /** Save the bitmap taken in the Camera app */
    public void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);
        if (resultCode == RESULT_OK && requestCode == REQUEST_THUMBNAIL) {
    // The return intent from the camera contains the small ("thumbnail") photo inside an extra called "data".
            thumbnail = (Bitmap) resultIntent.getExtras().get("data");
        }
    }
}
