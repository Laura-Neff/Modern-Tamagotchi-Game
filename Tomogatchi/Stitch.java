package Tomogatchi;
import acm.graphics.GCanvas;
import acm.graphics.GDimension;
import acm.graphics.GImage;

import javax.swing.*;

public class Stitch extends Pet implements PetsComeinAllShapesandSizes {

    private static final String THESTITCH = "Tomogatchi/Stitch.png";
    private static final String THEKABOB="Tomogatchi/kebab.png";
    private static final String THEBEACH = "Tomogatchi/honolulubeach.JPG";
    private double w,h;

    public Stitch(JTextArea topTextbox) {
        super(topTextbox);
        petImage = new GImage(THESTITCH);
        petImage.setLocation(200, 200);
        petImage.setVisible(true);
        GDimension dogdim = petImage.getSize();
        w = dogdim.getWidth();
        h = dogdim.getHeight();
        //petImage.scale(.5,.5);
        add(petImage);
    }

    public String getName() { return this.name; }

    @Override
    public void setName(String name){
        this.name = name;
        this.setText("You adopted a " + getGender() + " Stitch named " + name + ".");
    }

    @Override
    public void eat() {
        GCanvas canvas = (GCanvas) this.getParent();
        GImage kabob = new GImage(THEKABOB);
        kabob.setLocation(300, 430);
        setStitchScale(.5,.5);
        petImage.setLocation(300,230);
        canvas.add(petImage);
        canvas.add(kabob);
        this.setText(getName() + " ate a kabob. So delicious! Aloha.");
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
        GImage honoluluBeach = new GImage(THEBEACH);
        honoluluBeach.setSize(2000, 2000);
        honoluluBeach.scale(.4, .4);
        canvas.add(honoluluBeach);
        petImage.setLocation(400, 500);
        setStitchScale(.3, .3);
        canvas.add(petImage);
        this.setText(getName() + " took a walk on the best beach in Honolulu!");
        this.appendText("Aloha!");
        this.setFun(this.getFun() + 5);
    }

    public void setStitchLocation(double x, double y) {
        GCanvas canvas = (GCanvas) this.getParent();
        petImage.setLocation(x, y);
        canvas.add(petImage);
    }

    public void setStitchScale(double x, double y) {
        GCanvas canvas = (GCanvas) this.getParent();
        petImage.setSize(w, h);
        petImage.scale(x, y);
        canvas.add(petImage);
    }

    @Override
    public void giveCandy(){
        this.setText("Nice try. That didn't do anything. But at least "
                + this.getName() + " is less cranky?");
    }

    @Override
    public void giveVirus(){
        this.setText("Wow, how did you know that using another virus is the only cure to " +
                "any of a Stitch's ailments?");
        this.setHealth(100);
    }

    @Override
    public void giveSoda(){
        this.setHealth(0);
        this.setText("AHHHH, YOU FOOL! A Stitch doesn't take kindly to Goizueta schemes!" +
                " You just killed " + this.getName());
    }

    @Override
    public void givePill(){
       this.setText("A Stitch is different than any other kind of species. Pills that " +
                "heal dogs, parrots, and snails do not heal a Stitch. You should try clicking on something else.");
    }
}




