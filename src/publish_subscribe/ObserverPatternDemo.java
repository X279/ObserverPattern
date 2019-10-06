package publish_subscribe;

public class ObserverPatternDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Publish publisher = new Publish();
		Subscriber01 subscriber01 = new Subscriber01(publisher);
		Subscriber02 subscriber02 = new Subscriber02(publisher);
		Subscriber03 subscriber03 = new Subscriber03(publisher);
		
		publisher.start();
		subscriber01.start();
		subscriber02.start();
		subscriber03.start();
	}
}
