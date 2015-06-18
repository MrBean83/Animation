/**
 * Created by Henry Nichols on 4/28/15.
 */
/******************************************************
 *
 * Program Name - BounceZ
 *
 ******************************************************/
import java.awt.*;				// for GUI
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;		// for events
import javax.swing.*;			// for GUI

public class BounceZ extends JFrame implements Runnable, MouseListener
{
    // Create three bouncing ball objects
    public ObjectZ b1, b2, b3;

    // Create three threads for each object
    Thread t1, t2, t3;

    // JFrame size
    int frameWidth  = 300;
    int frameHeight = 400;

    // Declare the variable for your content pane
    Container content;

    // Create an array of different colors for the program to toggle between
    Color colors[] = { Color.red, Color.cyan, Color.pink, Color.orange, Color.lightGray };

    // Create integer variable to use as an index to toggle between colors in array
    int colorSwitch  = 0;

    // Declare a BufferedImage variable (for the third ObjectZ instance) with a default value
    BufferedImage img = null;

    // Create boolean variable to store status of mouseExited events
    public boolean bouncing = true;

    /*********************************************
     *                Constructor
     *********************************************/
    public BounceZ()
    {
        setTitle("Animation");
        setSize(frameWidth, frameHeight);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** *************************
         *	Center JFrame
         ****************************/
        Toolkit tk  = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation((d.width - frameWidth)/2, (d.height - frameHeight)/2);

        /** *************************
         *	Create content Container
         ****************************/
        content = getContentPane();

        // Register mouse
        addMouseListener(this);

        // Declare and start three Threads in the JFrame
        t1 = new Thread(this);
        t1.start();

        t2 = new Thread(this);
        t2.start();

        t3 = new Thread(this);
        t3.start();

        // Declare and start the Thread for each ObjectZ
        b1 = new ObjectZ(10, 32, frameWidth, frameHeight);
        b1.start();

        b2 = new ObjectZ(50, 125, frameWidth, frameHeight);
        b2.start();

        b3 = new ObjectZ(135, 18, frameWidth, frameHeight);
        b3.start();

    } // End constructor

    /** *************************
     *	Program Option #6
     ****************************/
    // Implement the mouseClicked event to change the coordinates for the first ObjectZ instance's 'x' and 'y' variables
    public void mouseClicked(MouseEvent e)
    {
        b1.setX(e.getX());
        b1.setY(e.getY());
    }

    /** *************************
     *	Program Option #4
     ****************************/
    // Implement the mousePressed event to increment the third ObjectZ instance's 'x_increment' and 'y_increment' variables
    public void mousePressed(MouseEvent e)
    {
        b3.setX_increment(b3.getX_increment() + 5);
        b3.setY_increment(b3.getY_increment() + 5);
    }

    /** *************************
     *	Program Option #2
     ****************************/
    // Implement the mouseEntered event to increment the class' 'colorSwitch' variable
    public void mouseEntered(MouseEvent e)
    {
        colorSwitch++;
    }

    /** *************************
     *	Program Option #3
     ****************************/
    // Implement the mouseExited event to call the 'pause' and 'restart' methods of the second ObjectZ instance
    public synchronized void mouseExited(MouseEvent e)
    {
        if (bouncing)
        {
            b2.pause();
            bouncing = false;
        }
        else
        {
            b2.restart();
            bouncing = true;
        }

        repaint();
    }

    // Create an empty implementation of the mouseReleased event
    public void mouseReleased(MouseEvent e) { 	}

    // Implement the paint method, which is executed by repaint calls
    public void paint (Graphics g)
    {
        // Initialize the frame's background color to yellow
        g.setColor(Color.yellow);
        g.fillRect(0,0,frameWidth, frameHeight);

        // Set the color (indexed from an array), shape (circle), and coordinates for the first object
        if ((colorSwitch % 5) == 0) g.setColor(colors[0]);
        if ((colorSwitch % 5) == 1) g.setColor(colors[1]);
        if ((colorSwitch % 5) == 2) g.setColor(colors[2]);
        if ((colorSwitch % 5) == 3) g.setColor(colors[3]);
        if ((colorSwitch % 5) == 4) g.setColor(colors[4]);

        g.fillOval(b1.getX(), b1.getY(), b1.getWidth(), b1.getHeight());

        // Set the color(green), shape(arc), and coordinates for the second object
        g.setColor(Color.green);
        g.fillArc(b2.getX(), b2.getY(), b2.getWidth(), b2.getHeight(), 225, 300);

        // Set the coordinates and import the image file for the third object
        g.drawImage(img, b3.getX(), b3.getY(), 32, 32, this);

        try {
            img = ImageIO.read(new File("Titans-Sword.jpg"));
        } catch (IOException e) {}

    }  // End paint


    public void run()
    {
        while (true)
        {    // This is an infinite loop
            repaint();

            try { Thread.currentThread().sleep(3); }

            catch (Exception e) {  }

        }  // End of while loop

    }  // End run

    public static void main(String args[])
    {
        BounceZ fl = new BounceZ();
        fl.setVisible(true);
    }
}  // End program BounceZ
