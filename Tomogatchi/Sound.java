package Tomogatchi;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sound {
    private boolean soundLoaded;
    private Clip clip;
    public String pathToClip;

    public void run() {
        try {
            // Open an audio input stream.
            File file = new File(pathToClip);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);

            // Get a sound clip resource, open audio clip, and
            // load samples from the audio input stream.
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            soundLoaded = true;
        }
        catch (UnsupportedAudioFileException e) {
            soundLoaded = false;
            e.printStackTrace();
        }
        catch (IOException e) {
            soundLoaded = false;
            e.printStackTrace();
        }
        catch (LineUnavailableException e) {
            soundLoaded = false;
            e.printStackTrace();
        }

        if (soundLoaded) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }

    }




}