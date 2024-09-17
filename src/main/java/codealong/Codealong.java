package codealong;

import java.util.Scanner;

public class Codealong {

    //Getting first letter of operator from word
    static char opCodeFromString(String operationName){

        //grabs the first letter of a typed operation; ex multiply  -> 'm', add -> 'a'
        char opCode = operationName.charAt(0); //character positions are similar to indexes; start at 0

        return opCode;
    }

    //Getting the numeric value from word
    static double valueFromWord(String word){

        //written numbers to match the index/position
        String[] numberOfWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        double value = 0d;

        for(int i = 0; i < numberOfWords.length; i++){ //this loops through the array to check if the word matches any of the words in the array

            if(word.equals(numberOfWords[i])){ //if so, switch the word value with its numeric value; i
                value = i;
                break;
            }
        }

        return value;
    }

    static void executeInteractively() {

        System.out.println("Enter an operation and two numbers: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        String[] parts = userInput.split(" "); //Will split the string according to spaces; multiply 2 4 will be separated as 3 separate parts
        performOperation(parts);


    }

    private static void performOperation(String[] parts){

      char opCode = opCodeFromString(parts[0]);
      double leftNum = valueFromWord(parts[1]);
      double rightNum = valueFromWord(parts[2]);
      double result = execute(opCode, leftNum, rightNum);

      displayResult(opCode, leftNum, rightNum, result);

    }

    private static void displayResult(char opCode, double leftNum, double rightNum, double result) {

    char symbol = symbolFromOpCode(opCode);
    StringBuilder builder = new StringBuilder(20);
    builder.append(leftNum);
    builder.append(" ");
    builder.append(symbol);
    builder.append(" ");
    builder.append(rightNum);
    builder.append(" = ");
    builder.append(result); //String builder does the conversion to a string for you
    String output = builder.toString();

    System.out.println(output);

    }

    static double execute(char opCode, double leftNum, double rightNum){

        double result = 0.0;

        switch (opCode) {

            case 'a':
                result = leftNum + rightNum;
                break;

            case 's':
                result = leftNum - rightNum;
                break;

            case 'm':
                result = leftNum * rightNum;
                break;

            case 'd':
                result = leftNum / rightNum;
                break;
        }

        return result;
    }

    private static char symbolFromOpCode(char opCode) {
        char[] opCodes = {'a', 's', 'm', 'd'};
        char[] symbols = {'+', '-', '*', '/'}; // Parallel arrays, the values correspond
        char symbol = ' ';

        for (int index = 0; index < opCodes.length; index++) {
            if (opCode == opCodes[index]) {
                symbol = symbols[index];
                break;
            }
        }

        return symbol;
    }



    public static void main(String[] args) {

        //String Class---------------------------------------------------------------------------------------

        //String s in java can store almost any character you are likely to work with; Unicode characters

        String name = "Treasure";

        //Use concatonation using operators + and +-;
            // Using += will add the new value to the String variable IN ADDITION to what is already stored

        name += " Davis"; //" Davis" is added to name, so name now is "Treasure Davis"

        //Strings are immutable - the value of the string cannot be directly changed
            //Changes to the value creates a new instance of the String
            //The variable will update to the location of the new made instance of the variable


        //String Equality----------------------------------------------------------------------------------------------

        String s1 = "I love";
        s1 += " Java";

        String s2 = "I";
        s2 += " love Java";

        // You cannot use the == to compare the value of Strings, it instead checks the reference of the String instance
            //Instead you have to use the .equals method

        if(s1 == s2){  //THIS IS WRONG
            System.out.println("Equals!");
        }

        if (s1.equals(s2)){ //THIS IS RIGHT
            System.out.println("Equals!");
        }

        //String interning is used when you want to frequently check the equality of a String
            //Provides a canonicalized value, a string of any given value will always return back a reference to the same string instance.
            //This allows us to use the == operator

        String s3 = s1.intern(); //Interning a String

        //What the intern method does is it looks at the value of the string, and it looks around to see if there's already an intern version of that string.
        //So then s3 is set to reference the intern version of the string.

        String s4 = s2.intern();
        //the intern method will look at the value of the string that s2 references, and it'll look around for an intern version of that string. It would find the value matches s3 intern
        //It will reference the same instance as s3

        if(s3 == s4){  //THIS WILL NOW WORK
            System.out.println("Equals!");
        }

        //Only intern when you frequently need to compare strings


        //String Methods and String Conversions--------------------------------------------------------------------------------------------------------------------------------------------------

        //String methods allow you to operate on the data (ex: length, equals, toUpperCase, toLowerCase, trim, etc)
        //Converting non string types to a string can be done by using the String.valueOf method

        //example 1
        int number = 100;
        String stringNumber = String.valueOf(number); //Converts the int into a string value; "100"

        //example 2 - convert output to a string
        int x = 2, y = 3;
        int result = x * y; //6
        String output = x + "*" + y + "=" + result; //the compiler will automatically convert each of those integer values into their string representation.

        System.out.println(output);


        //Adding String Support to CalcEngine------------------------------------------------------------------------------------------------------------------------------------------------------

            double[] leftVals = {100.0d, 25.0d, 225.0d, 11.0d};
            double[] rightVals = {50.0d, 92.0d, 17.0d, 3.0d};
            char[] opCodes = {'d', 'a', 's', 'm'};
            double[] results = new double[opCodes.length];

            if (args.length == 0) {
                for (int i = 0; i < opCodes.length; i++) {
                    results[i] = execute(opCodes[i], leftVals[i], rightVals[i]);
                }

                for (double currentResult : results) {
                    System.out.println(currentResult);
                }
            } else if (args.length == 1 && args[0].equals("interactive")) {

                executeInteractively();

            }else {
                System.out.println("Please provide an operation code and 2 numeric values");
            }


        //String Builder------------------------------------------------------------------------------------------------------------------------------------------------------

        //Allows you to construct string values piece by piece
            //add new content with append, add new content within with insert

        String location = "Florida";
        int flightNum = 175;

        StringBuilder sb = new StringBuilder(40); //Builds string piece by piece
        sb.append("I flew to ");
        sb.append(location);
        sb.append(" on Flight #");
        sb.append(flightNum);

        String message = sb.toString(); // "I flew to Florida on Flight #175"
        System.out.println(message);

        String time = "9:00";
        int pos = sb.indexOf(" on"); //Gives the index of the selected content
        sb.insert(pos, " at ");
        sb.insert(pos + 4, time);
        message = sb.toString(); // "I flew to Florida at 9:00 on Flight #175
        System.out.println(message);

    }

}
