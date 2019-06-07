package Tomogatchi;
import acm.graphics.GCanvas;
import acm.graphics.GDimension;
import acm.graphics.GImage;

import javax.swing.*;

public class Snail extends Pet implements PetsComeinAllShapesandSizes  {

    private static final String THESNAIL="Tomogatchi/snail.png";
    private static final String THEFUNGUS="Tomogatchi/fungus.png";
    private static final String THEBIKINIBOTTOM="Tomogatchi/BikiniBottom.PNG";
    private double w,h;
    private static final String MEOW = "MEOW, MEOW, MEOW!";

    public Snail(JTextArea topTextbox) {
        super(topTextbox);

        petImage = new GImage(THESNAIL);
        petImage.setLocation(300,300);
        petImage.setVisible(true);
        GDimension dogdim = petImage.getSize();
        w = dogdim.getWidth();
        h = dogdim.getHeight();
        add(petImage);
    }

    public String getName() { return this.name; }

    @Override
    public void setName(String name){
        this.name = name;
        this.setText("You adopted a " + getGender() + " snail named " + name + ".");
    }

    @Override
    public void eat() {
        GCanvas canvas = (GCanvas) this.getParent();
        GImage fungusFood = new GImage(THEFUNGUS);
        fungusFood.setLocation(0, 470);
        petImage.setLocation(300,230);
        canvas.add(petImage);
        canvas.add(fungusFood);
        this.setText(getName() + " ate some fungus off of Spongebob. Tasty! " + MEOW);
        if (getGender().equals("male")) {
            this.appendText("He seems like he enjoyed it! Lils' better step up their game...");
        } else {
            this.appendText("She seems like she enjoyed it! Lils' better step up their game...");
        }
        this.setHealth(this.getHealth()+30);

    }

    @Override
    public void activity() {
        GCanvas canvas = (GCanvas) this.getParent();
        GImage bikiniBottom = new GImage(THEBIKINIBOTTOM);
        bikiniBottom.setSize(2000,2000);
        bikiniBottom.scale(.4,.4);
        canvas.add(bikiniBottom);
        petImage.setLocation(400,600);
        setSnailScale(.3,.3);
        canvas.add(petImage);
        this.setText(getName() + " took a walk on Conch Street, right next to Squidward's House!");
        this.appendText(MEOW);
        this.setFun(this.getFun() + 5);
    }

    public void setSnailScale(double x, double y) {
        GCanvas canvas = (GCanvas) this.getParent();
        petImage.setSize(w,h);
        petImage.scale(x,y);
        canvas.add(petImage);
    }


    public void setSnailLocation(double x, double y) {
        GCanvas canvas = (GCanvas) this.getParent();
        petImage.setLocation(x,y);
        canvas.add(petImage);
    }

    @Override
    public void giveCandy(){
        this.setText("Wow, how did you know that M&Ms cure snails?");
        this.setHealth(100);
    }

    @Override
    public void giveVirus(){
        this.setText("YOU JUST GAVE " + this.getName() +
                " ANOTHER VIRUS! Are you incompetent?! You just killed " + this.getName());
        this.setHealth(0);
    }

    @Override
    public void giveSoda(){
        this.setText("Your snail fell for the Goizueta schemes! Now " + this.getName()
                + " won't stop talking about the Beta index or the NASDAQ! Maybe if you give " + this.getName() +
                " something else, " + this.getName() + " will be back to normal.");
    }

    @Override
    public void givePill(){
        this.setText("That helped a little, but it's not the best option for a snail. Maybe you should try clicking on" +
                "something else for better results.");
        this.setHealth(this.getHealth() + 8);
    }

}


