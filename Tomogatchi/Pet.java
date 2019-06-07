package Tomogatchi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

import java.awt.Color;
import acm.graphics.GLabel;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Pet extends GCompound  {

    //abstract methods that all pets must implement
    public abstract void setName(String name);
    public abstract void givePill();
    public abstract void giveCandy();
    public abstract void giveSoda();
    public abstract void giveVirus();

    //pet characteristics
    private String gender;
    private boolean alive = true;
    public String name;
    public GImage petImage;
    public String getName(){return this.name;}

    //health stats
    private static final double MAX_ANY = 100;
    private double health = 100;
    private double energy = 100;
    private double happiness = 100;
    private double fun = 100;

    private static final int WARN_LEVEL = 50;
    private static final int CRITICAL_LEVEL = 30;

    private String Insult;
    private String Yikes;

    //health related messages and functions
    private static final String [] Gender = {"male", "female"};

    private static final String [] UnhappyInsults = {"Some Georgia Tech students undermined your pet's tech skills.", "Your pet thought about Lils' sweet potatoes.",
            "Some Goizueta students tried to force Coca-Cola down your pet's throat", "Dooley had a bone to pick with your pet.", "Your pet didn’t Swoop loudly enough.",
            "Someone called the Atlanta Campus \"Main Campus\".", "Your pet didn't get a Flem spot"};

    private static final String [] NotHealthy = {"Your pet contracted a rare infection from the JRC! Mold is growing out of the cells!",
            "Some premeds tried to use your pet in their Ebola vaccine research!", "" +
            "Your pet caught Opus."};

    private boolean Healthy() { return (this.health > WARN_LEVEL); }
    private boolean Awake() { return(this.energy > WARN_LEVEL); }
    private boolean Happy() { return (this.happiness > WARN_LEVEL); }
    private boolean Fun() { return (this.fun > WARN_LEVEL); }

    //where output should be redirected
    private JTextArea topTextbox;
    private void setOutput(JTextArea topTextbox){this.topTextbox = topTextbox;}
    public void setText(String input){ this.topTextbox.setText(input+"\n"); }
    public void appendText(String input){this.topTextbox.setText(this.topTextbox.getText()+input+"\n");}

    //images of cures
    private static final String CANDY="Tomogatchi/Pill.png";
    public static GImage candy;
    private static final String VIRUS="Tomogatchi/virus.png";
    public static GImage virus;
    private static final String GOIZUETA="Tomogatchi/Goizueta.png";
    public static GImage Coke;
    private static final String PILL="Tomogatchi/RealMedicine.png";
    public static GImage Pill;
    private static final String BED="Tomogatchi/bed.jpg";

    //ending screen
    private static final String GAMEOVER = "Tomogatchi/gameOver.jpg";

    public Pet(JTextArea topTextbox) {
        this.setOutput(topTextbox);
        this.gender = Gender[new Random().nextInt(Gender.length)];
        this.Yikes = NotHealthy[new Random().nextInt(NotHealthy.length)];
        this.Insult = UnhappyInsults[new Random().nextInt(UnhappyInsults.length)];
        this.setText("It's a " + this.getGender() + "!");
        updateBars();
    }


    private void GameOver() {
        this.alive = false;
        this.timerBars.cancel();

        GCanvas canvas = (GCanvas) this.getParent();
        GImage gameOver = new GImage(GAMEOVER);
        gameOver.setSize(2000,2000);
        gameOver.scale(.4,.4);
        canvas.add(gameOver);
    }

    public boolean isAlive(){ return this.alive; }

    private double setBar(double value, double bar){
        if((bar!=0)|(this.alive)){
            if((bar+value)<=0){
                GameOver();
            }
            return Math.min(bar+value,100);
        }
        return bar;
    }

    private double resetBar(double value, double bar){
        if((bar!=0)|(this.alive)){
            if(value<=0){
                GameOver();
            }
            return Math.min(value,100);
        }
        return bar;
    }

    private void timerTick() {
        this.happiness = setBar(-5,this.happiness);
        this.health = setBar(-3,this.health);
        this.energy = setBar(-2,this.energy);
        this.fun = setBar(-4,this.fun);
        updateBars();
    }

    private Timer timerBars = new Timer();
        private TimerTask taskBars = new TimerTask() {
        public void run () {
            timerTick();
        }
    };
            public void start() {
                    timerBars.scheduleAtFixedRate(taskBars,1000,5000);

        }

    private void updateBars() {
        //GCanvas canvas = (GCanvas) this.getParent();
        GRect healthBarEmpty = new GRect(6, 51, 150, 50);
        GRect energyBarEmpty = new GRect(201, 51, 150, 50);
        GRect happinessBarEmpty = new GRect(401, 51, 150, 50);
        GRect funBarEmpty = new GRect(601, 51, 150, 50);

        healthBarEmpty.setFilled(true);
        energyBarEmpty.setFilled(true);
        happinessBarEmpty.setFilled(true);
        funBarEmpty.setFilled(true);

        healthBarEmpty.setFillColor(Color.WHITE);
        energyBarEmpty.setFillColor(Color.WHITE);
        happinessBarEmpty.setFillColor(Color.WHITE);
        funBarEmpty.setFillColor(Color.WHITE);

        healthBarEmpty.setLocation(6,51);
        energyBarEmpty.setLocation(201,51);
        happinessBarEmpty.setLocation(401,51);
        funBarEmpty.setLocation(601,51);

        add(healthBarEmpty);
        add(energyBarEmpty);
        add(happinessBarEmpty);
        add(funBarEmpty);

        GRect healthBar = new GRect(6, 51, 150, 50);
        GRect energyBar = new GRect(201, 51, 150, 50);
        GRect happinessBar = new GRect(401, 51, 150, 50);
        GRect funBar = new GRect(601, 51, 150, 50);

        healthBar.setFilled(true);
        energyBar.setFilled(true);
        happinessBar.setFilled(true);
        funBar.setFilled(true);

        healthBar.setSize(150*(this.health/MAX_ANY), 50);
        energyBar.setSize(150*(this.energy/MAX_ANY), 50);
        funBar.setSize(150*(this.fun/MAX_ANY), 50);
        happinessBar.setSize(150*(this.happiness/MAX_ANY), 50);

        healthBar.setLocation(5,50);
        energyBar.setLocation(200,50);
        happinessBar.setLocation(400,50);
        funBar.setLocation(600,50);

        //////
        // Check health stats
        //////

        //normal levels for health stats
        if(Fun()) {
            funBar.setFillColor(Color.GREEN);
            funBar.setColor(Color.GREEN);
        }

        if(Awake()) {
            energyBar.setFillColor(Color.GREEN);
            energyBar.setColor(Color.GREEN);
        }

        if(Happy()) {
            happinessBar.setFillColor(Color.GREEN);
            happinessBar.setColor(Color.GREEN);
        }

        if(Healthy()) {
            healthBar.setFillColor(Color.GREEN);
            healthBar.setColor(Color.GREEN);
        }

        //warning level for health stats
        if(this.happiness <= WARN_LEVEL) {
            happinessBar.setFillColor(Color.YELLOW);
            happinessBar.setColor(Color.YELLOW);
            this.Insult = UnhappyInsults[new Random().nextInt(UnhappyInsults.length)];
            setText(Insult);
            //Thread.sleep(2000);
            appendText("Your pet is unhappy!");
            if (this.gender.equals("male")) {
                appendText("He needs your love and affection to feel better!");
            } else {
                appendText("She needs your love and affection to feel better!");
            }
        }

        if(this.health <= WARN_LEVEL) {
            healthBar.setFillColor(Color.YELLOW);
            healthBar.setColor(Color.YELLOW);
            this.Yikes = NotHealthy[new Random().nextInt(NotHealthy.length)];
            setText(Yikes);
            appendText("Your pet needs medicine to get better!");
        }

        if(this.fun <= WARN_LEVEL){
            funBar.setFillColor(Color.YELLOW);
            funBar.setColor(Color.YELLOW);
            setText("Your pet is bored, and is almost bored to death!");
            if (this.gender.equals("male")) {
                appendText("You should go do an activity with him and have some fun!");
            } else {
                appendText("You should go do an activity with her and have some fun!");
            }
        }

        if(this.energy <= WARN_LEVEL) {
            energyBar.setFillColor(Color.YELLOW);
            energyBar.setColor(Color.YELLOW);
            setText("Your pet is exhausted and needs to sleep!");
        }

        //critical levels for health stats
        if(this.fun <= CRITICAL_LEVEL) {
            funBar.setFillColor(Color.RED);
            funBar.setColor(Color.RED);
            setText("Are you trying to kill this thing? Your pet is bored!");
        }

        if(this.energy <= CRITICAL_LEVEL) {
            energyBar.setFillColor(Color.RED);
            energyBar.setColor(Color.RED);
            setText("Are you trying to kill this thing? Your pet needs to sleep!");
        }

        if(this.happiness <= CRITICAL_LEVEL) {
            happinessBar.setFillColor(Color.RED);
            happinessBar.setColor(Color.RED);
            setText("Are you trying to kill this thing? Your pet needs love and affection!");
        }

        if(this.health <= CRITICAL_LEVEL) {
            healthBar.setFillColor(Color.RED);
            healthBar.setColor(Color.RED);
            setText("Are you trying to kill this thing? Your pet needs medicine!");
        }

        add(healthBar);
        add(energyBar);
        add(happinessBar);
        add(funBar);

        GLabel healthLbl = new GLabel("HEALTH");
        GLabel energyLbl = new GLabel("ENERGY");
        GLabel happinessLbl = new GLabel("HAPPINESS");
        GLabel funLbl = new GLabel("FUN");

        healthLbl.setFont("Gill Sans Ultra Bold-BOLD-18");
        add(healthLbl, 20, 80);
        energyLbl.setFont("Gill Sans Ultra Bold-BOLD-18");
        add(energyLbl, 220, 80);
        happinessLbl.setFont("Gill Sans Ultra Bold-BOLD-18");
        add(happinessLbl, 420, 80);
        funLbl.setFont("Gill Sans Ultra Bold-BOLD-18");
        add(funLbl, 630, 80);

    }


    //Make timer for health, energy, and happiness to go down at a certain rate (maybe 2.0/s)

    public String getGender() { return gender; }

    public void cure() {
        GCanvas canvas = (GCanvas) this.getParent();

        setText("Pick the medicine that you think will cure your pet.");

        candy = new GImage(CANDY);
        candy.scale(.2,.2);
        candy.setLocation(100,400);
        canvas.add(candy);

        Coke = new GImage(GOIZUETA);
        Coke.scale(.6,.6);
        Coke.setLocation(200,400);
        Coke.setVisible(true);
        canvas.add(Coke);

        virus = new GImage(VIRUS);
        virus.scale(.2,.2);
        virus.setLocation(400,400);
        virus.setVisible(true);
        canvas.add(virus);

        Pill = new GImage(PILL);
        Pill.scale(.2,.2);
        Pill.setLocation(600,400);
        Pill.setVisible(true);
        canvas.add(Pill);
    }

    public void sleep() {
        GCanvas canvas = (GCanvas) this.getParent();
        GImage bed = new GImage(BED);
        bed.setSize(2000,2000);
        bed.scale(.4,.4);
        canvas.add(bed);
        this.energy = setBar(100,this.energy);
        updateBars();
    }

    public void affection() {
        setText("❤ ❤ ❤ ❤ ❤ ❤");
        this.happiness = setBar(5, this.happiness);
        updateBars();
    }

    public void setHealth(double health) { this.health = resetBar(health,this.health); updateBars();}
    public void setEnergy(double energy) { this.energy = resetBar(energy, this.energy); updateBars(); }
    public void setHappiness(double happiness) { this.happiness = resetBar(happiness,this.happiness); updateBars();}
    public void setFun(double fun) { this.fun = resetBar(fun,this.fun); updateBars(); }

    public double getHealth() {
        return this.health;
    }
    public double getEnergy() {
        return this.energy;
    }
    public double getHappiness() {
        return this.happiness;
    }
    public double getFun() {
        return this.fun;
    }

    /*
    public void pause(int millis){
        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(millis);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    */

}




