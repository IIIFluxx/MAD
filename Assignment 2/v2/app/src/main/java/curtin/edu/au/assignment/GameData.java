package curtin.edu.au.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import curtin.edu.au.assignment.DatabaseSchema.MapElementTable;
import curtin.edu.au.assignment.DatabaseSchema.GameDataTable;

import java.util.Random;

/**
 * Represents the overall map, and contains a grid of MapElement objects (accessible using the
 * get(row, col) method). The two static constants WIDTH and HEIGHT indicate the size of the map.
 *
 * There is a static get() method to be used to obtain an instance (rather than calling the
 * constructor directly).
 *
 * There is also a regenerate() method. The map is randomly-generated, and this method will invoke
 * the algorithm again to replace all the map data with a new randomly-generated grid.
 */

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Singleton that stores information specific to this Game. Also contains relevant
 *           Database methods for resume functionality
 * REFERENCE : This code is based off MAD Practical 03,
 *             especially the generateGrid() and choose() methods.
 */


public class GameData
{
    public static int WIDTH = 30;
    public static int HEIGHT = 10;

    private static final int WATER = R.drawable.ic_water;
    private static final int[] GRASS = {R.drawable.ic_grass1, R.drawable.ic_grass2,
            R.drawable.ic_grass3, R.drawable.ic_grass4};

    private static final Random rng = new Random();
    private static GameData gd;

    //Settings
    private Settings settings;
    // Map
    private static MapElement[][] map;

    // Databases
    private SQLiteDatabase mapDB; //Database
    private SQLiteDatabase gameDB; //Database

    // Database cursors
    private MapElementCursor mc;
    private GameDataCursor gc;


    //Game statistics classfields
    private int time;
    private int money;
    private int population;
    private double empRate;
    private int nCommercial;
    private int nResidential;
    private int nRoad;
    private double income;
    private int mode;

    private static GameData instance = null; // Singleton

    public static GameData get()
    {
        if(instance == null)
        {
            instance = new GameData(generateGrid());
        }
        return instance;
    }

    /** GameData methods */

    // Default Constructor
    protected GameData(MapElement[][] grid) 
	{
        this.map = grid;
    }



    // Reset all GameData values and then remake the map also
    public void reset() {
        this.map = generateGrid();
        this.money = 0;
        this.time = 0;
        this.population = 0;
        this.empRate = 0.0;
        this.nRoad = 0;
        this.nCommercial = 0;
        this.nResidential = 0;
        this.income = 0;
        this.mode = 0;
    }

    public MapElement get(int row, int col)
    {
        return map[row][col];
    }


    /** old MapData methods */


