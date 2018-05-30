package Bubbles;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	
	
	
	public Window(String title, GameFrame bubbles){
		
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(GameFrame.WIDTH,GameFrame.HEIGHT)); //set sizes of frame
		frame.setMaximumSize(new Dimension(GameFrame.WIDTH,GameFrame.HEIGHT));
		frame.setMinimumSize(new Dimension(GameFrame.WIDTH,GameFrame.HEIGHT));	
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allow to close
		frame.setResizable(false); //dont let people resize frame
		frame.setLocationRelativeTo(null); //set at very centre of screen
		frame.add(bubbles); //adding GrameFrame to frame
		frame.setVisible(true); //make it visible
		bubbles.start(); //start the start() method in GameFrame -> begin the thread
		
	}
	
}
