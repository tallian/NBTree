package railway;

public class Turnstile implements Runnable {
	Controller controller;
	Boolean stop;
	Integer index;

	Turnstile (Controller cont, int ind){
		controller = cont;
		stop = false;
		index = ind;
	}
	public void callController(Integer tourist) throws InterruptedException {
		synchronized (this) {
			System.out.println("Турист № " + tourist + " отметился в турникете № "+index);
			controller.add(tourist);
		}
	}
	
	public void run() {
		System.out.println("Турникет инициализирован.");
		while (!stop) {
			
		}
	}
}
