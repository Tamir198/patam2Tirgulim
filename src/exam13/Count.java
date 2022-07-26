package exam13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Count {
	ArrayList<AtomicInteger> countArr;


	public Count(int size) {
		countArr= new ArrayList<>(size);

		for (int i = 0; i < size; i++)
			countArr.add(new AtomicInteger(0));
	}
	
	public  void inc(){
		for (int i = 0; i < countArr.size(); i++)
			countArr.get(i).incrementAndGet();
	}
	
	public  void dec() {
		for (int i = 0; i < countArr.size(); i++)
			countArr.get(i).decrementAndGet();

	}
	
	public int get(int index) {	
		return countArr.get(index).get();
	}
}
