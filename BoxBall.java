import java.awt.*;
import java.awt.geom.*;

/**
 * Class BoxBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2016.02.29
 */

public class BoxBall
{
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private final int topPosition;
    private final int leftPosition;
    private final int rightPosition;
    private Canvas canvas;
    private int xSpeed;
    private int ySpeed;                // initial downward speed

    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int groundPos, int topPos, int leftPos, int rightPos,
                        int xSpeed, int ySpeed,
                        Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        topPosition = topPos;
        leftPosition = leftPos;
        rightPosition = rightPos;
        canvas = drawingCanvas;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        xPosition += xSpeed;
        yPosition += ySpeed;

        // if hits the ground
        if (yPosition >= (groundPosition - diameter) && ySpeed > 0) {
            yPosition = (int)(groundPosition - diameter);
            ySpeed = -ySpeed; 
        }
        
        // if hits the ceiling
        if (yPosition <= (topPosition) && ySpeed < 0) {
            yPosition = (int)(topPosition);
            ySpeed = -ySpeed;
        }
        
        // if hits the left wall
        if (xPosition <= (leftPosition) && xSpeed < 0) {
            xPosition = (int)(leftPosition);
            xSpeed = -xSpeed; 
        }
        
        // if hits the right wall
        if (xPosition >= (rightPosition - diameter) && xSpeed > 0) {
            xPosition = (int)(rightPosition - diameter);
            xSpeed = -xSpeed; 
        }
        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
