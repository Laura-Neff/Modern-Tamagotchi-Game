import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import acm.breadboards.AbstractBreadboard;
import acm.breadboards.BreadboardComboBoxModel;
import acm.graphics.GImage;
import Tomogatchi.*;

import java.io.*;
import java.util.Random;

import javax.sound.sampled.*;

public class FinalTomogatchi extends AbstractBreadboard {

    //top text display
    private JTextArea topTextbox;

    //bottom text display
    private JTextField inputTextbox;

    //bottom pet select
    private final String[] options = {"dog", "parrot", "Stitch", "snail"};
    private final BreadboardComboBoxModel model = new BreadboardComboBoxModel(options); //inspired by: acm.graphics package
    private JComboBox petPicker;
    private JButton petSelect;
    private boolean petSelected;

    //opening scene
    private GImage Gift;

    //current pet
    private Pet myPet;

    private static final String GIFT="src/Tomogatchi/Gift.png";
    private static final String PATHTOYEE = "src/Tomogatchi/Yee.wav";

    private boolean soundLoaded;
    private Clip clip;


    private static final int WELCOME_WIDTH = 500;
    private static final int WELCOME_HEIGHT = 667;

    //private static TamagotchiPicture tama;
    private static TamagotchiFall[] tamaFall;
   // private Random random;
    private JButton start;

