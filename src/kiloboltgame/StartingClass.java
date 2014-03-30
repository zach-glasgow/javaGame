package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;

public class StartingClass extends Applet implements Runnable {

	@Override
	public void init() {

		// sets applet size
		setSize(800, 600);
		// sets applet background
		setBackground(Color.BLACK);
		// applet takes focus and the input goes directly into it
		setFocusable(true);
		// assigns the applet window to the frame variable
		Frame frame = (Frame) this.getParent().getParent();
		// sets the title
		frame.setTitle("Q-Bot Alpha");
	}

	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void run() {
		while (true) {
			// this calls paint
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
