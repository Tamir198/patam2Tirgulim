package exam4;

//package test
public class Q2 {

    static double avg(double[] x) {
        double sum = 0;
        for (int i = 0; i < x.length; i++)
            sum += x[i];
        return sum / x.length;
    }

    static double[] averageCols(double[][] data) {
        //IF you want to calculate average
        double avg[] = new double[data[0].length];
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                avg[col] += data[row][col];
            }
        }
        for (int i = 0; i < avg.length; i++){
            avg[i] /= data.length;
        }
        return avg;
    }


}
