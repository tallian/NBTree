package railway;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller implements Runnable {
	Cart cart;
    boolean stop;
    AtomicInteger i = new AtomicInteger(0);
	//static Semaphore bisy = new Semaphore(1);

	Controller(Cart c) {
		cart = c;
		stop = false;
	}
	
	public void add(Integer tourist) throws InterruptedException {
		synchronized (this) {
			i.set(i.get()+1);
			System.out.println("Турист № " + tourist + " зарегистрирован контролёром, туристов: "+ i);
		}
		if (i.get()>=5 && !cart.moving.get()) {
			synchronized (this) {
				i.set(i.get()-5);
				System.out.println("Пять туристов погрузилось, туристов осталось: "+ i);
			}
			cart.start();
		}
	}
	
	@Override
	public void run() {
		do { 
		} while (!stop);
	}
}
