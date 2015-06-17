package railway;
import java.util.concurrent.atomic.AtomicBoolean;


public class Cart implements Runnable{
	
	Controller controller;
	boolean stop;
	AtomicBoolean moving = new AtomicBoolean(false);
	
	void addCont (Controller cont) {
		controller = cont;
		stop = false;
	}
	Cart () {}
	
	public void start() throws InterruptedException {
		moving.set(true);
		System.out.println("Тележка поехала");
		Thread.sleep(1000);
		System.out.println("Тележка приехала");
		moving.set(false);
	}
	
	@Override
	public void run() {
		while (!stop) {
		}
	}
}
