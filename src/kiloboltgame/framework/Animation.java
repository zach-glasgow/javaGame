package kiloboltgame.framework;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {

	// It contains AnimFrames objects.
	private ArrayList frames;
	// It refers to the integer value index of the current frame in the
	// ArrayList.
	private int currentFrame;
	// long because it can hold more accurate numbers
	// It will keep track of how much time has elapsed since the current image
	// was displayed.
	private long animTime;
	// It refers to the amount of time that each frame will be displayed for.
	private long totalDuration;

	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;

		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}
	}
	
	public synchronized void addFrame(Image image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}
	
	public synchronized void update(long elapsedTime) {
		if (frames.size() > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;
			}
			while (animTime > getFrame(currentFrame).endTime) {
				currentFrame++;
			}
		}
	}
	
	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}

	private AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}
	
	private class AnimFrame {
		
		Image image;
		long endTime;
		
		public AnimFrame(Image image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}

}
