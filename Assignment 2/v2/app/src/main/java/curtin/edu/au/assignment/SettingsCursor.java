package curtin.edu.au.assignment;

import android.database.Cursor;
import android.database.CursorWrapper;
/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Class that iterates through the Settings DB Table and returns a Settings Object.
 */
public class SettingsCursor extends CursorWrapper
{
    public SettingsCursor(Cursor c) {
        super(c);
    }

    public Settings getSettings()
    {
        Settings s = Settings.get();

        int initMoney = getInt(getColumnIndex(DatabaseSchema.SettingsTable.Cols.INITIALMONEY));
        int height = getInt(getColumnIndex(DatabaseSchema.SettingsTable.Cols.MAPHEIGHT));
        int width = getInt(getColumnIndex(DatabaseSchema.SettingsTable.Cols.MAPWIDTH));
        String city = getString(getColumnIndex(DatabaseSchema.SettingsTable.Cols.CITY));

        s.setInitialMoney(initMoney);
        s.setMapHeight(height);
        s.setMapWidth(width);
        s.setCity(city);

        return s;
        //return new Settings(width,height,initMoney);
    }

}


