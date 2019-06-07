//package Tomogatchi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import acm.breadboards.AbstractBreadboard;
import acm.graphics.GImage;

import Tomogatchi.*;

import javax.swing.*;

public class FrontPage extends AbstractBreadboard {
	public static final int BREADBOARD_WIDTH = 500;
	public static final int BREADBOARD_HEIGHT = 667;

	TamagotchiPicture tama;
	TamagotchiFall[] tamaFall;
	Random random;
	

	public void run() {
		JButton start = new JButton("Let's go!");
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

		this.setSize(BREADBOARD_WIDTH,BREADBOARD_HEIGHT);
		
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

	public void startGame(){
		//this.getParent().setVisible(false);
		this.getTimer().stop();
		String[] args = {""};
		FinalTomogatchi.main(args);
	}
}
