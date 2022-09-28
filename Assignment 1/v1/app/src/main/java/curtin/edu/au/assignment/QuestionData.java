package curtin.edu.au.assignment;

import java.util.Arrays;
import java.util.List;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Class that stores Question objects in a List.
 */
public class QuestionData
{

    private int cols = 2;
    private boolean specialAnswered = false;

    // ========= HARD CODING ==============
    /** ==========  Australia  ==========  */
    private List<String> ausAnswers1 = Arrays.asList(new String[] {
            "Sydney", "Melbourne", "Canberra"});

    private List<String> ausAnswers2 = Arrays.asList(new String[] {
            "WA", "NT", "VIC","NSW"});

    private List<String> ausAnswers3 = Arrays.asList(new String[] {
            "Gungartan", "Mount Townsend", "Mount Kosciuszko"});

    private List<String> ausAnswers4 = Arrays.asList(new String[] {
            "Australia", "Oceania", "Asia"});

    private List<String> ausAnswers5 = Arrays.asList(new String[] {
            "Mandarin", "English", "Arabic", "Cantonese"});

    private List<String> ausAnswers6 = Arrays.asList(new String[] {
            "Under 20m", "20-22m", "22-24m", "25m and over"});

    private List<String> ausAnswers7 = Arrays.asList(new String[] {
            "Golden Wattle", "Lily"});

    private List<String> ausAnswers8 = Arrays.asList(new String[] {
            "Kangaroo", "Peacock" , "Ostrich","Emu"});

    private List<String> ausAnswers9 = Arrays.asList(new String[] {
            "Cricket", "Basketball" , "Hockey"});

    private List<String> ausAnswers10 = Arrays.asList(new String[] {
            "Pounds", "Dollars" , "Yen"});

    /** ==========  Brazil  ==========  */
    private List<String> brAnswers1 = Arrays.asList(new String[] {
            "Rio de Janeiro", "Brasilia", "Manaus"});

    private List<String> brAnswers2 = Arrays.asList(new String[] {
            "Para", "Amazonas", "Mata Grosso"});

    private List<String> brAnswers3 = Arrays.asList(new String[] {
            "Pico da Bandeira", "Mount Roraima", "Pico da Neblina"});

    private List<String> brAnswers4 = Arrays.asList(new String[] {
            "Oceania", "North America", "South America"});

    private List<String> brAnswers5 = Arrays.asList(new String[] {
            "Spanish", "English", "Portuguese", "German"});

    private List<String> brAnswers6 = Arrays.asList(new String[] {
            "Futbol(Soccer)", "Basketball" , "Volleyball"});

    /** ========== Canada  ========== */

    private List<String> caAnswers1 = Arrays.asList(new String[] {
            "Toronto", "Montreal", "Ottawa", "Vancouver"});

    private List<String> caAnswers2 = Arrays.asList(new String[] {
            "Nunavut", "Quebec"});

    private List<String> caAnswers3 = Arrays.asList(new String[] {
            "Lucania", "Saint Elias", "Mount Logan"});

    private List<String> caAnswers4 = Arrays.asList(new String[] {
            "Oceania", "Europe", "Asia", "North America"});

    private List<String> caAnswers5 = Arrays.asList(new String[] {
            "French", "English"});

    private List<String> caAnswers6 = Arrays.asList(new String[] {
            "Under 30m", "30-35m", "35-40m", "40m and over"});

    private List<String> caAnswers7 = Arrays.asList(new String[] {
            "Kingfisher", "Elk" , "Gray Jay","Moose"});


    /** ========== China  ========== */

    private List<String> cnAnswers1 = Arrays.asList(new String[] {
            "Beijing", "Shanghai", "Wuhan", "Shenzen"});

    private List<String> cnAnswers2 = Arrays.asList(new String[] {
            "Xinjiang Uygur Autonomous Region", "Beijing Region"});

    private List<String> cnAnswers3 = Arrays.asList(new String[] {
            "K2", "Everest", "Lhotse"});

    private List<String> cnAnswers4 = Arrays.asList(new String[] {
            "Oceania", "Europe", "Asia", "North America"});

