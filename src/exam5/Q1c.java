package exam5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
public class Q1c {

	public static <T> T recThis(ExecutorService es, T[] array, int start, int end, BinaryOperator<T> f) throws Exception{
        if(end-start==2)
            return f.apply(array[start],array[start+1]);

        int mid=start+(end-start)/2;
        Future<T> left=es.submit(()->recThis(es, array, start, mid, f));
        T right=recThis(es, array, mid, end, f);
        return f.apply(left.get(), right);
	}
}
