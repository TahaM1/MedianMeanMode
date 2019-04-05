import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int[] num = {3,4,6,8,6,4,3};
        mode(num);

        System.out.println(mode(num));


    }

    public static ArrayList<Integer> mode(int[] array){
        sortArray(array);
        int currentNum = array[0];
        int[] recurringValues = new int[array.length];
        int recurringNumberIndex = 0;

        for(int i = 0; i < array.length; i++){
            if(array[i] == currentNum){
                recurringValues[recurringNumberIndex] += 1;
            } else {
                recurringNumberIndex = i;
                currentNum = array[i];
                recurringValues[recurringNumberIndex] = 1;
            }
        }

      ArrayList<Integer> values = new ArrayList<Integer>();

        int tempIndex = 0;
        for(int i = 0; i < recurringValues.length; i++){
            if(recurringValues[tempIndex] < recurringValues[i]){
                tempIndex = i;
            }
        }

        for(int i = 0; i < recurringValues.length; i++){
            if(recurringValues[tempIndex] == recurringValues[i]){
                values.add(array[i]);
            }
        }

       // System.out.println(Arrays.toString(recurringValues));
        return values;
    }

    public static double medianOfArray(int[] array){
        sortArray(array);
        if((array.length % 2) == 0){
            double average = (array[array.length/2-1] + array[array.length/2])/2;
            return average;
        } else {
            return array[array.length/2];
        }

    }

    //calculates average of ints in an array and returns it
    public static double meanOfArray(int[] array){
        double sum = 0;
        for(int number : array){
            sum += number;
        }

        return sum/array.length;

    }

    //generates array of ints given the max number and number of values
    public static int[] RandomArray(int max, int arrayLength){
        int[] array = new int[arrayLength];

        for(int i = 0; i < array.length; i++){
            array[i] = (GenerateNum(max));
        }

        return array;

    }

    //generates number from 1 to max number given
    public static int GenerateNum(int max){
        return (int)(Math.random()*max)+1;
    }

    //sorts array from smallest to greatest
    public static void sortArray(int[] array){

        boolean sorted =  false;

        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int smallTemp = array[i + 1];
                    int bigTemp = array[i];
                    array[i + 1] = bigTemp;
                    array[i] = smallTemp;
                    sorted = false;
                }
            }
        }

    }


}
