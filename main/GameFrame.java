package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	GamePanel gp;
	int boundX = 6;
	int boundY = 27;
	public GameFrame() {
		super("Ball");
		createGUI();
		new RefreshWindow(this).start();
		setResizable(false);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createGUI() {
		gp = new GamePanel();
		add(gp);
		setSize(gp.x*40 + boundX, gp.y*40 + boundY);
	}
}

class GamePanel extends JPanel {
	String lab;
	int x, y;
	BufferedImage block1, block2, ball;
	Ball b;
	public GamePanel (){
		super();
		createGUI();
		b = new Ball((lab.indexOf('b')%x), (lab.indexOf('b')/x));
		addKeyListener(new GameAction(b));
		setFocusable(true);
        requestFocusInWindow();
	}
	
	private void createGUI(){
		lab = "";
		try {
			Scanner in = new Scanner(getClass().getResourceAsStream("lab.txt"));
			lab += in.nextLine().trim();
			x = lab.length();
			//x = new Integer(temp.substring(0, temp.indexOf(' ')));
			//y = new Integer(temp.substring(temp.indexOf(' ')+1, temp.length()));
			y = 1;
			while(in.hasNext()) {
				lab += in.nextLine().trim();
				y++;
			}
			in.close();
		} catch (Exception ex) {System.out.print(ex.getMessage());}
		
		try {
			block1 = ImageIO.read(getClass().getResource("block_3.png"));
			block2 = ImageIO.read(getClass().getResource("block_4.png"));
			ball = ImageIO.read(getClass().getResource("ball_4.png"));
		} catch (Exception c) {}
		setSize(x*40, y * 40);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i <lab.length(); i++) 
			if (lab.charAt(i) == '#') {
				g.drawImage(block1, (i%x)*block1.getWidth(), (i/x)*block1.getHeight(), null);
			}
			else if (lab.charAt(i) == '!') {
				g.drawImage(block2, (i%x)*block1.getWidth(), (i/x)*block1.getHeight(), null);
			}
		
		
		
		if (lab.charAt(b.y*x+b.x) == '#' || lab.charAt(b.y*x+b.x) == '!') {
			g.drawImage(ball, b.oldx*block1.getWidth(), b.oldy*block1.getHeight(), null);
			b.x = b.oldx;
			b.y = b.oldy;
		}
		else {
			g.drawImage(ball, b.x*block1.getWidth(), b.y*block1.getHeight(), null);
			b.oldx = b.x;
			b.oldy = b.y;
		}
			

	}
}
