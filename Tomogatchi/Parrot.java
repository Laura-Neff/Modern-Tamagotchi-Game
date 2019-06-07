
package Tomogatchi;

import acm.graphics.GCanvas;
import acm.graphics.GDimension;
import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.graphics.GLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Parrot extends Pet implements PetsComeinAllShapesandSizes {

    private static final String THEPARROT="Tomogatchi/parrot.png";
    private static final String THEWORM="Tomogatchi/worm.png";
    private static final String TIKI="Tomogatchi/TikiRoom.jpg";
    private double w, h;

    //for teaching the parrot how to say things
    private JTextField parrotSay;
    private String teachParrot = "";
    private GOval speechBubble;
    private JTextArea parrotPrompt;
    private JButton submit;
    private GLabel learnedPhrase;

    public Parrot(JTextArea topTextbox) {
        super(topTextbox);
        petImage = new GImage(THEPARROT);
        petImage.setLocation(300, 300);
        petImage.setVisible(true);
        GDimension parrotdim = petImage.getSize();
        w = parrotdim.getWidth();
        h = parrotdim.getHeight();
        add(petImage);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name){
        this.name = name;
        this.setText("You adopted a " + getGender() + " parrot named " + name + ".");
    }

    @Override
    public void eat() {
        GCanvas canvas = (GCanvas) this.getParent();
        GImage worm = new GImage(THEWORM);
        worm.scale(.3,.3);
        worm.setLocation(300, 430);
        setParrotScale(1,1);
        petImage.setLocation(300,230);
        canvas.add(petImage);
        canvas.add(worm);
        this.setText(getName() + " ate a worm. That's gross! CHIRP, CHIRP!");
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
        GImage tikiRoom = new GImage(TIKI);
        tikiRoom.setSize(2000,2000);
        tikiRoom.scale(.4,.4);
        canvas.add(tikiRoom);
        petImage.setLocation(520,450);
        setParrotScale(.5,.5);
        canvas.add(petImage);
        this.setText(getName() + " wants to sing at the Tiki Room, but doesn't know any words!");
        if (getGender().equals("male")) {
            this.appendText("You should teach him some!");
        } else {
            this.appendText("You should teach her some!");
        }
        this.setFun(this.getFun() + 10);
        TeachParrot();
    }

    public void setParrotScale(double x, double y) {
        GCanvas canvas = (GCanvas) this.getParent();
        petImage.setSize(w,h);
        petImage.scale(x,y);
        canvas.add(petImage);
    }

    public void setParrotLocation(double x, double y) {
        GCanvas canvas = (GCanvas) this.getParent();
        petImage.setLocation(x,y);
        canvas.add(petImage);
    }

    private void TeachParrot() {
        GCanvas canvas = (GCanvas) this.getParent();
        if(teachParrot.length() > 0){
            this.setText(getName() + " says " + teachParrot + "! (and is overjoyed)");
            canvas.add(speechBubble);
            canvas.add(learnedPhrase);
            return;
        }
        //background
        speechBubble = new GOval(225,120);
        speechBubble.setFilled(true);
        speechBubble.setFillColor(Color.WHITE);
        speechBubble.setLocation(475,325);
        canvas.add(speechBubble);

        //input area
        parrotPrompt = new JTextArea("Enter a phrase to teach "+ getName());
        parrotSay = new JTextField(15);
        parrotPrompt.setLocation(500,365);
        parrotPrompt.setEditable(false);
        parrotSay.setLocation(500,380);
        canvas.add(parrotSay);
        canvas.add(parrotPrompt);

        //submit button
        submit = new JButton("Teach!");
        submit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        teachCallback();
                    }
                });
        submit.setLocation(500,400);
        canvas.add(submit);
        //recheck that all objects supposed to be there are
        canvas.revalidate();
    }

    private void teachCallback(){
        GCanvas canvas = (GCanvas) this.getParent();
        teachParrot = parrotSay.getText();
        if(teachParrot.length() > 0){
            this.setText(getName() + " learned a new phrase!");
            this.appendText(getName() + " says " + teachParrot + "! (and is overjoyed)");
            //speechBubble.getParent().remove(speechBubble);
            learnedPhrase = new GLabel(teachParrot+"!");
            learnedPhrase.setFont("Gill Sans Ultra Bold-BOLD-18");
            learnedPhrase.setLocation(525,390);
            canvas.add(learnedPhrase);
            parrotPrompt.getParent().remove(parrotPrompt);
            parrotSay.getParent().remove(parrotSay);
            submit.getParent().remove(submit);
        }
    }

    @Override
    public void giveCandy(){
        this.setText("Who would have thought? Parrots can’t eat M&Ms! You just killed " + this.getName());
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
        this.setText("Your parrot fell for the Goizueta schemes! Now " + this.getName()
                + " won't stop talking about the Beta index or the NASDAQ! Maybe if you give " + this.getName() +
                " something else, " + this.getName() + " will be back to normal.");
    }

    @Override
    public void givePill(){
        this.setText(this.getName() + " is cured and healthy again! Just what a parrot needs!");
        this.setHealth(100);
    }

}
