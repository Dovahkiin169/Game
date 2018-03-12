package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameAction extends KeyAdapter{
	
	Ball b;
	public GameAction(Ball b) {
		this.b = b;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		
		switch (k) {
		case 37: b.x--; break;
		case 38: b.y--; break;
		case 39: b.x++; break;
		case 40: b.y++; break;
		}
	}
	
}