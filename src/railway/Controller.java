package railway;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Controller implements Runnable {
	Cart cart;
    boolean stop;
    Lock lock = new ReentrantLock();
    Integer i;
	BlockingQueue<Tourist> tourists = new ArrayBlockingQueue<Tourist>(100);
	
	static Semaphore bisy = new Semaphore(1);

	Controller(Cart c) {
		cart = c;
		stop = false;
		i = 0;
	}
	
	public void add() throws InterruptedException {
		synchronized (this) {
			bisy.acquire();
			i++;
			System.out.println("Турист зарегистрирован контролёром, туристов: "+ i);
			bisy.release();
		}
	}
	@Override
	public void run() {
		do { 
			synchronized (this) {
				if (i>=5 && !cart.moving) {
					i-=5;
					System.out.println("Пять туристов погрузилось, туристов осталось: "+ i);
					cart.start();
				}
			}
		} while (!stop);
	}

}
