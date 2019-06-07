package Tomogatchi;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GOval;

//SUBMITTED BY: Jhaelle Payne

public class TamagotchiPicture extends TamagotchiFall {

	private GImage apple;
	double ay = .01;

	public TamagotchiPicture(double x, double y) {
			GImage apple = new GImage("src/Tomogatchi/Tamagotchi-12.png");
			
			this.add(apple);
			this.setLocation(x,y);
			
			this.setVelocity(0, 0.5);
			this.setAcceleration(0, 0);
			
		}
		
		public void move() {
			
			double vx = this.getXVelocity();
			double vy = this.getYVelocity()+ay;
			this.setLocation(this.getX() + vx, this.getY() + vy);
			
			if (this.getX() == 250){
				this.setVelocity(0, 0);
				this.setAcceleration(0, 0);
			}
			
		
	}
	   
}