    private static MapElement[][] generateGrid()
    {
        final int HEIGHT_RANGE = 256;
        final int WATER_LEVEL = 112;
        final int INLAND_BIAS = 24;
        final int AREA_SIZE = 1;
        final int SMOOTHING_ITERATIONS = 2;
        int meID = 0;
        int[][] heightField = new int[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++)
        {
            for(int j = 0; j < WIDTH; j++)
            {
                heightField[i][j] =
                    rng.nextInt(HEIGHT_RANGE)
                    + INLAND_BIAS * (
                        Math.min(Math.min(i, j), Math.min(HEIGHT - i - 1, WIDTH - j - 1)) -
                        Math.min(HEIGHT, WIDTH) / 4);
            }
        }

        int[][] newHf = new int[HEIGHT][WIDTH];
        for(int s = 0; s < SMOOTHING_ITERATIONS; s++)
        {
            for(int i = 0; i < HEIGHT; i++)
            {
                for(int j = 0; j < WIDTH; j++)
                {
                    int areaSize = 0;
                    int heightSum = 0;

                    for(int areaI = Math.max(0, i - AREA_SIZE);
                            areaI < Math.min(HEIGHT, i + AREA_SIZE + 1);
                            areaI++)
                    {
                        for(int areaJ = Math.max(0, j - AREA_SIZE);
                                areaJ < Math.min(WIDTH, j + AREA_SIZE + 1);
                                areaJ++)
                        {
                            areaSize++;
                            heightSum += heightField[areaI][areaJ];
                        }
                    }

                    newHf[i][j] = heightSum / areaSize;
                }
            }

            int[][] tmpHf = heightField;
            heightField = newHf;
            newHf = tmpHf;
        }

        MapElement[][] grid = new MapElement[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++)
        {
            for(int j = 0; j < WIDTH; j++)
            {

                if(heightField[i][j] >= WATER_LEVEL)
                {
                    boolean waterN = (i == 0)          || (heightField[i - 1][j] < WATER_LEVEL);
                    boolean waterE = (j == WIDTH - 1)  || (heightField[i][j + 1] < WATER_LEVEL);
                    boolean waterS = (i == HEIGHT - 1) || (heightField[i + 1][j] < WATER_LEVEL);
                    boolean waterW = (j == 0)          || (heightField[i][j - 1] < WATER_LEVEL);

                    boolean waterNW = (i == 0) ||          (j == 0) ||         (heightField[i - 1][j - 1] < WATER_LEVEL);
                    boolean waterNE = (i == 0) ||          (j == WIDTH - 1) || (heightField[i - 1][j + 1] < WATER_LEVEL);
                    boolean waterSW = (i == HEIGHT - 1) || (j == 0) ||         (heightField[i + 1][j - 1] < WATER_LEVEL);
                    boolean waterSE = (i == HEIGHT - 1) || (j == WIDTH - 1) || (heightField[i + 1][j + 1] < WATER_LEVEL);

                    boolean coast = waterN || waterE || waterS || waterW ||
                                    waterNW || waterNE || waterSW || waterSE;

                    grid[i][j] = new MapElement(meID, i, j,
                        !coast,
                        choose(waterN, waterW, waterNW,
                            R.drawable.ic_coast_north, R.drawable.ic_coast_west,
                            R.drawable.ic_coast_northwest, R.drawable.ic_coast_northwest_concave),
                        choose(waterN, waterE, waterNE,
                            R.drawable.ic_coast_north, R.drawable.ic_coast_east,
                            R.drawable.ic_coast_northeast, R.drawable.ic_coast_northeast_concave),
                        choose(waterS, waterW, waterSW,
                            R.drawable.ic_coast_south, R.drawable.ic_coast_west,
                            R.drawable.ic_coast_southwest, R.drawable.ic_coast_southwest_concave),
                        choose(waterS, waterE, waterSE,
                            R.drawable.ic_coast_south, R.drawable.ic_coast_east,
                            R.drawable.ic_coast_southeast, R.drawable.ic_coast_southeast_concave),
                        null, null);
                    meID++; // ID that keeps track of a ME (used for DB)
                }
                else
                {
                    grid[i][j] = new MapElement(meID, i,j,false, WATER, WATER, WATER, WATER, null, null);
                    meID++;
                }
            }
        }
        return grid;
    }

	

    private static int choose(boolean nsWater, boolean ewWater, boolean diagWater,
        int nsCoastId, int ewCoastId, int convexCoastId, int concaveCoastId)
    {
        int id;
        if(nsWater)
        {
            if(ewWater)
            {
                id = convexCoastId;
            }
            else
            {
                id = nsCoastId;
            }
        }
        else
        {
            if(ewWater)
            {
                id = ewCoastId;
            }
            else if(diagWater)
            {
                id = concaveCoastId;
            }
            else
            {
                id = GRASS[rng.nextInt(GRASS.length)];
            }
        }
        return id;
    }
	
	
	

    /** Game behaviour methods */
    public boolean checkAdj(int row, int col)
    {
        boolean adjacentCheck1 = false;
        boolean adjacentCheck2 = false;
        boolean adjacentCheck3 = false;
        boolean adjacentCheck4 = false;

        /*
        We want to check if ANY tile that is adjacent to the one we clicked on (indicated by the
         row/col). Therefore we check four things
         1. Look for Bldg to the left of our tile -- col - 1
         2. Look for Bldg to the right of our tile -- col + 1
         3. Look for Bldg beneath our tile -- row - 1
         4. Look for Bldg above our tile -- row + 1
         */

        if(map[row][col-1].getStructure() != null){
            if (get(row,col-1).getStructure() instanceof Road)
            {
                adjacentCheck1 = true;
            }
        }

        if(map[row][col+1].getStructure() != null){
            if (get(row,col+1).getStructure() instanceof Road)
            {
                adjacentCheck2 = true;
            }
        }

        if(map[row - 1][col].getStructure() != null){
            if (get(row-1,col).getStructure() instanceof Road)
            {
                adjacentCheck3 = true;
            }
        }

        if(map[row + 1][col].getStructure() != null){
            if (get(row+1,col).getStructure() instanceof Road)
            {
                adjacentCheck4 = true;
            }
        }

        return(adjacentCheck1 || adjacentCheck2 || adjacentCheck3 || adjacentCheck4);
    }

