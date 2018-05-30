package Bubbles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.sun.xml.internal.bind.v2.model.core.ID;



public class Bubble extends BubbleObjects {

	Random r = new Random(); 
	Handler handler = new Handler();

	public Bubble(int x, int y, int radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler; //need this to use collision method
		setVelX(randomDirection()); //The velocity/speed of x direction of bubble set to random value between -3 to 3
		setVelY(randomDirection()); //The velocity/speed of y direction of bubble set to random value between -3 to 3
	}

	public int randomDirection(){
		//used for the random direction. Can not equal to 0 so therefore can not spawn staying still
		
		boolean good = true; //used to loop
		int number = 3-r.nextInt(6); //Initial random number
		
		if (number == 0){ //if the randomly generated number is 0
			
			while(good == true){ //loop
				number = 3-r.nextInt(6); //generate another number
				
				if(number == 0){ //continue to loop if it is 0
					good = true;
				}else{ //come out of loop once generated number is not 0
					good = false;
				}
					
				
			}
			
		}
		
		return number; //return the random generated number


	}
	
	
	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(),getRadius(),getRadius()); //return bounds of object
	}



	public void tick() {
		//running in the thread, sets the created balls in a random motion
		
		setX(getX()+getVelX());
		setY(getY()+getVelY());
		movement(); //bounces off walls
		collision(); //bounces off each other


	}
	
	private void collision() { //collision method ****BUGGY BECAUSE OF DIFFERING SIZES AND THE INNER FOR LOOP DOES NOT MATCH SPEED OF RENDER
		//REASON WHY SOMETIMES OBJECTS DO NOT TOUCH OR GO IN ONE ANOTHER
		//REASON WHY OBJECTS STAY STILL
		
		for(int counter = 0; counter < handler.object.size(); counter++){ //for each initialized object
			BubbleObjects object1 = handler.object.get(counter); //first object
			
			if (handler.object.size() > 1){ //if theres more than 1 object
				for(int count = (counter+1) ; count < handler.object.size(); count++){ //run through the other objects
					BubbleObjects object2 = handler.object.get(count); //second object for current instance of loop
					
					if(object1.getBounds().intersects(object2.getBounds())){ //if the two objects' rectangular bounds intersect
						
						int distanceX = (Math.abs(object2.getX() - object1.getX())); //x distance calculated from center points of two objects
						int distanceY = (Math.abs(object2.getY() - object1.getY())); //y distance calculated from center points of two objects
						
						double finalDistance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)); //final distance calculated using square root 
						//of a^2 + b^2
						
						if(finalDistance <= object1.getRadius()/2 + object2.getRadius()/2){ //if the final distance is less than the 
							//addition of the radius of both objects so basically if the circles touch.
							//the following physics equation is used to calculate elastic collision:
							// Vfinal1 = (m1-m2/m1+m2)*v1 + (2*m2/m1+m2)*v2 -> m1 = mass1, m2= mass2, v1 = initial velocity1, v2 = initial velocity2
							// Vfinal2 = (2*m1/ m1+m2)*v1 + (m2-m1/m1+m2)*v2 -> m1 = mass1, m2= mass2, v1 = initial velocity1, v2 = initial velocity2
							//equations can be shortened to (((m1-m2)*v1) + (2*m2*v2))/ (m1 + m2) and ((2*m1*v1) + ((m2-m1)*v2)) / (m1 + m2)
						
							int fVelX1 = (int) (object1.getVelX() * (object1.getRadius() - object2.getRadius()) + (2 * object2.getRadius() * object2.getVelX())) / (object1.getRadius() + object2.getRadius());
							int fVelY1 = (int) (object1.getVelY() * (object1.getRadius() - object2.getRadius()) + (2 * object2.getRadius() * object2.getVelY())) / (object1.getRadius() + object2.getRadius());
							
							int fVelX2 = (int) (object2.getVelX() * (object2.getRadius() - object2.getRadius()) + (2 * object1.getRadius() * object1.getVelX())) / (object1.getRadius() + object2.getRadius());
							int fVelY2 = (int) (object2.getVelY() * (object2.getRadius() - object1.getRadius()) + (2 * object1.getRadius() * object1.getVelY())) / (object1.getRadius() + object2.getRadius());
							
							//replace with new velocities
							object1.setVelX(fVelX1); 
							object1.setVelY(fVelY1);
							
							object2.setVelX(fVelX2);
							object2.setVelY(fVelY2);
							
							object1.setX (object1.getX() + fVelX1);
							object1.setY (object1.getY() + fVelY1);

							object2.setX (object2.getX() + fVelX2);
							object2.setY (object2.getY() + fVelY2);
							
							
						}
						
						
					}
					
					
				}
			}
			
		}
		
	}

	public void render(Graphics g) {
		//running in the thread, creates an orange ball with the specified coordinates and size in the GameFrame class
		//Graphics2D g2d = (Graphics2D) g;
		
		//g.setColor(Color.white);
		//g2d.draw(getBounds());
		g.setColor(Color.white); 
		g.fillOval(getX(), getY(), getRadius(), getRadius());

	}
	
	
	public void movement(){
		
		//STILL BUGS WITH Y MOVEMENT, MATH MAKES SENSE; MAYBE SOMETHING TO DO WITH THE DIFFERING SIZES OF THE OBJECTS
		
		
		//for the left and right walls
		if(getX() <= 0 || getX() >= GameFrame.WIDTH - getRadius()){ //left and right bounce
			setVelX(-getVelX()); //reverse direction
		}

		//for the top and bottom walls
		if (getY() <= 0 || getY() >= GameFrame.HEIGHT - (getRadius()+ getRadius()/6)) { //top and bottom bounce
			setVelY(-getVelY()); //reverse direction
		
		}
	}


}
