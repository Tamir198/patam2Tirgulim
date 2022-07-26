package exam11;

import java.util.List;
import java.util.function.BinaryOperator;

public class Q3good<V> {

	List<V> data;
	V result;
	public Q3good(List<V> data) {
		this.data=data;
	}

	public V fold(V identity,BinaryOperator<V> acc) {
		result=identity;
		for(V v : data) {
			result=acc.apply(result, v);
		}
		return result;
	}
}
