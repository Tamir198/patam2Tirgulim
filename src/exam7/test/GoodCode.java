package test;

import java.awt.Point;


public class GoodCode {

	private static double EucDistance2(Point a, Point b){
		return (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y);
	}
	
	public static Point geoMedian(Point[] values){

		double[] sum=new double[values.length];
		for(int i=0;i<values.length;i++){
			for(int j=i+1;j<values.length;j++){
				double d=EucDistance2(values[i], values[j]);
				sum[i]+=d;
				sum[j]+=d;
			}
		}
		
		Point r=null;
		double min=Double.MAX_VALUE;
		for(int i=0;i<sum.length;i++)
			if(min>sum[i]){
				min=sum[i];
				r=values[i];
			}
		return r;
	}

}
