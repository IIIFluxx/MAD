package curtin.edu.au.assignment;
import android.graphics.Bitmap;

/**
 * Represents a single grid square in the map. Each map element has both terrain and an optional
 * structure.
 *
 * The terrain comes in four pieces, as if each grid square was further divided into its own tiny
 * 2x2 grid (north-west, north-east, south-west and south-east). Each piece of the terrain is
 * represented as an int, which is actually a drawable reference. That is, if you have both an
 * ImageView and a MapElement, you can do this:
 *
 * ImageView iv = ...;
 * MapElement me = ...;
 * iv.setImageResource(me.getNorthWest());
 *
 * This will cause the ImageView to display the grid square's north-western terrain image,
 * whatever it is.
 *
 * (The terrain is broken up like this because there are a lot of possible combinations of terrain
 * images for each grid square. If we had a single terrain image for each square, we'd need to
 * manually combine all the possible combinations of images, and we'd get a small explosion of
 * image files.)
 *
 * Meanwhile, the structure is something we want to display over the top of the terrain. Each
 * MapElement has either zero or one Structure} objects. For each grid square, we can also change
 * which structure is built on it.
 */

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Object class that represents one grid square in the map.
 * Primarily made in MAD Practical 03 and modified to meet assignment requirements
 */




public class MapElement
{
    private final boolean buildable;
    private final int terrainNorthWest;
    private final int terrainSouthWest;
    private final int terrainNorthEast;
    private final int terrainSouthEast;
    private Structure structure;
    private String ownerName;
    private int keyID;

    // Added classfields (not from Prac).
    private int row;
    private int col;

    private Bitmap image;

    public MapElement(int keyID, int row, int col, boolean buildable, int northWest, int northEast,
                      int southWest, int southEast, Structure structure, String ownerName)
    {
        this.keyID = keyID;
        this.row = row;
        this.col = col;
        this.buildable = buildable;
        this.terrainNorthWest = northWest;
        this.terrainNorthEast = northEast;
        this.terrainSouthWest = southWest;
        this.terrainSouthEast = southEast;
        this.structure = structure;
        this.ownerName = ownerName;
    }


	/** Getters and Setters - generated using Alt + Insert */

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isBuildable()
    {
        return buildable;
    }

    public int getBuildable()
    {
        int export = 0;
        if(buildable == true)
        {
            export = 1;
        }
        else
        {
            export = 0;
        }
        return export;
    }

    public boolean hasStructure()
    {
        return structure != null;
    }

    public int getNorthWest()
    {
        return terrainNorthWest;
    }

    public int getSouthWest()
    {
        return terrainSouthWest;
    }

    public int getNorthEast()
    {
        return terrainNorthEast;
    }

    public int getSouthEast()
    {
        return terrainSouthEast;
    }

    public Structure getStructure()
    {
        return structure;
    }

    public void setStructure(Structure structure)
    {
        this.structure = structure;
    }

    public int getKeyID() {
        return keyID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
