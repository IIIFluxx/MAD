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
 * PURPOSE : Helper class to create the Settings Database
 */

public class SettingsDBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "settings.db";

    public SettingsDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // CREATE TABLE
        db.execSQL("CREATE TABLE " + SettingsTable.NAME + "(" +
                SettingsTable.Cols.MAPWIDTH + " INTEGER, " +
                SettingsTable.Cols.MAPHEIGHT + " INTEGER, " +
                SettingsTable.Cols.INITIALMONEY + " INTEGER, " +
                SettingsTable.Cols.CITY + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        // Not implemented.
    }
}
