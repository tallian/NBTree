package railway;
public class Tourist implements Runnable{
	Integer i;
	Turnstile t;
	
	Tourist(Turnstile tur, int ind) {
		t = tur;
		i = ind;
	}
	
	@Override
	public void run() {
		System.out.println("������ �" + i + " ������");
		try {
			t.callController();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
