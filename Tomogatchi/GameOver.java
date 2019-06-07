package Tomogatchi;
import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GImage;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GImage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;



public class GameOver extends GCompound {
   /*  private static GImage gameOver;
    private static String gameOverString;
    private static final String GAMEOVER1="Tomogatchi/gameOver1.png";
    private static final String GAMEOVER2="Tomogatchi/gameOver2.png";

    private static final String

    private String [] GameOverImages = {GAMEOVER1, GAMEOVER2};

    */

    public static final int LOOPS = 0;
    public static final int REMOVES_ITSELF_WHEN_COMPLETE = 1;

    private String frameDir;
    private GImage frames[];
    private int frameDelays[];
    private boolean framesLoaded;
    private int mode;

    private javax.swing.Timer timer;
    private int currentFrame;
    private int millisecondsToNextFrame;


    public void loadFrames() {
        File dir = new File(frameDir);				// Get the directory
        File[] directoryListing = dir.listFiles();	// Get the list of frame files
        if (directoryListing != null) {				// If the directory isn't empty
            frameDelays = new int[directoryListing.length];	// Frame delays
            frames = new GImage[directoryListing.length];	// Frame images
            for (int i = 0; i < directoryListing.length; i++) {
                // Setting up the delays and images for each animation frame
                File file = directoryListing[i];
                int frameNum = Integer.parseInt(file.getName().split("_")[1]);
                frameDelays[frameNum] = (int) (1000.0 * Double.parseDouble(
                        file.getName().split("-")[1].split("s")[0]));
                frames[frameNum] = new GImage(frameDir + "//" + file.getName());
            }
            framesLoaded = true;
        }
    }

    // This method return whether or not the animation frames have been loaded
    public boolean framesLoaded() {
        return framesLoaded;
    }

    // The constructor takes a directory and an int representing whether the
    // animation should loop (either LOOPS or REMOVES_ITSELF_WHEN_DONE)
    public GameOver(String frameDir, int mode) {
        this.frameDir = frameDir;
        this.mode = mode;

        if (! framesLoaded) {
            loadFrames();
        }

        // Sets up an internal timer object for the animation with a delay of 1
        // and an action listener to control the progression of the frames
        timer = new javax.swing.Timer(1,new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                if ((millisecondsToNextFrame == 0)	// If it's time for next frame
                        && (currentFrame < frames.length - 1)) {	// And there is another
                    // Get the delay until the next frame
                    millisecondsToNextFrame = frameDelays[currentFrame];
                    currentFrame++;
                    // Clear the compound
                    GameOver.this.removeAll();
                    // Add the next frame to the center of the compound
                    frames[currentFrame].setLocation(
                            -frames[currentFrame].getWidth()/2,
                            -frames[currentFrame].getHeight()/2);
                    GameOver.this.add(frames[currentFrame]);
                }
                // If it's not time for the next frame but there is another frame
                else if (currentFrame < frames.length - 1) {
                    // Countdown to the next frame
                    millisecondsToNextFrame--;
                }
                // If it's time for the next frame but there isn't another one
                else {
                    switch (mode) {
                        // Loop to the beginning of the animation if applicable
                        case LOOPS :
                            currentFrame = 0;
                            break;
                        // Otherwise remove this animation object from the parent canvas
                        case REMOVES_ITSELF_WHEN_COMPLETE :
                            GCanvas c = ((GCanvas) (GameOver.this.getParent()));
                            c.remove(GameOver.this);
                            GameOver.this.timer.stop();
                            break;
                    }
                }
            }});
        // Don't forget to start this timer!
        timer.start();
    }







    /*
    public GameOver() {
        start();
    }

    public void TimetoCry() {
        GCanvas canvas = (GCanvas) this.getParent();
        gameOverString = GameOverImages[new Random().nextInt(GameOverImages.length)];
        gameOver = new GImage(gameOverString);
        gameOver.setSize(2000,2000);
        canvas.add(gameOver);

    }

    public Timer timerAnimation = new Timer();
    TimerTask taskAnimation = new TimerTask() {
        public void run () {
            TimetoCry();
        }
    };
    public void start() {
        timerAnimation.scheduleAtFixedRate(taskAnimation,1000,1000);

    }


*/





}
