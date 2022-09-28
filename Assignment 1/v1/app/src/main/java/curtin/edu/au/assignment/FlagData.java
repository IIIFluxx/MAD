package curtin.edu.au.assignment;

import androidx.recyclerview.widget.GridLayoutManager;

import java.util.Arrays;
import java.util.List;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Class that stores Flag objects in a List.
 */

public class FlagData
{
    private int cols = 2;
    private int orientation = GridLayoutManager.VERTICAL; // 0 = Hor, 1 = Vert

    public static final int[] DRAWABLES = {
            0, // No Flag
            R.drawable.flag_au, R.drawable.flag_br, R.drawable.flag_ca,
            R.drawable.flag_cn, R.drawable.flag_cz, R.drawable.flag_dk,
            R.drawable.flag_fr, R.drawable.flag_de,
            R.drawable.flag_gb, R.drawable.flag_it, R.drawable.flag_jp,
            R.drawable.flag_mx, R.drawable.flag_pl, R.drawable.flag_ru, R.drawable.flag_es,
            R.drawable.flag_us};

    // All flags in this program -- each index is a Flag object.

    private List<Flag> flagsList = Arrays.asList(new Flag[] {
            new Flag(R.drawable.flag_au,"Australia", 0),
            new Flag(R.drawable.flag_br,"Brazil", 0),
            new Flag(R.drawable.flag_ca,"Canada", 0),
            new Flag(R.drawable.flag_cn,"China", 0),
            new Flag(R.drawable.flag_cz,"Czech Republic", 0),
            new Flag(R.drawable.flag_dk,"Denmark", 0),
            new Flag(R.drawable.flag_fr,"France", 0),
            new Flag(R.drawable.flag_de,"Germany", 0),
            new Flag(R.drawable.flag_gb,"Great Britain", 0),
            new Flag(R.drawable.flag_it,"Italy", 0),
            new Flag(R.drawable.flag_jp,"Japan", 0),
            new Flag(R.drawable.flag_mx,"Mexico", 0),
            new Flag(R.drawable.flag_pl,"Poland", 0),
            new Flag(R.drawable.flag_ru,"Russia", 0),
            new Flag(R.drawable.flag_es,"Spain", 0),
            new Flag(R.drawable.flag_us,"USA", 0),
    });

    private static FlagData instance = null;

    public static FlagData get() // Singleton getter - when called, retrives the instance of this class.
    {
        if(instance == null)
        {
            instance = new FlagData();
        }
        return instance;
    }

    protected FlagData() {}

    public Flag get(int i)
    {
        return flagsList.get(i);
    } // Retrives a Flag object in our FlagData specific to an index

    public int size()
    {
        return flagsList.size();
    } // Returns the total number of flags.

    public void add(Flag f)
    {
        flagsList.add(0, f);
    }

    public void remove(int i)
    {
        flagsList.remove(i);
    }

    public int getCols()
    {
        return cols;
    }

    public void setCols(int cols)
    {
        this.cols = cols;
    }

    public int getOrientation() {
        return orientation;
    } // Gets the current orientation of the RecyclerView

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    } // Sets the current orientation of the RecyclerView
}