    public void tallyUp(Structure s)
    {
        if(s instanceof Road)
        {
            nRoad++;
        }
        else if(s instanceof Commercial)
        {
            nCommercial++;
        }
        else if(s instanceof Residential)
        {
            nResidential++;
        }
    }


    public void tallyDown(Structure s)
    {
        if(s instanceof Road)
        {
            nRoad--;
        }
        else if(s instanceof Commercial)
        {
            nCommercial--;
        }
        else if(s instanceof Residential)
        {
            nResidential--;
        }
    }


    // Method is run when the clock icon is clicked on in the Taskbar Fragment.
    // Steps through one iteration of the simulation

    public void step()
    {
        this.time++;
        population = settings.getFamilySize() * nResidential;
        // Formula: employmentRate = min(1, nCommercial * shopSize / population); note: real division.
        empRate = Math.min(1, (double) nCommercial * (double) settings.getShopSize() / (double) population);
        // the player will gain or lose money according to the following formula:
        income = population * (empRate * settings.getSalary() * settings.getTaxRate() - (double) settings.getServiceCost());
        // During each time unit, when population > 0
        if(population > 0)
        {
            //money += (int)income;
            money += income;
        }
    }



    /** General database methods -- may delete */

    // === Load Map DB + load GameData DB ===
    public void loadDatabases(Context c)
    {
        loadMapElement(c); // Loads the MapElement DB
        loadGameData(c); // Loads the GameData DB
    }

    public void deleteDatabases()
    {
        deleteMEDB(); // Delete ME DB (used in SettingsActivity to reset DB's)
        deleteGDDB(); // Delete GD DB (used in SettingsActivity to reset DB's)
    }

    /** Database methods for MapElement Table */

    // addMapElement() method.
    // This is inserting each map element into a Database.

    public void addMapElement(MapElement me) /** ADDS information to the DATABASE. */
    {
        ContentValues cv = new ContentValues();
        String type = "";
        cv.put(DatabaseSchema.MapElementTable.Cols.KEYID, me.getKeyID());
        cv.put(DatabaseSchema.MapElementTable.Cols.ROW, me.getRow());
        cv.put(DatabaseSchema.MapElementTable.Cols.COL, me.getCol());
        cv.put(DatabaseSchema.MapElementTable.Cols.BUILDABLE, me.getBuildable()); // Integer indicating true or false
        cv.put(DatabaseSchema.MapElementTable.Cols.NW, me.getNorthWest());
        cv.put(DatabaseSchema.MapElementTable.Cols.NE,me.getNorthEast());
        cv.put(DatabaseSchema.MapElementTable.Cols.SW,me.getSouthWest());
        cv.put(DatabaseSchema.MapElementTable.Cols.SE,me.getSouthEast());
        // Deal with the structure now.

        Structure struct = me.getStructure();

        if(struct != null) // IF the current ME has a structure on it -- struct = classfield of 'me'
        {
            if(struct instanceof Road)
            {
                type = "road";
            }
            else if(struct instanceof Commercial)
            {
                type = "commercial";
            }
            else if(struct instanceof Residential)
            {
                type = "residential";
            }
            cv.put(MapElementTable.Cols.STRUCTTYPE, type);
            cv.put(MapElementTable.Cols.STRUCTID, struct.getDrawableId());
            if(me.getOwnerName() != null) {
                if (me.getOwnerName().equals("")) {
                    cv.put(MapElementTable.Cols.LABEL, type); // By default - set it to its type e.g. "Road".
                } else {
                    cv.put(MapElementTable.Cols.LABEL, me.getOwnerName()); // If not null i.e. changed - keep its name.
                }
            }
        }
        else // IF NO structure exists at MapElement -- when we deal with grass/coast.
        {
            cv.put(MapElementTable.Cols.STRUCTTYPE, ""); // Type - Road/Comm/Resi
            cv.put(MapElementTable.Cols.STRUCTID, 0); // Drawable ID = 0
            cv.put(MapElementTable.Cols.LABEL, ""); // Label
        }

        mapDB.insert(MapElementTable.NAME, null, cv);
        // The database is now filled with <<MapElements>>

    }

