package curtin.edu.au.assignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import curtin.edu.au.assignment.DatabaseSchema.SettingsTable;
import curtin.edu.au.assignment.DatabaseSchema.MapElementTable;
import curtin.edu.au.assignment.DatabaseSchema.GameDataTable;
/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Helper class to create the MapElement Database
 */
public class MapElementDBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "mapelement.db";

    public MapElementDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // CREATE TABLE
        db.execSQL("CREATE TABLE " + MapElementTable.NAME + "(" +
            MapElementTable.Cols.KEYID + " INTEGER, " +
            MapElementTable.Cols.STRUCTID + " INTEGER, " +
            MapElementTable.Cols.STRUCTTYPE + " TEXT, " +
            MapElementTable.Cols.LABEL + " TEXT, " +
            MapElementTable.Cols.ROW + " INTEGER, " +
            MapElementTable.Cols.COL + " INTEGER, " +
            MapElementTable.Cols.BUILDABLE + " INTEGER, " + // Boolean / Integer.
            MapElementTable.Cols.NW + " INTEGER, " +
            MapElementTable.Cols.NE + " INTEGER, " +
            MapElementTable.Cols.SW + " INTEGER, " +
            MapElementTable.Cols.SE + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        // Not implemented.
    }
}
