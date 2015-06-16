package railway;
import java.util.concurrent.Semaphore;


public class Cart implements Runnable{
	
	Controller controller;
	static Semaphore isMoving = new Semaphore(1);
	boolean stop;
	boolean moving;
	
	void addCont (Controller cont) {
		controller = cont;
		stop = false;
		moving = false;
	}
	Cart () {
		
	}
	
	public void start() {
		moving = true;
		System.out.println("Тележка поехала");
		System.out.println("Тележка приехала");
		moving = false;
	}

	@Override
	public void run() {
		while (!stop) {
		}
	}

}
