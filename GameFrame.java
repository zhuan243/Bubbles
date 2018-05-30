package Bubbles;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.xml.internal.bind.v2.model.core.ID;

public class GameFrame extends Canvas implements Runnable{

	public static final int WIDTH = 800, HEIGHT = WIDTH/12 *9; //the dimensions for the canvas and JFrame
	private Thread thread; //create a thread
	private boolean running = false; //thread initially not running, run method initiates run
	private Handler handler = new Handler();; //calling the handler class
	private Timer timer; //timer used to calculate mouse press time
	private TimerTask task; //what to do when timing the mouse press
	private int size = 0; //used to increase size of bubbles
	Window f = new Window("Bubbles!", this); //calling the window class to create a JFrame

	//public static void main (String args[]){
		
		 //GameFrame f = new GameFrame(); //start this class
	
	//}


	public GameFrame(){
		
		this.addMouseListener(new MouseAdapter(){ 
			
			@Override
			public void mousePressed(MouseEvent e) { //when mouse is pressed
				
				time(); //start time
				timer.scheduleAtFixedRate(task, 0, 100); //Every 100ms increase size by 1

			}
			
			@Override
			public void mouseReleased(MouseEvent e) { //when mouse is released

				if(size <= 1){ //if mouse was just clicked
					handler.addObject(new Bubble(e.getX() - 10, e.getY() - 10, 20, ID.ID, handler)); //add an orange bubble with the following stats
			
				}else if (size > 10){ //prevents the bubble from getting too big
					handler.addObject(new Bubble(e.getX() - 100, e.getY() - 100, 200, ID.ID, handler));
					
				}else{ //creates a bubble based on time of mouse press
					handler.addObject(new Bubble(e.getX() - (20 * size)/2 ,e.getY() - (20 * size)/2 ,(20 * size), ID.ID, handler));
					
				}
				
				timer.cancel(); //stop time
				size = 0; //reset size counter
				
			}



		});


	}
	
	public void time(){ //timer method
		timer = new Timer();
		task = new TimerTask(){
			public void run(){
				size++; //increment size
			}
		};
	}



	public synchronized void start(){ //start method: synchronized with stop so the stop method does not interfere with the thread
		thread = new Thread(this); //create new thread
		thread.start(); //run the thread
		running = true; //sets the boolean of run to true
	}


	public synchronized void stop(){ //stop method: synchronized with start so the stop method does not interfere with the thread
		try{ 
			thread.join(); //if the thread has run through once fully
			running = false; //and the boolean running is false, then stop the thread
		}catch(Exception e){
			e.printStackTrace(); //print out any errors
		}
	}


	public void run(){ //the thread: displays the frames per second and each sequence runs the tick and render methods
		long lastTime = System.nanoTime(); //current time in nanoseconds
		double ticksAmount = 60.0; //amount of updates in a second
		double ns = 1000000000 / ticksAmount; //1000000000 ns is 1 second, and divided by 60 represents how long for every update
		double delta = 0; //used to ensure that tick only occurs every 1/60 of a second
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running){
			long now = System.nanoTime(); //current time in nanoseconds after running thread
			delta += (now - lastTime) / ns; //time differential after initializing the thread divided by 1/60 of a second
			lastTime = now; //updates current time
			
			while (delta >= 1){ //to ensure that 1/60 of a second has passed before initializing the tick method
				tick();
				delta--; //reset delta
			}
			if (running){
				render();
				frames++; //increment frames every second; counts how many renders per second
			}

			if(System.currentTimeMillis() - timer > 1000){ //every second that passes, update the FPS
				timer+= 1000; //timer is now 1 second later, used to calculate for next update of FPS
				System.out.println("FPS: " + frames);
				frames = 0; //reset frames per second
			}
		}
		stop();

	}

	private void tick(){ //this method initiates every time the thread runs through itself
		
		handler.tick();

	}

	private void render(){  
		
		
		BufferStrategy bs = this.getBufferStrategy(); //used to render each frame
		if(bs == null){ //if buffer strategy doesn't exist
			this.createBufferStrategy(3); //create three buffers
			return;
		}

		Graphics g = bs.getDrawGraphics(); //retrieving an image from buffer
		
		g.setColor(Color.gray); //background
		g.fillRect(0,0,WIDTH,HEIGHT);
		
	
		handler.render(g); //calls the render in the Handler class
		
		g.dispose(); //reset the frame with all it's images
		bs.show(); //updates to current buffer
	}

	
}
