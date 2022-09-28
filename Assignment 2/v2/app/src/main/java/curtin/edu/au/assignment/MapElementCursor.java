package curtin.edu.au.assignment;

import android.database.Cursor;
import android.database.CursorWrapper;
import curtin.edu.au.assignment.DatabaseSchema.MapElementTable;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Class that iterates through the MapElement DB Table and returns a ME Object.
 */

public class MapElementCursor extends CursorWrapper {
    public MapElementCursor(Cursor c) {
        super(c);
    }

    public MapElement getME()
    {
        Structure currStruct = null;
        MapElement me = null;
        int keyID = getInt(getColumnIndex(MapElementTable.Cols.KEYID));
        int row = getInt(getColumnIndex(MapElementTable.Cols.ROW));
        int col = getInt(getColumnIndex(MapElementTable.Cols.COL));
        boolean buildable = checkBool();
        int NW = getInt(getColumnIndex(MapElementTable.Cols.NW));
        int NE = getInt(getColumnIndex(MapElementTable.Cols.NE));
        int SW = getInt(getColumnIndex(MapElementTable.Cols.SW));
        int SE = getInt(getColumnIndex(MapElementTable.Cols.SE));

		// Make appropriate Structure depending on the ID.
        String structType = getString(getColumnIndex(MapElementTable.Cols.STRUCTTYPE));
        int structID = getInt(getColumnIndex(MapElementTable.Cols.STRUCTID)); // The Drawable ID of the Structure
        String label = getString(getColumnIndex(MapElementTable.Cols.LABEL)); // aka OwnerName

        if(structID != 0) // If a structure exists (aka isn't just grass).
        {
            if(structType.equals("road"))
            {
                currStruct = new Road(structID, "Road");
            }
            else if (structType.equals("residential"))
            {
                currStruct = new Residential(structID, "Residential");
            }
            else if(structType.equals("commercial"))
            {
                currStruct = new Commercial(structID, "Commercial");
            }
        }

        me = new MapElement(keyID, row, col, buildable, NW, NE, SW, SE, currStruct, label);
        return me;
    }

    public boolean checkBool()
    {
        boolean exp = false;
        if(getInt(getColumnIndex(MapElementTable.Cols.BUILDABLE)) == 1)
        {
            exp = true;
        }
        return exp;
    }
}
