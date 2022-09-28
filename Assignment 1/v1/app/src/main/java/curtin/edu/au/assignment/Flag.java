package curtin.edu.au.assignment;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Object that stores information relevant to a specific flag.
 */

public class Flag
{
    private final int drawableId;
    private String label;
    private int filled; // 0 for

    public Flag(int drawableId, String label, int filled)
    {
        this.drawableId = drawableId;
        this.label = label;
        this.filled = filled;
    }

    public int getDrawableId()
    {
        return drawableId;
    }

    public String getLabel()
    {
        return label;
    }

    public int getFilled() {
        return filled;
    }

    public void setFilled() {
        this.filled = 1;
    }
}

