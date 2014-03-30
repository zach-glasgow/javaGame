package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartingClass extends Applet implements Runnable, KeyListener {

	@Override
	// when the applet run for first time, it will run the init() method
	public void init() {

		// sets applet size
		setSize(800, 600);
		// sets applet background
		setBackground(Color.BLACK);
		// applet takes focus and the input goes directly into it
		setFocusable(true);
		// add KeyListener to current Applet
		addKeyListener(this);
		// assigns the applet window to the frame variable
		Frame frame = (Frame) this.getParent().getParent();
		// sets the title
		frame.setTitle("Q-Bot Alpha");
	}

	@Override
	// It is called automatically after the init() method
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

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;

		case KeyEvent.VK_DOWN:
			System.out.println("Move down");
			break;

		case KeyEvent.VK_LEFT:
			System.out.println("Move left");
			break;

		case KeyEvent.VK_RIGHT:
			System.out.println("Move right");
			break;

		case KeyEvent.VK_SPACE:
			System.out.println("Jump");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			System.out.println("Stop moving down");
			break;

		case KeyEvent.VK_LEFT:
			System.out.println("Stop moving left");
			break;

		case KeyEvent.VK_RIGHT:
			System.out.println("Stop moving right");
			break;

		case KeyEvent.VK_SPACE:
			System.out.println("Stop jumping");
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
