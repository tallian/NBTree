package railway;
import java.util.concurrent.Semaphore;

public class Turnstile implements Runnable {
	Controller controller;
	Boolean stop;
	static Semaphore bisy = new Semaphore(1);

	Turnstile (Controller cont){
		controller = cont;
		stop = false;
	}
	public void callController() throws InterruptedException {
			bisy.acquire();
			System.out.println("������ ��������� � ���������");
			controller.add();
			bisy.release();
	}
	
	public void run() {
		System.out.println("�������� ���������������.");
		while (!stop) {
			
		}
	}

}
