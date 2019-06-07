package Tomogatchi;
import acm.graphics.GCanvas;
import acm.graphics.GDimension;
import acm.graphics.GImage;

import javax.swing.*;

public class Dog extends Pet implements PetsComeinAllShapesandSizes  {

    private static final String THEDOG="Tomogatchi/dog.png";
    private static final String THEBRISKET="Tomogatchi/brisket.png";
    private static final String PIEDMONT="Tomogatchi/Piedmont.jpg";
    private double w,h;
    private static final String BARK = "WOOF, WOOF!";

    public Dog(JTextArea topTextbox) {
       super(topTextbox);
       petImage = new GImage(THEDOG);
       petImage.setLocation(300, 300);
       petImage.setVisible(true);
       GDimension dogdim = petImage.getSize();
       w = dogdim.getWidth();
       h = dogdim.getHeight();
       add(petImage);
    }

    @Override
    public void setName(String name){
        this.name = name;
        this.setText("You adopted a " + getGender() + " dog named " + this.getName() + ".");
    }

    @Override
    public void eat() {
        GCanvas canvas = (GCanvas) this.getParent();
        GImage brisket = new GImage(THEBRISKET);
        brisket.scale(.5,.5);
        brisket.setLocation(300, 430);
        setDogScale(1.5,1.5);
        petImage.setLocation(300,230);
        canvas.add(petImage);
        canvas.add(brisket);
        this.setText(getName() + " ate some brisket. How yummy! " + BARK);

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
        GImage piedmont = new GImage(PIEDMONT);
        piedmont.setSize(2000,2000);
        piedmont.scale(.4,.4);
        canvas.add(piedmont);
        petImage.setLocation(400,550);
        setDogScale(.3,.3);
        canvas.add(petImage);
        this.setText(getName() + " took a walk. Guess the lap band surgery is off.");
        this.appendText("WOOF WOOF!");
        this.setFun(this.getFun() + 5);
    }

    public void setDogLocation(double x, double y) {
        GCanvas canvas = (GCanvas) this.getParent();
        petImage.setLocation(x,y);
        canvas.add(petImage);
    }

    public void setDogScale(double x, double y) {
        GCanvas canvas = (GCanvas) this.getParent();
        petImage.setSize(w,h);
        petImage.scale(x,y);
        canvas.add(petImage);
    }

    @Override
    public void giveCandy(){
        this.setText("You idiot! Dogs can't eat M&Ms! You just killed " + this.getName());
        this.setHealth(0);
    }

    @Override
    public void giveVirus(){
        this.setText("YOU JUST GAVE " + this.getName() +
                " ANOTHER VIRUS! Are you incompetent?! You just killed " + this.getName());
        this.setHealth(0);
    }

    @Override
    public void giveSoda(){
        this.setText("Your dog fell for the Goizueta schemes! Now " + this.getName()
                + " won't stop talking about the Beta index or the NASDAQ! Maybe if you give " + this.getName() +
                " something else, " + this.getName() + " will be back to normal.");
    }

    @Override
    public void givePill(){
        this.setText(this.getName() + " is cured and healthy again! Just what a dog needs!");
        this.setHealth(100);
    }

}
