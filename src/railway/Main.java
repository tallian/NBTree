package railway;
import java.util.ArrayList;
import java.util.Random;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		Cart cart =  new Cart();
		Controller controller = new Controller(cart);
		cart.addCont(controller);
		
		Thread contr = new Thread(controller);
		Thread car = new Thread(cart);
		
		contr.start();
		car.start();
		
		ArrayList<Turnstile> t = new ArrayList<Turnstile>();
		t.add(new Turnstile(controller));
		t.add(new Turnstile(controller));
		t.add(new Turnstile(controller));
		
		ArrayList<Thread> tu = new ArrayList<Thread>();
		tu.add(new Thread(t.get(0)));
		tu.add(new Thread(t.get(1)));
		tu.add(new Thread(t.get(2)));
		tu.get(0).start();
		tu.get(1).start();
		tu.get(2).start();
		
		Thread.sleep(1000);
		
		ArrayList<Tourist> to = new ArrayList<Tourist>();
		ArrayList<Thread> tou = new ArrayList<Thread>();
		for (int i=0; i<15; i++) {
			Random rand = new Random();
			int j = rand.nextInt(3);
			to.add(new Tourist(t.get(j), i));
			tou.add(new Thread(to.get(i)));
			tou.get(i).start();
		}
		
		Thread.sleep(10000);
		
		t.get(0).stop = true;
		t.get(1).stop = true;
		t.get(2).stop = true;
		
		controller.stop = true;
		cart.stop = true;
		
		return;
	}

}
