package exam6;


import java.util.concurrent.ConcurrentHashMap;

public class Singleton {


	//This will work with normal map as well
	static ConcurrentHashMap<String, Object> map=new ConcurrentHashMap<>();

	public static <V> V getInstance(Class<V> c) throws Exception{
		if(!map.containsKey(c.getName())){
			//synchronized == only one thread can access this block at the same time
			synchronized (map) {
				if(!map.containsKey(c.getName())){
					map.put(c.getName(), c.newInstance());
				}
			}
		}
		return (V)map.get(c.getName());
	}
}
