//SUBMITTED BY: Jhaelle Payne
package Tomogatchi;

import acm.graphics.GCompound;


public abstract class TamagotchiFall extends GCompound {

	private double vx;
	private double vy;
	private double ax;
	private double ay;
	
	public double getXVelocity() {
		return vx;
	}
	
	public double getYVelocity() {
		return vy;
	}
	
	public void setVelocity(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	public void setAcceleration(double ax, double ay) {
        this.ax = ax;
        this.ay = ay;
	}
	
	
	public abstract void move();

}