    private List<String> cnAnswers5 = Arrays.asList(new String[] {
            "Cantonese", "Mandarin", "English"});

    private List<String> cnAnswers6 = Arrays.asList(new String[] {
            "Under 1b", "1-1.25b", "1.25-1.5b", "1.5b and over"});


    /**  ========== Czech  ==========  */
    private List<String> czAnswers1 = Arrays.asList(new String[] {
            "Prague", "Brno", "Ostrava", "Pilsen"});

    private List<String> czAnswers2 = Arrays.asList(new String[] {
            "Prague", "Central Bohemian", "South Bohemian"});

    private List<String> czAnswers3 = Arrays.asList(new String[] {
            "Snezka", "Wielki Szyszak", "Praded"});

    private List<String> czAnswers4 = Arrays.asList(new String[] {
            "Oceania", "Europe", "Asia", "North America"});

    private List<String> czAnswers5 = Arrays.asList(new String[] {
            "Czech", "English"});

    /**  ========== Denmark  ==========  */

    private List<String> dkAnswers1 = Arrays.asList(new String[] {
            "Copenhagen", "Aarhus", "Odense", "Ribe"});

    private List<String> dkAnswers2 = Arrays.asList(new String[] {
            "Region Midtjylland", "Region Hovedstaden"});

    private List<String> dkAnswers3 = Arrays.asList(new String[] {
            "Slaettaratindur", "Gunnbjorn Fjeld", "Trehoje"});

    private List<String> dkAnswers4 = Arrays.asList(new String[] {
            "Oceania", "South America", "Asia", "Europe"});

    private List<String> dkAnswers5 = Arrays.asList(new String[] {
            "Norwegian", "English", "Danish", "Swedish"});

    private List<String> dkAnswers6 = Arrays.asList(new String[] {
            "Under 5m", "5-5.25m", "5.25-5.5m", "5.5m and over"});

    /**  ==========  France  ==========  */

    private List<String> frAnswers1 = Arrays.asList(new String[] {
            "Marseille", "Nice", "Lyon", "Paris"});

    private List<String> frAnswers2 = Arrays.asList(new String[] {
            "Ile-de-France", "Auvergne-Rhone-Alpes"});

    private List<String> frAnswers3 = Arrays.asList(new String[] {
            "Aiguille Verte", "Grandes Jorasses", "Meije", "Mont Blanc"});

    private List<String> frAnswers4 = Arrays.asList(new String[] {
            "Europe", "South America", "Asia", "Oceania"});

    private List<String> frAnswers5 = Arrays.asList(new String[] {
            "French", "Francaese", "Dutch"});

    private List<String> frAnswers6 = Arrays.asList(new String[] {
            "Snooker", "Basketball" , "Football(Soccer)"});

    /**  ========== Germany  ==========  */
    private List<String> deAnswers1 = Arrays.asList(new String[] {
            "Frankfurt", "Hamburg", "Berlin", "Leipzig"});

    private List<String> deAnswers2 = Arrays.asList(new String[] {
            "Bavaria", "Brandenburg", "Berlin", "Bremen"});

    private List<String> deAnswers3 = Arrays.asList(new String[] {
            "Zugspitze", "Schneefernerkopf"});

    private List<String> deAnswers4 = Arrays.asList(new String[] {
            "South America", "Europe", "Asia", "Oceania"});

    private List<String> deAnswers5 = Arrays.asList(new String[] {
            "Danish", "Sorbian", "Swedish", "German"});

    /**  ========== Great Britain ========== */

    private List<String> gbAnswers1 = Arrays.asList(new String[] {
            "Birmingham", "Bristol", "London", "Manchester"});

    private List<String> gbAnswers2 = Arrays.asList(new String[] {
            "Yorkshire", "Rutland"});

    private List<String> gbAnswers3 = Arrays.asList(new String[] {
            "Ben Macdui", "Ben Nevis", "Braeriach"});

    private List<String> gbAnswers4 = Arrays.asList(new String[] {
            "South America", "Europe", "Asia", "Oceania"});

    private List<String> gbAnswers5 = Arrays.asList(new String[] {
            "Celtic", "Sorbian", "English", "Polish"});

