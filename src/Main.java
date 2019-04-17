// Mean, Median, and Mode Calculator
// Taha M.
// April 16, 2019 Version 1.0

// The purpose of this program is to randomly generate an integer array
// based on the length and maximum possible number supplied by the user.
// The user can then find the mean, median or mode of this array using the console.
// The user can also generate a new array and do the same for that as well.

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to statsCalc. In this program you can randomly fill an array and find the\n" +
                "the mean, median and mode.");
        String userInput = "";
        Boolean ProgramOn = true;

        int[] array = new int[0];

        while (ProgramOn){

            System.out.print("\nDo you want create a new array? Type 'yes' or 'no' to use old array. Type 'stop' to end the program. ");
            userInput = scan.next();

            //this creates/overwrites an array
            if(userInput.toLowerCase().contains("yes")){
                System.out.print("What do you want the length of your array to be? ");
                int length = scanUserInt();
                System.out.print("What do you want the maximum number in your array to be? ");
                int max = scanUserInt();
                array = RandomArray(max, length); //generates array with user input
                System.out.println("Your array is: " + sayArray(array)); //prints array

                System.out.println("\nType 'MEDIAN' to find the median of an array." + "\nType 'MEAN' to find the mean of an array."
                        + "\nType 'MODE' to find the mode of an array.");

                //this scans the console for any of the words in the String array
                switch(scanUserString(new String[] {"mean","median","mode"})){
                    case 0:
                        System.out.println("\nThe mean is " + meanOfArray(array) + ".");
                        break;
                    case 1:
                        System.out.println("\nThe median is " + medianOfArray(array) + ".");
                        break;
                    case 2:
                        System.out.println("\nThe mode is " + mode(array) + ".");
                        break;
                }

            } else if(userInput.toLowerCase().contains("stop")){ //this stops the while loop when it checks the condition
                ProgramOn = false;
            } else if(userInput.toLowerCase().contains("no")){
                if(array.length == 0){ //the only reason the length would be 0 is if the array wasn't randomly generated
                    System.out.println("You haven't created an array yet!");
                } else { //runs if an array has already been made

                    System.out.println("\nType 'MEDIAN' to find the median of an array." + "\nType 'MEAN' to find the mean of an array."
                            + "\nType 'MODE' to find the mode of an array.");

                    //scans console for words in the String array below
                    switch(scanUserString(new String[] {"mean","median","mode"})){
                        case 0:
                            System.out.println("\nThe mean is " + meanOfArray(array) + ".");
                            break;
                        case 1:
                            System.out.println("\nThe median is " + medianOfArray(array) + ".");
                            break;
                        case 2:
                            System.out.println("\nThe mode is " + mode(array) + ".");
                            break;
                    }
                   // System.out.println("You didn't type mean, median, or mode! Ya goof.");
                }

            } else {
                System.out.println("That wasn't a valid option. Try again.");
            }
        }

        System.out.println("Thank you, come again.");


    }

    //prints an int array in a clean fashion
    public static String sayArray(int[] array){
        String word = "[";
        for(int i = 0; i < array.length; i++){
            if(i < array.length-1){ //this is here so the last num doesnt get a comma
                word += array[i] + ", ";
            } else {
                word += array[i];
            }
        }
        word += "]";
        return word;

    }

    //scans any ints the user types into the console and catches any errors before returning input
    public static int scanUserInt(){
        int input = 0;

        boolean inputIsAnInt = false; //used to end while loop when the input is an integer
        while(!inputIsAnInt){

            inputIsAnInt = true;
            //catches any exceptions
            try{
                input = scan.nextInt();

            } catch (InputMismatchException error){
                inputIsAnInt = false;
                System.out.print("That's not an Integer! Try again. ");
                scan.next();
                input = 0;
            } catch (Exception error){
                inputIsAnInt = false;
                System.out.print("Something went wrong. Try again. ");
                scan.next();
                input = 0;
            }
            //if input is negative it tells user to try again
            if(input <= -1){
                System.out.print("That's a negative number! Try a positive. ");
                inputIsAnInt = false;
            }
        }

        return input;


    }

    //scans console for any strings in the array then returns the index of the string that matches (I use this for switch statements)
    public static int scanUserString(String[] array){
        String input = "";

        while(true){
            input = scan.next();
            for(int i = 0; i < array.length; i++){
                if(input.toLowerCase().contains(array[i].toLowerCase())){
                    return i;
                }
            }
            System.out.print("That is not a valid option. Try again. ");
        }


    }

    //takes an int array and returns the mode(s) in an arraylist
    public static ArrayList<Integer> mode(int[] array){
        sortArray(array);
        int currentNum = array[0]; //the num being compared
        int[] recurringValues = new int[array.length]; //array to keep track of the counts
        int recurringNumberIndex = 0; //used to remember where the counts go in the reccuringValues array

        //compares the current number to the elements in the array
        for(int i = 0; i < array.length; i++){
            //if the values match, 1 is added to recurringValues using recurringIndex
            if(array[i] == currentNum){
                recurringValues[recurringNumberIndex] += 1;
            } else { //if not, the index and currentNumber change to the new number 
                recurringNumberIndex = i;
                currentNum = array[i];
                recurringValues[recurringNumberIndex] = 1;
            }
        }

        ArrayList<Integer> values = new ArrayList<Integer>();
        int tempIndex = 0;

        //goes through the array and finds the highest numbers index
        for(int i = 0; i < recurringValues.length; i++){
            if(recurringValues[tempIndex] < recurringValues[i]){
                tempIndex = i;
            }
        }

        // uses the highest number index to add the most recurring numbers to the arraylist
        for(int i = 0; i < recurringValues.length; i++){
            if(recurringValues[tempIndex] == recurringValues[i]){
                values.add(array[i]);
            }
        }


        return values;
    }

    //takes an int array and returns its median
    public static double medianOfArray(int[] array){
        sortArray(array);

        //finds median based on whether the # of elements is odd or even
        if((array.length % 2) == 0){
            //finds the 2 middle numbers and returns their average
            double average = (array[array.length/2-1] + array[array.length/2])/2.0;
            return average;
        } else {
            //returns number in the middle of array
            return array[array.length/2];
        }

    }

    //calculates average of ints in an array and returns it
    public static double meanOfArray(int[] array){

        return sumOfArray(array)/array.length;

    }

    //finds the sum of an int array
    public static double sumOfArray(int[] array){
        double sum = 0;
        for(int number : array) {
            sum += number;
        }
        return sum;
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

    //sorts array from smallest to greatest using bubble sort
    public static void sortArray(int[] array){

        boolean sorted =  false;

        while(!sorted) {
            sorted = true;
            //swaps neighbouring numbers if one is greater than other
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
