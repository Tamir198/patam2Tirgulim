package test;

import java.util.concurrent.RecursiveTask;

public class FJ_GCD extends RecursiveTask<Integer>{

	private static final long serialVersionUID = 1L; // Ignore
	
	private int a;
	private int b;

	public FJ_GCD(int a, int b) {
		this.a=a;
		this.b=b;
	}
	
	@Override
	protected Integer compute() {
		if(b==0)
			return a;
		FJ_GCD gcd=new FJ_GCD(b, a%b);
		gcd.fork();
		return gcd.join();
	}

	
}
