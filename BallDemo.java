import java.awt.Color;

/**
 * Class BallDemo - Two short demonstrations showing animation with the 
 * Canvas class.
 *
 * @author Michael Kölling and David J. Barnes
 * @author Michael Biondi
 * @version 2024.10.20
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * Simulate a user-specified number of balls bouncing inside a box
     * 
     * @param balls The number of balls in the simulation
     */
    public void boxBounce(int numOfBalls)
    {
        // positions of the walls
        int ground = 400;
        int top = 100;
        int left = 100;
        int right = 400;

        myCanvas.setVisible(true);

        // draw the walls
        myCanvas.setForegroundColor(Color.BLACK);
        drawBox(ground, top, left, right);

        // create and show the balls
        BoxBall[] balls = new BoxBall[numOfBalls];
        for(BoxBall ball : balls) {
            ball = new BoxBall(150, 150, 16, Color.BLUE,
                                    ground, top, left, right,
                                    myCanvas);
            ball.draw();
        }

        while (true) {
            myCanvas.wait(50);           // small delay
            for(BoxBall ball : balls) {
                ball.move();
            }
        }
    }
    
    /**
     * Draw a rectangle on the canvas
     * 
     * @param ground The y-coord of the bottom line
     * @param top The y-coord of the top line
     * @param left The x-coord of the left line
     * @param right The x-coord of the right line
     */
    private void drawBox(int ground, int top, int left, int right)
    {
        myCanvas.drawLine(left, ground, right, ground); // bottom
        myCanvas.drawLine(right, ground, right, top); // right
        myCanvas.drawLine(left, top, right, top); // top
        myCanvas.drawLine(left, top, left, ground); // left
    }
}
