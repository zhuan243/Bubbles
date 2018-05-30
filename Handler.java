package Bubbles;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<BubbleObjects> object = new LinkedList<BubbleObjects>(); //faster at adding and removing objects than ArrayList
	//LinkedList of bubbles from Bubble class

	public void tick(){
		for(BubbleObjects counter: object){ //create objects based on how many objects are initialized 
			BubbleObjects tempObject = counter; //runs through list to alter objects (positions and size)

			tempObject.tick();
		}
	}

	public void render(Graphics g){
		for(BubbleObjects counter: object){ //draws objects based on how many objects are initialized
			BubbleObjects tempObject = counter; //runs through list to draw object

			tempObject.render(g);
		}
	}

	
	public void addObject (BubbleObjects object){ //used to initialize objects
		this.object.add(object); //add object to LinkedList
	}
	
	//if need to remove object
	//public void removeObject (BubbleObjects object){
		//this.object.remove(object);
	//}

}
