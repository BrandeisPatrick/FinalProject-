package finalproject;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class GraphicBoard extends JPanel {
	public static final int WIDTH = 800, HEIGHT = 800;
	public static final int SPOTWIDTH =   50;
	public static final int SPOTBEGINX= 200, SPOTBEGINY= 200;
	
	public GraphicBoard(int WIDTH, int HEIGHT) {
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setResizable(false);
		jframe.setVisible(true);
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.white);
		
		g.setColor(Color.red);
		g.fillRect(25, 25, WIDTH, HEIGHT);
	}
	
	public void repaint(Graphics g, int SPOTWIDTH, int SPOTBEGINX, int SPOTBEGINY) {
		int cornerX = SPOTBEGINX, cornerY = SPOTBEGINY;
		int changeColor = 1;
		for(int i = 1; i <=8; i++) {
			cornerY += 50;
			for(int j = 1; j <=8; j++) {
				cornerX += 50;
				if(changeColor % 2 == 0) {
					g.setColor(Color.red);
				}else {
					g.setColor(Color.gray.darker());
				}
				g.fillRect(cornerX, cornerY, SPOTWIDTH, SPOTWIDTH);
			}
		}
		
	}
	

	public static void main(String[] args) {
		GraphicBoard gBoard = new GraphicBoard(WIDTH, HEIGHT);
	}
}
