package curtin.edu.au.assignment;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Class stores relevant schema for all three Databases/Tables in this program
 */

public class DatabaseSchema
{
    /**
     * Table #1 - Settings
     * This table stores the game settings and saves the
     * settings that the user can customise.
     */
    public static class SettingsTable
    {
        public static final String NAME = "settings"; // Table name
        public static class Cols
        {
            public static final String MAPWIDTH = "mapWidth";
            public static final String MAPHEIGHT = "mapHeight";
            public static final String INITIALMONEY = "initialMoney"; // How much money the player has from the beginning.
            public static final String CITY = "city"; // String that the user can customise - has no effect in the game.
        }
    }

    /**
     * Table #2 - GameData
     * This table stores the game's relevant data that can change over its lifespan.
     */
    public static class GameDataTable
    {
        public static final String NAME = "gamedata";

        public static class Cols
        {
            public static final String ID = "gameID"; // Only used for update method.
            public static final String MONEY = "money"; // Current game money
            public static final String TIME = "time"; // Time step the game is currently at.
            public static final String POPULATION = "population"; // Number of residents in our city
            public static final String EMPRATE = "empRate";
            public static final String NROAD = "nRoad"; // Number of roads found on the map
            public static final String NCOMM = "nCommercial"; // Number of commercial structures found on the map
            public static final String NRES = "nResidential"; // Number of residential structures found on the map
            public static final String INCOME = "income"; // How much money is generated in the current timestep
            public static final String MODE = "mode"; // Build/Demolish/Info.
        }
    }

    /**
     * Table #3 - MapElement
     * This table stores all map elements present on the map (map is defined by the
     * width and height in the Settings activity (and stored in the Settings Table)
     */
    public static class MapElementTable
    {
        public static final String NAME = "mapelement"; // Table name
        public static class Cols
        {
            // The row/col position of the map element
            public static final String KEYID = "keyID"; // Used to identify a map element for where clause when updating DB
            public static final String ROW = "rows"; // Grid position - row
            public static final String COL = "cols"; // Grid position - col
            // From practical
            public static final String BUILDABLE = "buildable";
            public static final String NW = "northWest";
            public static final String NE = "northEast";
            public static final String SW = "southWest";
            public static final String SE = "southEast";

            // Drawable ID of the Structure
            public static final String STRUCTTYPE = "structType"; // String that indicates type of Struct
            public static final String STRUCTID = "structID"; // Drawable ID integer.
            public static final String LABEL = "label"; // What it's called -- for Details Activity.
        }
    }

}
