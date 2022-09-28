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
 * PURPOSE : Helper class to create the GameData Database
 */
public class GameDataDBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "gamedata.db";

    public GameDataDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE " + GameDataTable.NAME + "(" +
                GameDataTable.Cols.ID+ " INTEGER, " +
                GameDataTable.Cols.MONEY+ " INTEGER, " +
                GameDataTable.Cols.TIME + " INTEGER, " +
                GameDataTable.Cols.POPULATION + " INTEGER, " +
                GameDataTable.Cols.EMPRATE + " REAL, " +
                GameDataTable.Cols.NROAD + " INTEGER, " +
                GameDataTable.Cols.NCOMM + " INTEGER, " +
                GameDataTable.Cols.NRES + " INTEGER, " +
                GameDataTable.Cols.INCOME + " REAL, " +
                GameDataTable.Cols.MODE + " INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        // Not implemented.
    }
}
