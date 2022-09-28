package curtin.edu.au.assignment;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Class that iterates through the GameData DB Table and returns a GD Object.
 */

public class GameDataCursor extends CursorWrapper {
    public GameDataCursor(Cursor c) {
        super(c);
    }

    public Number[] getGameData()
    {
        Number[] arr = new Number[9];
        arr[0] = getInt(getColumnIndex(DatabaseSchema.GameDataTable.Cols.MONEY));
        arr[1] = getInt(getColumnIndex(DatabaseSchema.GameDataTable.Cols.TIME));
        arr[2] = getInt(getColumnIndex(DatabaseSchema.GameDataTable.Cols.POPULATION));
        arr[3] = getDouble(getColumnIndex(DatabaseSchema.GameDataTable.Cols.EMPRATE));
        arr[4] = getInt(getColumnIndex(DatabaseSchema.GameDataTable.Cols.NROAD));
        arr[5] = getInt(getColumnIndex(DatabaseSchema.GameDataTable.Cols.NCOMM));
        arr[6] = getInt(getColumnIndex(DatabaseSchema.GameDataTable.Cols.NRES));
        arr[7] = getDouble(getColumnIndex(DatabaseSchema.GameDataTable.Cols.INCOME));
        arr[8] = getInt(getColumnIndex(DatabaseSchema.GameDataTable.Cols.MODE));
        return arr;
    }
}