    private List<String> gbAnswers6 = Arrays.asList(new String[] {
            "Under 50m", "50-60m", "60-70m", "70m and over"});

    private List<String> gbAnswers7 = Arrays.asList(new String[] {
            "Football(Soccer)", "Basketball" , "Cricket"});

    /**  ========== Italy  ==========  */
    private List<String> itAnswers1 = Arrays.asList(new String[] {
        "Rome", "Venice", "Milan", "Turin"});

    private List<String> itAnswers2 = Arrays.asList(new String[] {
        "Rome", "Venice", "Sicily"});

    private List<String> itAnswers3 = Arrays.asList(new String[] {
            "Mont Blanc", "Matterhorn", "Lyskamm"});

    private List<String> itAnswers4 = Arrays.asList(new String[] {
            "Oceania", "South America", "Asia", "Europe"});

    private List<String> itAnswers5 = Arrays.asList(new String[] {
            "Latin", "Italian", "English", "Venetian"});

    /**  ========== Japan  ==========  */
    private List<String> jpAnswers1 = Arrays.asList(new String[] {
            "Tokyo", "Osaka", "Kyoto", "Sapporo"});

    private List<String> jpAnswers2 = Arrays.asList(new String[] {
            "Fukushima", "Iwate", "Hokkaido"});

    private List<String> jpAnswers3 = Arrays.asList(new String[] {
           "Mount Kita", "Mount Fuji"});

    private List<String> jpAnswers4 = Arrays.asList(new String[] {
            "Oceania", "South America", "Asia", "Europe"});

    private List<String> jpAnswers5 = Arrays.asList(new String[] {
            "Japanese", "Mandarin", "English", "Ryukyuan"});

    /**  ========== Mexico  ========== */
    private List<String> mxAnswers1 = Arrays.asList(new String[] {
            "Cancun", "Merida", "Guadalajara", "Mexico City"});

    private List<String> mxAnswers2 = Arrays.asList(new String[] {
            "Coahuila", "Chihuahua", "Guadalajara", "Mexico City"});

    private List<String> mxAnswers3 = Arrays.asList(new String[] {
            "Popocatépetl", "Pico de Orizaba", "Guadalajara Mt"});

    private List<String> mxAnswers4 = Arrays.asList(new String[] {
            "Oceania", "South America", "Asia", "Europe"});

    private List<String> mxAnswers5 = Arrays.asList(new String[] {
            "Nahuatl", "Portuguese", "English", "Spanish"});

    /**  ========== Poland  ==========  */
    private List<String> plAnswers1 = Arrays.asList(new String[] {
            "Warsaw", "Krakow", "Katowice", "Torun"});

    private List<String> plAnswers2 = Arrays.asList(new String[] {
            "Wroclaw", "Warsaw Region"});

    private List<String> plAnswers3 = Arrays.asList(new String[] {
            "Swinica", "Rysy"});

    private List<String> plAnswers4 = Arrays.asList(new String[] {
            "Oceania", "South America", "Asia", "Europe"});

    private List<String> plAnswers5 = Arrays.asList(new String[] {
            "Danish", "English", "Polish", "Swedish"});

    /**  ========== Russia  ==========  */
    private List<String> ruAnswers1 = Arrays.asList(new String[] {
            "Moscow", "Saint Petersburg", "Kazan", "Sochi"});
    private List<String> ruAnswers2 = Arrays.asList(new String[] {
            "Moscow Region", "West Siberian Plain"});
    private List<String> ruAnswers3 = Arrays.asList(new String[] {
            "Mount Elbrus", "Shkhara"});

    private List<String> ruAnswers4 = Arrays.asList(new String[] {
            "Asia", "South America", "Europe", "North America"});

    private List<String> ruAnswers5 = Arrays.asList(new String[] {
            "Danish", "Russian", "Tatar", "Polish"});

    /**  ========== Spain  ==========  */
    private List<String> esAnswers1 = Arrays.asList(new String[] {
            "Seville", "Barcalona", "Madrid", "Valencia"});

    private List<String> esAnswers2 = Arrays.asList(new String[] {
            "Catalonia", "Andalusia", "Valenciago"});

