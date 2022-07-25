package exam9;

public class GoodCode {
    static int[] arr = new int[101];
    static int maxGrade = -1;
    public static int common(int[] grades) {
        for (int i = 0; i < grades.length ; i++) {
            arr[grades[i]]+=1;
        }

        int maxIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > maxGrade){
                maxIndex = i;
                maxGrade = arr[i];
            }
        }

        return maxIndex;

    }

}
