package kiloboltgame;

public class Enemy {

	private int maxHealth, currentHealth, power, speedX, centerX, centerY;
	private Background bg = StartingClass.getBg1();

	// Behavioral methods
	public void update() {
		centerX += speedX;
		speedX = bg.getSpeedX();
	}

	public void die() {

	}

	public void attack() {

	}
}
