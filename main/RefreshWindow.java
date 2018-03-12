package main;

public class RefreshWindow extends Thread {
	GameFrame gf;
	
	public RefreshWindow(GameFrame gf) {
		this.gf = gf;
	}
	public void run() {
		while (true) {
			
			
			try {
				sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gf.repaint();
		}
	}
}
