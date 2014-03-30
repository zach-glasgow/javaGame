package kiloboltgame;

public class Robot {

	// X,Y coordinates of the robot character
	private int centerX = 100;
	// 382 is the distance of the centre of the robot from the land. 440 is the whole length of Y.
	private int centerY = 382;
	private boolean jumped = false;

	// the rate at which X and Y position changes
	private int speedX = 0;
	private int speedY = 1;

	// this method is called in each iteration of loop
	public void update() {
		
		// Moves character or scrolls background accordingly
		if (speedX < 0) {
			centerX += speedX; // This changes centerX by adding speedX.
		} else if (speedX == 0) {
			System.out.println("Do not scroll the background");
		} else {
			if (centerX <=150) { // If the character's centerX is in the left 150 pixels
				centerX += speedX; // Change centerX by adding speedX.
			} else {
				System.out.println("Scroll the background here");
			}
		}
		
		// Updates the Y position
		if (centerY + speedY >=382){
		// 382 is where the character's centerX would be if he were standing on the ground.
			centerY = 382;
		} else {
			centerY += speedY; // Add speedY to centerY to determine its new position.
		}
		
		// Handles jumping
		if (jumped	 == true) {
			speedY += 1; // While the character is in the air, add 1 to speedY.
			// NOTE: This will bring the character downwards
			
			if (centerY + speedY >= 382) {
				centerY =382;
				speedY = 0;
				jumped = false;
			}
		}
		
		// Prevents going beyond X coordinate to 0
		if (centerX + speedX <= 60) { // If speedX plus speedX would bring the character outside the screen
			centerX = 61;
			// Fix the character's centerX at 60 pixels.
		}
	}
	
	public void moveRight() {
		speedX = 6;
	}
	
	public void moveLeft() {
		speedX = -6;
	}
	
	public void stop() {
		speedX = 0;
	}
	
	public void jump() {
		if (jumped == false) {
			speedY = -15;
			jumped = true;
		}
	}

}
