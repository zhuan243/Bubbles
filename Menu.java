package Bubbles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicButtonUI;

public class Menu {
	
	//************* NOTE: NEED TO CHANGE THE DIRECTORY OF IMAGES ON A DIFFERENT COMPUTER, OR THE BACKGROUND AND ICONS WONT SHOW UP.
	
	public static void main (String args[]){
		
		final JFrame menu = new JFrame("MAIN MENU");
		
		
		//set Background Image
		try {
			menu.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Zhenyu\\COMP SCI\\Bubbles\\res\\BackGround.jpg")))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		menu.setSize(800, 600); //set size
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allow to close
		menu.setResizable(false); //dont let people resize menu
		menu.setLocationRelativeTo(null); //set at very centre of screen
		
		
		ImageIcon startImg = new ImageIcon("C:\\Zhenyu\\COMP SCI\\Bubbles\\res\\Start.png"); //image icon for start button
		JButton start = new JButton(startImg);
		start.setUI(new BasicButtonUI()); //gets rid of button background
		start.setBounds(89,250,240,225);
		start.setOpaque(false); //disallow hover colour
		start.setBorderPainted(false); //disallow border
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menu.setVisible(false);
				GameFrame start = new GameFrame();
				
			}
			
		});
		
		ImageIcon helpImg = new ImageIcon("C:\\Zhenyu\\COMP SCI\\Bubbles\\res\\Help.png"); //image icon for help button
		JButton help = new JButton(helpImg);
		help.setUI(new BasicButtonUI());
		help.setBounds(464,250,240,225);
		help.setOpaque(false);
		help.setBorderPainted(false);
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(menu, "Click left mouse to create bubble. Hold to make a bigger bubble!");
				
			}
			
		});
		
		menu.add(help); //add buttons to frame
		menu.add(start);
		menu.setVisible(true); //make it visible
		
		
		
		
		
	
		
	}
	
	
}
