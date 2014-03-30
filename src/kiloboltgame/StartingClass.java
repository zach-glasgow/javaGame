package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private Robot robot;
	private Image image, character;
	private URL base;
	private Graphics second;

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
		try {
			base = getDocumentBase();
		} catch (Exception e){
			//TODO: handle exception
		}
		
		//Image Setups
		character = getImage(base, "data/character.png");
	}

	@Override
	// It is called automatically after the init() method
	public void start() {
		robot = new Robot();
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
			robot.update();
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
	// It is used for double buffering, a technique that retaining the previous
	// position
	// of the screen's current image for a short amount of time, so that the
	// movement
	// of the image looks smooth natural.
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	// It is used to draw graphics to the screen
	public void paint(Graphics g) {
		g.drawImage(character, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
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
			robot.moveLeft();
			break;

		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			break;

		case KeyEvent.VK_SPACE:
			robot.jump();
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
			robot.stop();
			break;

		case KeyEvent.VK_RIGHT:
			robot.stop();
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