    public void run() {
        start = new JButton("Let's go!");
        start.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startGame();
                    }
                });
        add(start, SOUTH);

        GImage background = new GImage("src/Tomogatchi/background1.png");{
            this.add(background);
            this.setLocation(0,0);}

        this.setSize(WELCOME_WIDTH,WELCOME_HEIGHT);

        Random random = new Random();
        tamaFall = new TamagotchiFall[80];
        for (int j = 0; j < 80; j++) {
            tamaFall[j] = new TamagotchiPicture(random.nextInt(400),
                    -50*random.nextInt(100));
            tamaFall[j].setVelocity(0, 1+2 *Math.random());
            this.add(tamaFall[j]);
        }

        this.getTimer().setDelay(50);
        this.getTimer().start();

    }

    public void onTimerTick() {
        for (int j = 0; j <tamaFall.length; j++) {
            tamaFall[j].move();
        }

    }

    private void startGame(){
        //this.getParent().setVisible(false);
        this.getTimer().stop();
        FinalTomogatchi.this.removeAll();
        start.getParent().remove(start);
        runWelcome();
    }

    private void runWelcome() {

        //init new pet
        petSelected = false;

        final int INSTRUCTIONS_LINES = 3;
        final int INSTRUCTIONS_WIDTH = 50;

        //load the sounds used in this project
        try {
            File file = new File(PATHTOYEE);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            soundLoaded = true;
        } catch (UnsupportedAudioFileException e) {
            soundLoaded = false;
            e.printStackTrace();
        } catch (IOException e) { //generated by clip.open()
            soundLoaded = false;
            e.printStackTrace();
        } catch (LineUnavailableException e) { //generated by clip.open()
            soundLoaded = false;
            e.printStackTrace();
        }

        //init window
        this.setTitle("Tamogatchi!");

        //display text box at the top
        topTextbox = new JTextArea(INSTRUCTIONS_LINES, INSTRUCTIONS_WIDTH);
        topTextbox.setLineWrap(true);
        topTextbox.setWrapStyleWord(true);
        topTextbox.setEditable(false);
        this.add(topTextbox, NORTH);

        //pet picker show
        petPicker = new JComboBox(model); //inspired by: acm.graphics package
        add(petPicker, SOUTH);
        petSelect = new JButton("Let's adopt!");
        petSelect.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectPet();
                    }
                });
        add(petSelect, SOUTH);

        //opening scene - gift box
        Gift = new GImage(GIFT);
        Gift.scale(.5, .5);
        Gift.setLocation(190, 50);
        this.add(Gift);
        //Gift.setVisible(true);

        //after calling entire intro scene, set size of window
        this.setSize(800, 800); //important
        topTextbox.setText("What kind of pet would you like to adopt?");
    } //end intro scene with gift box

    private void selectPet(){
        final int INPUT_WIDTH = 25;

        if(!petSelected){ //if we haven't picked a pet yet
            String pickaPet = (String) this.petPicker.getSelectedItem();
            petSelected = true;
            petPicker.getParent().remove(petPicker);
            //bottom text input
            inputTextbox = new JTextField(INPUT_WIDTH);
            remove(Gift);
            add(inputTextbox, SOUTH);
            add(petSelect, SOUTH);
            petSelect.setText("Select");
            switch (pickaPet) {
                case "dog":
                    myPet = new Dog(topTextbox);
                    myPet.appendText("What would you like to name your dog?");
                    //myPet = new Dog();
                    this.add(myPet);
                    break;
                case "parrot":
                    myPet = new Parrot(topTextbox);
                    myPet.appendText("What would you like to name your parrot?");
                    this.add(myPet);
                    break;
                case "Stitch":
                    myPet = new Stitch(topTextbox);
                    myPet.appendText("What would you like to name your Stitch?");
                    this.add(myPet);
                    break;
                default:
                    myPet = new Snail(topTextbox);
                    myPet.appendText("What would you like to name your snail?");
                    this.add(myPet);
                    break;
            }
        } else {
            String input_name = this.inputTextbox.getText();
            myPet.setName(input_name);
            inputTextbox.getParent().remove(inputTextbox);
            petSelect.getParent().remove(petSelect);
            runTamogatchi();
        }
    }

    private void runTamogatchi(){

        //removes the old components from everywhere !important
        getContentPane().repaint();

        //add the selected pet
        remove(Gift);
        this.add(myPet);
        myPet.setVisible(true);
        myPet.start();

        //init buttons at the bottom
        //action buttons
        JButton buttonFeed = new JButton("Feed");
        JButton buttonMedicine = new JButton("Medicine");
        JButton buttonSleep = new JButton("Sleep");
        JButton buttonAffection = new JButton("Affection");
        JButton buttonActivity = new JButton("Activity");
        JButton buttonAbandon = new JButton("Abandon");
        this.add(buttonFeed, SOUTH);
        this.add(buttonMedicine, SOUTH);
        this.add(buttonSleep, SOUTH);
        this.add(buttonAffection, SOUTH);
        this.add(buttonActivity, SOUTH);
        this.add(buttonAbandon, SOUTH);

        //redraw all components in south !important (just use one of the south buttons to getParent)
        buttonFeed.getParent().revalidate();

        //add button listeners
        buttonFeed.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(!myPet.isAlive()){return;}
                        FinalTomogatchi.this.removeAll();
                            ((PetsComeinAllShapesandSizes) myPet).eat();
                            FinalTomogatchi.this.add(myPet);
                }
            }
        );
        buttonActivity.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(!myPet.isAlive()){return;}
                    FinalTomogatchi.this.removeAll();
                    ((PetsComeinAllShapesandSizes) myPet).activity();
                    FinalTomogatchi.this.add(myPet);

                }
            }
        );
        buttonMedicine.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if(!myPet.isAlive()){return;}
                        FinalTomogatchi.this.removeAll();

                        if (myPet instanceof Dog) {
                            ((Dog) myPet).setDogScale(.7,.7);
                            ((Dog) myPet).setDogLocation(350,100);
                        } else if (myPet instanceof Parrot) {
                            ((Parrot) myPet).setParrotScale(.7,.7);
                            ((Parrot) myPet).setParrotLocation(350,100);
                        } else if (myPet instanceof Stitch) {
                            //myPet = new Parrot();
                            ((Stitch) myPet).setStitchScale(.5,.5);
                            ((Stitch) myPet).setStitchLocation(350,100);
                        } else if (myPet instanceof Snail) {
                            ((Snail) myPet).setSnailScale(.5, .5);
                            ((Snail) myPet).setSnailLocation(350, 150);
                        }

                        myPet.cure();
                        FinalTomogatchi.this.add(myPet);

                        Pet.candy.addMouseListener(
                                new MouseListener() {
                                    @Override
                                    public void mousePressed(MouseEvent e) { myPet.giveCandy();}
                                    @Override
                                    public void mouseReleased(MouseEvent e) {}
                                    @Override
                                    public void mouseEntered(MouseEvent e) {}
                                    @Override
                                    public void mouseExited(MouseEvent e) {}
                                    @Override
                                    public void mouseClicked(MouseEvent e) {}
                                }
                        );

                        Pet.virus.addMouseListener(
                                new MouseListener() {
                                    @Override
                                    public void mousePressed(MouseEvent e) { myPet.giveVirus(); }
                                    @Override
                                    public void mouseReleased(MouseEvent e) {}
                                    @Override
                                    public void mouseEntered(MouseEvent e) {}
                                    @Override
                                    public void mouseExited(MouseEvent e) {}
                                    @Override
                                    public void mouseClicked(MouseEvent e) {}
                                }
                        );
                        Pet.Coke.addMouseListener(
                                new MouseListener() {
                                    @Override
                                    public void mousePressed(MouseEvent e) { myPet.giveSoda();}
                                    @Override
                                    public void mouseReleased(MouseEvent e) {}
                                    @Override
                                    public void mouseEntered(MouseEvent e) {}
                                    @Override
                                    public void mouseExited(MouseEvent e) {}
                                    @Override
                                    public void mouseClicked(MouseEvent e) {}
                                }
                        );
                        Pet.Pill.addMouseListener(
                                new MouseListener() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {myPet.givePill();}
                                    @Override
                                    public void mouseClicked(MouseEvent e) {}
                                    @Override
                                    public void mouseReleased(MouseEvent e) {}
                                    @Override
                                    public void mouseEntered(MouseEvent e) {}
                                    @Override
                                    public void mouseExited(MouseEvent e) {}
                                }
                        );
                    }
                } //end actionListener
        ); //end buttonMedicine

        buttonSleep.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if(!myPet.isAlive()){return;}

                    FinalTomogatchi.this.removeAll();
                    myPet.sleep();

                    if (myPet instanceof Dog) {
                        ((Dog) myPet).setDogLocation(230,300);
                        ((Dog) myPet).setDogScale(.4, .4);
                    } else if (myPet instanceof Parrot) {
                        ((Parrot) myPet).setParrotLocation(230, 300);
                        ((Parrot) myPet).setParrotScale(.4, .4);
                    } else if (myPet instanceof Stitch) {
                        ((Stitch) myPet).setStitchLocation(230, 250);
                        ((Stitch) myPet).setStitchScale(.3, .3);
                    } else if (myPet instanceof Snail) {
                        ((Snail) myPet).setSnailLocation(230, 330);
                        ((Snail) myPet).setSnailScale(.3, .3);
                    }

                    FinalTomogatchi.this.add(myPet);
                    myPet.setText("You let your pet sleep, something ya'll CS majors can only dream of!\nOh wait, you can't!");

                    if (myPet.getGender().equals("male")) {
                        myPet.appendText("After his rest, he has renewed energy.");
                    } else {
                        myPet.appendText("After her rest, she has renewed energy.");
                    }
                }
            } //end actionListener
        ); //end buttonSleep

        buttonAffection.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!myPet.isAlive()) { return; }

                    FinalTomogatchi.this.removeAll();
                    myPet.setText("Click and drag your mouse over your pet to give them some affection.");
                    myPet.appendText("The more affection and love you give it, the more happy it becomes.");

                    if (myPet instanceof Dog) {
                        ((Dog) myPet).setDogLocation(300, 300);
                        ((Dog) myPet).setDogScale(1, 1);
                    } else if (myPet instanceof Parrot) {
                        ((Parrot) myPet).setParrotLocation(300, 300);
                        ((Parrot) myPet).setParrotScale(1, 1);
                    } else if (myPet instanceof Stitch) {
                        ((Stitch) myPet).setStitchLocation(300, 300);
                        ((Stitch) myPet).setStitchScale(.5, .5);
                    } else if (myPet instanceof Snail) {
                        ((Snail) myPet).setSnailLocation(300, 300);
                        ((Snail) myPet).setSnailScale(1, 1);
                    }

                    FinalTomogatchi.this.add(myPet);

                    myPet.petImage.addMouseMotionListener(
                            new MouseMotionListener() {
                                @Override
                                public void mouseDragged(MouseEvent e) {
                                    myPet.affection();
                                    if (soundLoaded) {
                                        if (clip.isRunning())
                                            clip.stop();   // Stop the player if it is still running
                                        clip.setFramePosition(0); // rewind to the beginning
                                        clip.start();     // Start playing
                                    }
                                }
                                @Override
                                public void mouseMoved(MouseEvent e) {
                                }
                            }
                    );
                }
            } //end actionListener
        ); //end buttonaffection
        buttonAbandon.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        FinalTomogatchi.this.removeAll();
                        Container bottom = buttonFeed.getParent();
                        bottom.removeAll();
                        topTextbox.getParent().removeAll();
                        bottom.repaint();
                        runWelcome();
                    }
                }
        );
    }
}