    public void saveEveryElement()
    {
        if(dbExists())
        {
            for (int ll = 0; ll < HEIGHT; ll++)
            {
                for (int kk = 0; kk < WIDTH; kk++)
                {
                    addMapElement(map[ll][kk]);
                }
            }
        }
    }

    public boolean dbExists()
    {
        return mc.getCount() < 1;
    }


    // updateMapElement() method.
    public void updateMapElement(MapElement me)
    {
        ContentValues cv = new ContentValues();
        String type = "";
        cv.put(DatabaseSchema.MapElementTable.Cols.KEYID, me.getKeyID());
        cv.put(DatabaseSchema.MapElementTable.Cols.ROW, me.getRow());
        cv.put(DatabaseSchema.MapElementTable.Cols.COL, me.getCol());
        cv.put(DatabaseSchema.MapElementTable.Cols.BUILDABLE, me.getBuildable());
        cv.put(DatabaseSchema.MapElementTable.Cols.NW, me.getNorthWest());
        cv.put(DatabaseSchema.MapElementTable.Cols.NE,me.getNorthEast());
        cv.put(DatabaseSchema.MapElementTable.Cols.SW,me.getSouthWest());
        cv.put(DatabaseSchema.MapElementTable.Cols.SE,me.getSouthEast());
        // Now deal with the Structure.

        Structure struct = me.getStructure();
        if(struct != null) // IF the current ME has a structure on it -- struct = classfield of ME
        {
            if(struct instanceof Road)
            {
                type = "road";
            }
            else if(struct instanceof Commercial)
            {
                type = "commercial";
            }
            else if(struct instanceof Residential)
            {
                type = "residential";
            }
            cv.put(MapElementTable.Cols.STRUCTTYPE, type);
            cv.put(MapElementTable.Cols.STRUCTID, struct.getDrawableId());
            if(me.getOwnerName() != null) {
                if (me.getOwnerName().equals("")) {
                    cv.put(MapElementTable.Cols.LABEL, type); // By default - set it to its type e.g. "Road".
                } else {
                    cv.put(MapElementTable.Cols.LABEL, me.getOwnerName()); // If not null i.e. changed - keep its name.
                }
            }
        }
        else // IF NO structure exists at MapElement
        {
            cv.put(DatabaseSchema.MapElementTable.Cols.STRUCTTYPE, ""); // Type - Road/Comm/Resi
            cv.put(DatabaseSchema.MapElementTable.Cols.STRUCTID, 0); // Drawable ID = 0
            cv.put(DatabaseSchema.MapElementTable.Cols.LABEL, ""); // Label
        }
        String[] whereValue = { String.valueOf(me.getKeyID()) };
        mapDB.update(MapElementTable.NAME, cv, MapElementTable.Cols.KEYID + " = ?",
                whereValue);
    }


    // loadMapElement() method.
    public void loadMapElement(Context c) /** RELOADS DB state by going thru DB and loading. */
    {
        int w = 0;
        int h = 0;

        //width = settings.getMapWidth();
        //height = settings.getMapHeight();
        mapDB = new MapElementDBHelper(c.getApplicationContext()).getWritableDatabase();

        mc = new MapElementCursor(
                mapDB.query(DatabaseSchema.MapElementTable.NAME, // FROM clause for our table
                        null, // SELECT all columns
                        null, // WHERE clause (null = all rows)
                        null, // WHERE arguments
                        null, // GROUP BY clause
                        null, // HAVING clause
                        null) // ORDER BY clause
        );

        try // From Lecture Slides
        {
            mc.moveToFirst();
            while (!mc.isAfterLast())
            {
                if(w > WIDTH - 1)
                {
                    h++;
                    w = 0;
                }

                map[h][w] = mc.getME();

                w++;
                mc.moveToNext();
            }
        }
        finally
        {
            mc.close();
        }
    }


    public void deleteMEDB()
    {
        // SELECT *
        // FROM: MapElementTable
        // WHERE: <blank> -- we want to completely delete the table.
        mapDB.delete(MapElementTable.NAME, null, null);
    }


    /** Database methods for GameData Table */


    // addGameData() method.
    // This is inserting each piece of game data into a Database.

