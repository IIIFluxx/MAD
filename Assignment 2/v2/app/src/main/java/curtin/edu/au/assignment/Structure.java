package curtin.edu.au.assignment;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 5-10-20
 * PURPOSE : Abstract class that represents a building in the game.
 *           Child classes are Road, Commercial and Residential
 */
public abstract class Structure
{
    private final int drawableId;
    private String label;

    public Structure(int drawableId, String label)
    {
        this.drawableId = drawableId;
        this.label = label;
    }


    public int getDrawableId()
    {
        return drawableId;
    }

    public String getLabel()
    {
        return label;
    }
}
