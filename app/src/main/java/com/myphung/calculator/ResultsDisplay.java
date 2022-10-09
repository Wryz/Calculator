package com.myphung.calculator;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsDisplay {

    /**
     * Set the proper text display for some cases
     */
    public void displayResult(TextView view) {
        String sequence = MainActivity.getSequence();
        sequence = filter(sequence);
        view.setText(getSequenceResult(sequence));
    }


    //TODO fix crash for when adding integer to end of negative value

    private String filter(String sequence) {

        String lastCharacter = String.valueOf(sequence.charAt(sequence.length()-1));

        //check if last character is a period, and search for other periods in sequence
        //if so remove the period

        StringBuilder editedSequence = new StringBuilder(sequence);

        int decimalCounter = 0;


        if (lastCharacter.equals("=")) {
            boolean removeEqualSign = true;
            for (int index = 0; index < sequence.length(); index++) {
                Character c = sequence.charAt(index);

                //checks if there is another operator in the sequence (excludes = sign at end)
                if (isOperator(String.valueOf(c)) && index != sequence.length()-1) {
                    removeEqualSign = false;
                    break;
                }
            }
            if (removeEqualSign) {
                editedSequence.deleteCharAt(editedSequence.length()-1);
            }
        }

        for (int index = 0; index < sequence.length(); index++) {

            String c = String.valueOf(sequence.charAt(index));

            /*
            finding excess decimal points
            skips the first decimal point
            */
            if(c.equals(".")) {
                if(decimalCounter > 1) {
                    editedSequence.deleteCharAt(index);
                } else {
                    decimalCounter += 1;
                }
            }



        }


        return sequence;
    }

    private String getSequenceResult(String sequence) {
        //gets at max three parts from the sequence
        //first part is first number before the operator
        //second part is the operator
        //third part is the number after the operator and before the second operator
        Log.i("border", "--------------------");

        ArrayList<Integer> operatorIndexes = new ArrayList<>(0);
        String firstNumber = sequence;
        String operator = "";
        String secondNumber = "";

        //adds the location of all operators to use as reference points to find the numbers

        for (int index = 0; index < sequence.length(); index++) {

            Character c = sequence.charAt(index);
            Log.i("c", String.valueOf(c));
            Log.i("index", String.valueOf(index));
            if (isOperator(String.valueOf(c))) {

                if (isOperator(String.valueOf(sequence.charAt(index-1)))) {

                    //take the previous operator out
                    sequence = sequence.replace(sequence.charAt(index-1), c);
                    sequence = sequence.substring(0, sequence.length()-1);

                } else {
                    operatorIndexes.add((index));
                }

            }

        }
        MainActivity.setSequence(sequence);
        Log.i("sequence", sequence);

        try {
            Log.i("operators", operatorIndexes.toString());

            firstNumber = sequence.substring(0, operatorIndexes.get(0));
            operator = String.valueOf(sequence.charAt(operatorIndexes.get(0)));
            if (operatorIndexes.size()>1) {
                secondNumber = sequence.substring(operatorIndexes.get(0)+1, sequence.length()-1);

            } else {
                secondNumber = sequence.substring(operatorIndexes.get(0)+1, sequence.length());
            }
        } catch (Exception x) {

        }
        Log.i("firstNumber", firstNumber);
        Log.i("operator", operator);
        Log.i("secondNumber", secondNumber);


        Log.i("operatorAmt", String.valueOf(operatorIndexes.size()));


        //if there are two operators then there is a completed statement
        try {
            String result = getResult(
                    Float.parseFloat(firstNumber),
                    operator,
                    Float.parseFloat(secondNumber));

            String newSequence = result+ sequence.charAt(operatorIndexes.get(1));
            if (sequence.contains("=")) newSequence = result;

            Log.i("formattedNumber", result);
            Log.i("operatorIndex2", String.valueOf(sequence.charAt(operatorIndexes.get(1))));

            MainActivity.setSequence(newSequence);
            sequence = newSequence;

        } catch (Exception ignored) {}
        return sequence;
    }

    /**
     * Calculate the result of two numbers and a string operator
     * @param numOne first number
     * @param operator operator
     * @param numTwo second number
     * @return the result of the arithmetic
     */
    private String getResult(float numOne, String operator, float numTwo) {
        float result = 0;
        switch (operator) {
            case "*":
                result = numOne * numTwo;
                break;
            case "/":
                result = numOne / numTwo;
                break;
            case "+":
                result = numOne + numTwo;
                break;
            case "-":
                result = numOne - numTwo;
                break;
            case "%":
                result = numOne % numTwo;
                break;
            case "=":
                result = Float.parseFloat(getSequenceResult(MainActivity.getSequence()));
        }
        return MainActivity.getFormattedNumber(String.valueOf(result));
    }

    public boolean isOperator(String operator) {

        ArrayList<String> operators = new ArrayList<String>() {
            {
                add("*");
                add("/");
                add("+");
                add("-");
                add("%");
                add("=");
            }
        };

        return operators.contains(operator);
    }
}
