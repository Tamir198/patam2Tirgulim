package exam2.ParallelStreamer__Pearson;

public class Q2 {


    // the original code:
	public static double avg(double[] x){
		double sum=0;
		for(int i=0;i<x.length;i++)
			sum+=x[i];
		return sum/x.length;
	}

	public static double pearson(double[] x,double[] y){
		if(x.length==y.length){
			double sumXY=0, sumX=0, sumY=0, avgX=0,avgY=0;
			avgY = avg(y);
			avgX = avg(x);
			for(int i=0;i<x.length;i++){
				sumXY+=(x[i]-avgX)*(y[i]-avgY);
				sumX+=Math.pow((x[i]-avgX),2);
				sumY+=Math.pow((y[i]-avgY),2);
			}
			return sumXY/Math.sqrt(sumX*sumY);
		}
		return 0;
	}





	public static void warmup() {
		double arr[] = {1,2,3,4};

		for (int i = 0; i < 10000; i++) {
			pearson(arr,arr);
		}
	}
}
