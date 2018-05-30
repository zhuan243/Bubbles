package Bubbles;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.sun.xml.internal.bind.v2.model.core.ID;



public abstract class BubbleObjects { //used to create an object of bubble : used in linkedlist
	
	private int x; //pos x
	private int y; //pos y
	private ID id;	// IDs of objects (in case I added more object types)
	private int velX; // velocity x
	private int velY; // velocity y
	private int radius; // size

	
	public BubbleObjects(int x , int y, int radius, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
		this.radius = radius;
	}
	
	public abstract void tick(); //to create object
	public abstract void render(Graphics g); //to draw object
	public abstract Rectangle getBounds(); //for collision, get rectangular bounds of object
	
	
	//SETTERS AND GETTERS
	
	public void setRadius(int radius){
		this.radius = radius;	
	}
	
	public int getRadius(){
		return radius;
	}
	
	public void setX(int x){
		this.x = x;
		
	}
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	public void setID(ID id){
		this.id = id;
	}
	
	public ID getID(){
		return id;
	}
	
	public void setVelX(int velX){
		this.velX = velX;
	}
	
	public void setVelY(int velY){
		this.velY = velY;
	}
	
	public int getVelX(){
		return velX;
	}
	
	public int getVelY(){
		return velY;
	}
	
	
	
	
}