    private List<String> esAnswers3 = Arrays.asList(new String[] {
            "Teide", "Mulhacen", "Aneto"});

    private List<String> esAnswers4 = Arrays.asList(new String[] {
            "Asia", "South America", "Europe", "North America"});

    private List<String> esAnswers5 = Arrays.asList(new String[] {
            "Basque", "Portuguese", "Spanish"});

    /**  ========== USA  ==========  */
    private List<String> usAnswers1 = Arrays.asList(new String[] {
            "New York", "Los Angeles", "Chicago", "Washington D.C"});

    private List<String> usAnswers2 = Arrays.asList(new String[] {
            "California", "Alaska", "Texas", "New Mexico"});

    private List<String> usAnswers3 = Arrays.asList(new String[] {
            "Denali", "Saint Elias"});

    private List<String> usAnswers4 = Arrays.asList(new String[] {
            "Asia", "South America", "Europe", "North America"});

    private List<String> usAnswers5 = Arrays.asList(new String[] {
            "French", "English", "Spanish"});

    private List<String> usAnswers6 = Arrays.asList(new String[] {
            "Bald Eagle", "Peacock" , "Ostrich","Reindeer"});


/*
    private List<String> __AnswersX = Arrays.asList(new String[] {
            "", "", "", ""});

    new Question(R.drawable.flag_XX, "XX Q5", 3, 1, "What is the main language?", XXAnswers5, <pos>, false,0,false),

 */

