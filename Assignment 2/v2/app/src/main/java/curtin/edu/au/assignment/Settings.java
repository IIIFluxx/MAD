package curtin.edu.au.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import curtin.edu.au.assignment.DatabaseSchema.SettingsTable;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Singleton that stores information specific to the game's settings.
 * Also contains relevant Database methods for resume functionality
 */

public class Settings
{
    // Declare private classfields ~ from spec sheet UML.
    private int mapWidth;
    private int mapHeight;
    private int initialMoney;
    private int familySize;
    private int shopSize;
    private int salary;
    private double taxRate;
    private int serviceCost;
    private int houseBuildingCost;
    private int commBuildingCost;
    private int roadBuildingCost;
    private String city;

    private SQLiteDatabase db;
    private SettingsCursor sc;



	
    public static Settings get()
    {
        if (instance == null)
        {
            instance = new Settings();
        }
        return instance;
    }

	// Default Constructor -- default values as specified by the Assignment Specification.
	
    public Settings()
    {
        this.mapWidth = 50;
        this.mapHeight = 10;
        this.initialMoney = 1000; // Starting money
        this.familySize = 4;
        this.shopSize = 6;
        this.salary = 10; // Salary = curr money per unit time
        this.taxRate = 0.3;
        this.serviceCost = 2;
        this.houseBuildingCost = 100;
        this.commBuildingCost = 500;
        this.roadBuildingCost = 20;
        this.city = "London";
        // Assumption - changing the City name will not affect the game's Temperature.
    }


    private static Settings instance = null;


    // Alternate constructor that takes in 4 things -- called from the Settings Activity
    // whenever User wishes to change the main three settings - Map w/h,  Init. Money & City name.

    public Settings(int mapWidth, int mapHeight, int initialMoney, String inCity)
    {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.initialMoney = initialMoney;
        this.familySize = 4;
        this.shopSize = 6;
        this.salary = 10;
        this.taxRate = 0.3;
        this.serviceCost = 2;
        this.houseBuildingCost = 100;
        this.commBuildingCost = 500;
        this.roadBuildingCost = 20;
        this.city = inCity;
        // Assumption - changing the City name will not affect the game's Temperature.
    }



    // =============================================================================================
    // DB Functions - from Lecture Slides.
    // =============================================================================================

    /** Database methods for Settings Table */

    public void addSettings(Settings s)
    {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseSchema.SettingsTable.Cols.MAPWIDTH, s.getMapWidth());
        cv.put(DatabaseSchema.SettingsTable.Cols.MAPHEIGHT, s.getMapHeight());
        cv.put(DatabaseSchema.SettingsTable.Cols.INITIALMONEY, s.getInitialMoney());
        cv.put(DatabaseSchema.SettingsTable.Cols.CITY, s.getCity());
        db.insert(DatabaseSchema.SettingsTable.NAME, null, cv);
    }

    public void updateSettings(Settings s)
    {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseSchema.SettingsTable.Cols.MAPWIDTH, s.getMapWidth());
        cv.put(DatabaseSchema.SettingsTable.Cols.MAPHEIGHT, s.getMapHeight());
        cv.put(DatabaseSchema.SettingsTable.Cols.INITIALMONEY, s.getInitialMoney());
        cv.put(DatabaseSchema.SettingsTable.Cols.CITY, s.getCity());
        db.update(DatabaseSchema.SettingsTable.NAME, cv, null, null);
    }

    public void loadSettings(Context c)
    {
        //initialise database and cursor
        db = new SettingsDBHelper(c.getApplicationContext()).getWritableDatabase();
		
		// Cursor comments from Lecture Slides.
        sc = new SettingsCursor(
                db.query(DatabaseSchema.SettingsTable.NAME, // FROM clause for our table
                        null, // SELECT all columns
                        null, // WHERE clause (null = all rows)
                        null, // WHERE arguments
                        null, // GROUP BY clause
                        null, // HAVING clause
                        null) // ORDER BY clause
        );
        try
        {
            sc.moveToFirst();

            while(!sc.isAfterLast())
            {
                instance = sc.getSettings(); // Sets the settings from DB to Settings obj class field
                sc.moveToNext();

            }
        }
        finally
        {
            sc.close(); // This is needed, or your app will "leak" certain resources.
        }
    }



    // =============================================================================================
    // Setters and Getters - used throughout the entire program to retrieve Settings values
    // Created by simply using Alt + Insert.
    // =============================================================================================

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    public void setInitialMoney(int initialMoney) {
        this.initialMoney = initialMoney;
    }

    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public int getShopSize() {
        return shopSize;
    }

    public void setShopSize(int shopSize) {
        this.shopSize = shopSize;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(int serviceCost) {
        this.serviceCost = serviceCost;
    }

    public int getHouseBuildingCost() {
        return houseBuildingCost;
    }

    public void setHouseBuildingCost(int houseBuildingCost) {
        this.houseBuildingCost = houseBuildingCost;
    }

    public int getCommBuildingCost() {
        return commBuildingCost;
    }

    public void setCommBuildingCost(int commBuildingCost) {
        this.commBuildingCost = commBuildingCost;
    }

    public int getRoadBuildingCost() {
        return roadBuildingCost;
    }

    public void setRoadBuildingCost(int roadBuildingCost) {
        this.roadBuildingCost = roadBuildingCost;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
