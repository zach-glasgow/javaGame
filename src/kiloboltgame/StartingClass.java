package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private Robot robot;
	private Heliboy hb, hb2;
	private Image image, currentSprite, character, characterDown, characterJumped, background, heliboy;
	private URL base;
	private Graphics second;
	// They are static so we can create getters and setters for them to be used
	// in other classes for movement.
	private static Background bg1, bg2;

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
		} catch (Exception e) {
			// TODO: handle exception
		}

		//Image Setups
		character = getImage(base, "data/character.png");
		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jumped.png");
		currentSprite = character;
		heliboy = getImage(base, "data/heliboy.png");
		//Background Setups
		background = getImage(base, "data/background.png");
	}

	@Override
	// It is called automatically after the init() method
	public void start() {
		bg1 = new Background(0, 0);
		hb = new Heliboy(340, 360);
		hb2 = new Heliboy(700, 360);
		bg2 = new Background(2160, 0);
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
			if (robot.isJumped()) {
				currentSprite = characterJumped;
			} else if (robot.isJumped() == false && robot.isDucked() == false) {
				currentSprite 	= character;
			}
			
			ArrayList projectiles = robot.getProjectiles();
			for(int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				if (p.isVisible() == true) {
					p.update();
				} else {
					projectiles.remove(i);
				}
			}
			
			hb.update();
			hb2.update();
			bg1.update();
			bg2.update();
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
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		
		ArrayList projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), 10, 5);
		}
		g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
		g.drawImage(heliboy, hb.getCenterX() - 48, hb.getCenterY() - 48, this);
		g.drawImage(heliboy, hb2.getCenterX() - 48, hb2.getCenterY() - 48, this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (robot.isJumped() == false) {
				robot.setDucked(true);
				robot.setSpeedX(0);
			}
			break;

		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;

		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			robot.setMovingRight(true);	
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
			currentSprite = character;
			robot.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			robot.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			robot.stopRight();
			break;

		case KeyEvent.VK_SPACE:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

}