    // ========= END OF HARD CODING ==============
    //TODO: Configure this with questionList
    private List<Question> questionsList = Arrays.asList(new Question[] {
            /** Australia */ //TODO: fix Points/Penalty per Qn -- Aus points are correct.
            new Question(R.drawable.flag_au, "Aus Q1", 3, 4, "What is the Capital?", ausAnswers1, 3, false, 0,false),
            new Question(R.drawable.flag_au, "Aus Q2", 8, 4, "What is the biggest state?", ausAnswers2, 1, false,0,false),
            new Question(R.drawable.flag_au, "Aus Q3", 6, 2, "What is the largest mountain?", ausAnswers3, 3, false,0,false),
            new Question(R.drawable.flag_au, "Aus Q4", 2, 6, "Which continent is this country in?", ausAnswers4, 2, false,0,false),
            new Question(R.drawable.flag_au, "Aus Q5", 3, 3, "What is the main language?", ausAnswers5, 2, false,0,false),
            new Question(R.drawable.flag_au, "Aus Q6", 4, 2, "What is the population?", ausAnswers6, 4, false,0,false),
            new Question(R.drawable.flag_au, "Aus Q7", 5, 1, "What is the national flower?", ausAnswers7, 1, false,0,false),
            new Question(R.drawable.flag_au, "Aus Q8", 3, 1, "What is the national bird?", ausAnswers8, 4, false,0,false),
            new Question(R.drawable.flag_au, "Aus Q9", 1, 3, "What is the national sport?", ausAnswers9, 1, true,0,false),
            new Question(R.drawable.flag_au, "Aus Q10", 3, 3, "What is the main currency?", ausAnswers10, 2, false,0,false),


            /** Brazil */
            new Question(R.drawable.flag_br, "BR Q1", 3, 4, "What is the Capital?", brAnswers1, 2, false,0,false),
            new Question(R.drawable.flag_br, "BR Q2", 8, 4, "What is the biggest state?", brAnswers2, 2, false,0,false),
            new Question(R.drawable.flag_br, "BR Q3", 6, 2, "What is the largest mountain?", brAnswers3, 3, false,0,false),
            new Question(R.drawable.flag_br, "BR Q4", 2, 6, "Which continent is this country in?", brAnswers4, 3, false,0,false),
            new Question(R.drawable.flag_br, "BR Q5", 3, 3, "What is the main language?", brAnswers5, 3, false,0,false),
            new Question(R.drawable.flag_br, "BR Q6", 1, 3, "What is the national sport?", brAnswers6, 1, true,0,false),

            /** Canada */
            new Question(R.drawable.flag_ca, "CA Q1", 3, 4, "What is the Capital?", caAnswers1, 3, false,0,false),
            new Question(R.drawable.flag_ca, "CA Q2", 8, 4, "What is the biggest state?", caAnswers2, 1, false,0,false),
            new Question(R.drawable.flag_ca, "CA Q3", 6, 2, "What is the largest mountain?", caAnswers3, 3, false,0,false),
            new Question(R.drawable.flag_ca, "CA Q4", 2, 6, "Which continent is this country in?", caAnswers4, 4, false,0,false),
            new Question(R.drawable.flag_ca, "CA Q5", 3, 3, "What is the main language?", caAnswers5, 2, false,0,false),
            new Question(R.drawable.flag_ca, "CA Q6", 4, 2, "What is the population?", caAnswers6, 3, true,0,false),
            new Question(R.drawable.flag_ca, "CA Q7", 3, 1, "What is the national bird?", caAnswers7, 3, false,0,false),

            /** China */
            new Question(R.drawable.flag_cn, "CN Q1", 3, 4, "What is the Capital?", cnAnswers1, 2, false,0,false),
            new Question(R.drawable.flag_cn, "CN Q2", 8, 4, "What is the biggest state?", cnAnswers2, 1, true,0,false),
            new Question(R.drawable.flag_cn, "CN Q3", 6, 5, "What is the largest mountain?", cnAnswers3, 2, false,0,false),
            new Question(R.drawable.flag_cn, "CN Q4", 2, 6, "Which continent is this country in?", cnAnswers4, 3, false,0,false),
            new Question(R.drawable.flag_cn, "CN Q5", 3, 3, "What is the main language?", cnAnswers5, 2, false,0,false),
            new Question(R.drawable.flag_cn, "CN Q6", 4, 2, "What is the population?", cnAnswers6, 3, false,0,false),
            /** Czech */
            new Question(R.drawable.flag_cz, "CZ Q1", 3, 4, "What is the Capital?", czAnswers1, 2, false,0,false),
            new Question(R.drawable.flag_cz, "CZ Q2", 8, 4, "What is the biggest state?", czAnswers2, 1, false,0,false),
            new Question(R.drawable.flag_cz, "CZ Q3", 6, 2, "What is the largest mountain?", czAnswers3, 1, false,0,false),
            new Question(R.drawable.flag_cz, "CZ Q4", 2, 6, "Which continent is this country in?", czAnswers4, 2, false,0,false),
            new Question(R.drawable.flag_cz, "CZ Q5", 3, 3, "What is the main language?", czAnswers5, 1, false,0,false),
            /**  ========== Denmark  ==========  */
            new Question(R.drawable.flag_dk, "DK Q1", 3, 4, "What is the Capital?", dkAnswers1, 1, false,0,false),
            new Question(R.drawable.flag_dk, "DK Q2", 8, 4, "What is the biggest state?", dkAnswers2, 2, false,0,false),
            new Question(R.drawable.flag_dk, "DK Q3", 6, 2, "What is the largest mountain?", dkAnswers3, 2, true,0,false),
            new Question(R.drawable.flag_dk, "DK Q4", 2, 6, "Which continent is this country in?", dkAnswers4, 4, false,0,false),
            new Question(R.drawable.flag_dk, "DK Q5", 3, 3, "What is the main language?", dkAnswers5, 3, false,0,false),
            new Question(R.drawable.flag_dk, "DK Q6", 4, 2, "What is the population?", dkAnswers6, 4, false,0,false),
            /**  ========== France  ==========  */
            new Question(R.drawable.flag_fr, "FR Q1", 3, 4, "What is the Capital?", frAnswers1, 4, false,0,false),
            new Question(R.drawable.flag_fr, "FR Q2", 8, 4, "What is the biggest state?", frAnswers2, 1, false,0,false),
            new Question(R.drawable.flag_fr, "FR Q3", 6, 2, "What is the largest mountain?", frAnswers3, 4, false,0,false),
            new Question(R.drawable.flag_fr, "FR Q4", 2, 6, "Which continent is this country in?", frAnswers4, 1, false,0,false),
            new Question(R.drawable.flag_fr, "FR Q5", 3, 3, "What is the main language?", frAnswers5, 1, false,0,false),
            new Question(R.drawable.flag_fr, "FR Q6", 1, 3, "What is the national sport?", frAnswers6, 3, true,0,false),
            /**  ========== Germany  ==========  */
            new Question(R.drawable.flag_de, "DE Q1", 3, 4, "What is the Capital?", deAnswers1, 3, false,0,false),
            new Question(R.drawable.flag_de, "DE Q2", 8, 4, "What is the biggest state?", deAnswers2, 1, true,0,false),
            new Question(R.drawable.flag_de, "DE Q3", 6, 2, "What is the largest mountain?", deAnswers3, 1, false,0,false),
            new Question(R.drawable.flag_de, "DE Q4", 2, 6, "Which continent is this country in?", deAnswers4, 2, false,0,false),
            new Question(R.drawable.flag_de, "DE Q5", 3, 3, "What is the main language?", deAnswers5, 4, false,0,false),

            /**  ========== Great Britain  ==========  */
            new Question(R.drawable.flag_gb, "GB Q1", 3, 4, "What is the Capital?", gbAnswers1, 3, false,0,false),
            new Question(R.drawable.flag_gb, "GB Q2", 8, 4, "What is the biggest county?", gbAnswers2,1, false,0,false),
            new Question(R.drawable.flag_gb, "GB Q3", 6, 2, "What is the largest mountain?", gbAnswers3, 2, false,0,false),
            new Question(R.drawable.flag_gb, "GB Q4", 2, 6, "Which continent is this country in?", gbAnswers4, 2, false,0,false),
            new Question(R.drawable.flag_gb, "GB Q5", 3, 3, "What is the main language?", gbAnswers5, 3, false,0,false),
            new Question(R.drawable.flag_gb, "GB Q6", 4, 2, "What is the population?", gbAnswers6, 3, false,0,false),
            new Question(R.drawable.flag_gb, "GB Q6", 1, 3, "What is the national sport?", gbAnswers7, 3, false,0,false),
            /**  ========== Italy  ==========  */
            new Question(R.drawable.flag_it, "IT Q1", 3, 4, "What is the Capital?", itAnswers1, 1, false,0,false),
            new Question(R.drawable.flag_it, "IT Q2", 8, 4, "What is the biggest state?", itAnswers2, 3, false,0,false),
            new Question(R.drawable.flag_it, "IT Q3", 6, 2, "What is the largest mountain?", itAnswers3, 1, false,0,false),
            new Question(R.drawable.flag_it, "IT Q4", 2, 6, "Which continent is this country in?", itAnswers4, 4, false,0,false),
            new Question(R.drawable.flag_it, "IT Q5", 3, 3, "What is the main language?", itAnswers5, 2, false,0,false),
            /**  ========== Japan  ==========  */
            new Question(R.drawable.flag_jp, "JP Q1", 3, 4, "What is the Capital?", jpAnswers1, 1, false,0,false),
            new Question(R.drawable.flag_jp, "JP Q2", 8, 4, "What is the biggest prefecture?", jpAnswers2, 3, true,0,false),
            new Question(R.drawable.flag_jp, "JP Q3", 6, 5, "What is the largest mountain?", jpAnswers3, 2, false,0,false),
            new Question(R.drawable.flag_jp, "JP Q4", 2, 6, "Which continent is this country in?", jpAnswers4, 3, false,0,false),
            new Question(R.drawable.flag_jp, "JP Q5", 3, 3, "What is the main language?", jpAnswers5, 1, false,0,false),
            /**  ========== Mexico  ==========  */
            new Question(R.drawable.flag_mx, "MX Q1", 3, 4, "What is the Capital?", mxAnswers1, 4, false,0,false),
            new Question(R.drawable.flag_mx, "MX Q2", 8, 4, "What is the biggest state?", mxAnswers2, 2, true,0,false),
            new Question(R.drawable.flag_mx, "MX Q3", 6, 2, "What is the largest mountain?", mxAnswers3, 2, false,0,false),
            new Question(R.drawable.flag_mx, "MX Q4", 2, 6, "Which continent is this country in?", mxAnswers4, 2, false,0,false),
            new Question(R.drawable.flag_mx, "MX Q5", 3, 3, "What is the main language?", mxAnswers5, 4, false,0,false),
            /**  ========== Poland  ==========  */
            new Question(R.drawable.flag_pl, "PL Q1", 5, 1, "What is the Capital?", plAnswers1, 1, false,0,false),
            new Question(R.drawable.flag_pl, "PL Q2", 8, 4, "What is the biggest region?", plAnswers2, 2, false,0,false),
            new Question(R.drawable.flag_pl, "PL Q3", 6, 2, "What is the largest mountain?", plAnswers3, 2, false,0,false),
            new Question(R.drawable.flag_pl, "PL Q4", 2, 6, "Which continent is this country in?", plAnswers4, 4, false,0,false),
            new Question(R.drawable.flag_pl, "PL Q5", 3, 3, "What is the main language?", plAnswers5, 3, false,0,false),
            /**  ========== Russia  ==========  */
            new Question(R.drawable.flag_ru, "RU Q1", 3, 4, "What is the Capital?", ruAnswers1, 1, false,0,false),
            new Question(R.drawable.flag_ru, "RU Q2", 8, 4, "What is the biggest state?", ruAnswers2, 2, false,0,false),
            new Question(R.drawable.flag_ru, "RU Q3", 6, 2, "What is the largest mountain?", ruAnswers3, 1, false,0,false),
            new Question(R.drawable.flag_ru, "RU Q4", 2, 6, "Which continent is this country (mostly) in?", ruAnswers4, 1, false,0,false),
            new Question(R.drawable.flag_ru, "RU Q5", 3, 3, "What is the main language?", ruAnswers5, 2, false,0,false),
            /**  ========== Spain  ==========  */
            new Question(R.drawable.flag_es, "ES Q1", 3, 4, "What is the Capital?", esAnswers1, 3, false,0,false),
            new Question(R.drawable.flag_es, "ES Q2", 8, 4, "What is the biggest state?", esAnswers2, 2, false,0,false),
            new Question(R.drawable.flag_es, "ES Q3", 6, 2, "What is the largest mountain?", esAnswers3, 1, false,0,false),
            new Question(R.drawable.flag_es, "ES Q4", 2, 6, "Which continent is this country in?", esAnswers4, 3, false,0,false),
            new Question(R.drawable.flag_es, "ES Q5", 3, 3, "What is the main language?", esAnswers5, 4, false,0,false),
            /**  ========== USA  ==========  */
            new Question(R.drawable.flag_us, "US Q1", 3, 4, "What is the Capital?", usAnswers1, 4, false,0,false),
            new Question(R.drawable.flag_us, "US Q2", 8, 4, "What is the biggest state?", usAnswers2, 2, false,0,false),
            new Question(R.drawable.flag_us, "US Q3", 6, 2, "What is the largest mountain?", usAnswers3, 1, false,0,false),
            new Question(R.drawable.flag_us, "US Q4", 2, 6, "Which continent is this country in?", usAnswers4, 4, false,0,false),
            new Question(R.drawable.flag_us, "US Q5", 3, 3, "What is the main language?", usAnswers5, 2, false,0,false),
            new Question(R.drawable.flag_us, "US Q6", 3, 1, "What is the national bird?", usAnswers6, 1, true,0,false),
    });

    // Hard coding over.

    private static QuestionData instance = null;

    public static QuestionData get()
    {
        if(instance == null)
        {
            instance = new QuestionData();
        }
        return instance;
    }

    protected QuestionData() {}

    public Question get(int i)
    {
        return questionsList.get(i);
    }

    public int size()
    {
        return questionsList.size();
    }



    public void add(Question q)
    {
        questionsList.add(0, q);
    }


    public void remove(int i)
    {
        questionsList.remove(i);
    }

    public int getCols()
    {
        return cols;
    }

    public void setCols(int cols)
    {
        this.cols = cols;
    }

    public void setSpecialAnswered(boolean specialAnswered) {
        this.specialAnswered = specialAnswered;
    }

    public boolean getSpecialAnswered() {
        return specialAnswered;
    }

    public List<Question> getQuestionsList() {
        return questionsList;
    }


}