    public void addGameData(GameData temp) /** ADDS information to the DATABASE. */
    {
        ContentValues cv = new ContentValues();
        cv.put(GameDataTable.Cols.ID, 1);
        cv.put(GameDataTable.Cols.MONEY, temp.getMoney());
        cv.put(GameDataTable.Cols.TIME, temp.getTime());
        cv.put(GameDataTable.Cols.POPULATION, temp.getPopulation());
        cv.put(GameDataTable.Cols.EMPRATE, temp.getEmpRate());
        cv.put(GameDataTable.Cols.NROAD, temp.getnRoad());
        cv.put(GameDataTable.Cols.NCOMM, temp.getnCommercial());
        cv.put(GameDataTable.Cols.NRES, temp.getnResidential());
        cv.put(GameDataTable.Cols.INCOME, temp.getIncome());
        cv.put(GameDataTable.Cols.MODE, temp.getMode());
        gameDB.insert(GameDataTable.NAME, null, cv);
        // The database is now filled with <<GameData information>>
    }

    // updateGameData() method.
    public void updateGameData(GameData temp)
    {
        ContentValues cv = new ContentValues();
        cv.put(GameDataTable.Cols.ID, 1);
        cv.put(GameDataTable.Cols.MONEY, temp.getMoney());
        cv.put(GameDataTable.Cols.TIME, temp.getTime());
        cv.put(GameDataTable.Cols.POPULATION, temp.getPopulation());
        cv.put(GameDataTable.Cols.EMPRATE, temp.getEmpRate());
        cv.put(GameDataTable.Cols.NROAD, temp.getnRoad());
        cv.put(GameDataTable.Cols.NCOMM, temp.getnCommercial());
        cv.put(GameDataTable.Cols.NRES, temp.getnResidential());
        cv.put(GameDataTable.Cols.INCOME, temp.getIncome());
        cv.put(GameDataTable.Cols.MODE, temp.getMode());

        String[] whereValue = { String.valueOf(1) };

        gameDB.update(GameDataTable.NAME, cv, GameDataTable.Cols.ID + " = ?", whereValue);
        // Only one row but putting ID for the sake of updating.
    }

    // loadGameData() method.
    public void loadGameData(Context c) /** RELOADS DB state by going thru DB and loading. */
    {
        gameDB = new GameDataDBHelper(c.getApplicationContext()).getWritableDatabase();

        gc = new GameDataCursor(
                gameDB.query(DatabaseSchema.GameDataTable.NAME, // FROM clause for our table
                        null, // SELECT all columns
                        null, // WHERE clause (null = all rows)
                        null, // WHERE arguments
                        null, // GROUP BY clause
                        null, // HAVING clause
                        null) // ORDER BY clause
        );

        try
        {
            gc.moveToFirst();
            while(!gc.isAfterLast())
            {
                Number[] array = gc.getGameData();
                // Retrieve values from DB (GD classfields).
                this.money = array[0].intValue();
                this.time = array[1].intValue();
                this.population = array[2].intValue();
                this.empRate = array[3].doubleValue();
                this.nRoad = array[4].intValue();
                this.nCommercial = array[5].intValue();
                this.nResidential = array[6].intValue();
                this.income = array[7].doubleValue();
                this.mode = array[8].intValue();
                gc.moveToNext();

            }
        }
        finally
        {
            gc.close();
        }
    }


    public void deleteGDDB()
    {
        // SELECT *
        // FROM: GameDataTable
        // WHERE: <blank> -- we want to completely delete the table.
        gameDB.delete(GameDataTable.NAME, null, null);
    }


    // ===================
    /** Getters and Setters -- created using Alt + Insert */

    public MapElement[][] getMap() {
        return map;
    }

    public void setMap(MapElement[][] map) {
        this.map = map;
    }

    public Settings getSettings() {
        return settings;
    }


    public void setSettings(Settings settings)
    {
        setMoney(settings.getInitialMoney());
        this.settings = settings;
    }
	
	public static void setWidth(int w)
    {
        WIDTH = w;
    }

    public static void setHeight(int h)
    {
        HEIGHT = h;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getEmpRate() {
        return empRate;
    }

    public void setEmpRate(int empRate) {
        this.empRate = empRate;
    }

    public int getnCommercial() {
        return nCommercial;
    }

    public void setnCommercial(int nCommercial) {
        this.nCommercial = nCommercial;
    }

    public int getnResidential() {
        return nResidential;
    }

    public void setnResidential(int nResidential) {
        this.nResidential = nResidential;
    }

    public int getnRoad() {
        return nRoad;
    }

    public void setnRoad(int nRoad) {
        this.nRoad = nRoad;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}