package kiloboltgame;

import java.applet.Applet;

public class StartingClass extends Applet implements Runnable {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
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
		// TODO Auto-generated method stub
		
	}

}
