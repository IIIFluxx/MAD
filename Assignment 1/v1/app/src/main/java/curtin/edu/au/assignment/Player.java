package curtin.edu.au.assignment;

/**
 * AUTHOR : BHARATH SUKESH
 * DATE : 27-9-20
 * PURPOSE : Player class - only keeps track of player points and the target point required to win.
 */

public class Player
{
    private int seedPoints, targetPoint;
    private static Player playerObj = null; // Singleton

    public Player(int seedPoints, int targetPoint) {
        this.seedPoints = seedPoints;
        this.targetPoint = targetPoint;
    }


    public static Player get()
    {
        return playerObj;
    }

    public static boolean setInstance(Player newPlayer)
    {
        playerObj = newPlayer;
        return true;
    }


    public int getSeedPoints() {
        return seedPoints;
    }

    public int getTargetPoint() {
        return targetPoint;
    }

    public void setSeedPoints(int seedPoints)
    {
        this.seedPoints = seedPoints;
    }

    public void setTargetPoint(int targetPoint)
    {
        this.targetPoint = targetPoint;
    }

    public void addPoints(int points)
    {
        this.seedPoints = this.seedPoints + points;
    }

    public void losePoints(int points)
    {
        this.seedPoints = this.seedPoints - points;
    }

}
