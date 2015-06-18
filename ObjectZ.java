/**
 * Created by Henry Nichols on 4/28/15.
 */
/******************************************************
 *
 * Program Name - ObjectZ
 *
 *   Assignment options implemented:
 *
 *   -Program requirements (20 points)
 *
 *   -Program option #2 (5 points)
 *
 *   -Program option #3 (5 points)
 *
 *   -Program option #4 (5 points)
 *
 *   -Program option #6 (10 points)
 *
 *   -Program option #7 (10 points)
 *
 *   **Total Points required: 50 points**
 *
 *   **Total Points attempted: 55 points**
 *
 ******************************************************/

import java.awt.Event;
import java.lang.Exception;

public class ObjectZ extends Thread
{
    private int x_increment  = 1;
    private int y_increment  = 1;

    private int frameHeight = 100;
    private int frameWidth  = 100;

    private int xCoord;
    private int yCoord;

    private int objectWidth  = 20;
    private int objectHeight = 20;

    // Create object constant to use in synchronized methods that pause and resume an object
    private final Object LOCK  = new Object();

    // Create boolean variable to store status of mouseExited events
    private boolean paused = false;

    public ObjectZ(int x, int y, int fw, int fh)
    {
        xCoord = x;
        yCoord = y;

        frameWidth  = fw;
        frameHeight = fh;

    } // End constructor ObjectZ


    public void run()
    {
        System.out.println("run is running");
        while (true)  // This is an infinite loop
        {
            xCoord += x_increment;
            yCoord += y_increment;

            if (xCoord + objectWidth  >= frameWidth   || xCoord < 0)
                x_increment *= -1;

            if (yCoord + objectHeight >= frameHeight  || yCoord < 0)
                y_increment *= -1;

            synchronized(LOCK){
                if (paused){
                    try{
                        LOCK.wait();
                    }catch(InterruptedException ie){}
                }

            }
            try {Thread.currentThread().sleep(5);}

            catch (Exception e) {}

        }  // End of while loop

    }  // End run

    // Create getter methods for private variables 'xCoord' and 'yCoord'

    public int getX()  {return xCoord;}

    public int getY()  {return yCoord;}

    public int getHeight() {return objectHeight;}

    public int getWidth()  {return objectWidth;}

    // Create getter methods for private variables 'x_increment' and 'y_increment'

    public int getX_increment() {return x_increment;}

    public int getY_increment() {return y_increment;}

    // Create setter methods for private variables 'xCoord' and 'yCoord'

    public void setX(int x) {xCoord = x;}

    public void setY(int y) {yCoord = y;}

    // Create setter methods for private variables 'x_increment' and 'y_increment'

    public void setX_increment(int inc) {x_increment = inc;}

    public void setY_increment(int inc) {y_increment = inc;}

    public void pause()
    {
        synchronized(LOCK){
            paused = true;
            LOCK.notifyAll();
        }
    }

    public void restart()
    {
        synchronized(LOCK){
            paused = false;
            LOCK.notifyAll();
        }
    }

}  // End program ObjectZ
