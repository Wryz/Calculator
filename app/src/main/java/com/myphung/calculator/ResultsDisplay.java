package com.myphung.calculator;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsDisplay {

    /**
     * Set the proper text display for some cases
     */
    public void displayResult(TextView view) {

        view.setText(getSequenceResult(MainActivity.getSequence()));
    }


    private String getSequenceResult(String sequence) {
        //gets at max three parts from the sequence
        //first part is first number before the operator
        //second part is the operator
        //third part is the number after the operator and before the second operator

        ArrayList<Integer> operatorIndexes = new ArrayList<>();
        String firstNumber = sequence;
        String operator = "";
        String secondNumber = "";

        Log.i("sequence", sequence);

        //adds the location of all operators to use as reference points to find the numbers
        for (Character c : sequence.toCharArray()) {
            if (isOperator(String.valueOf(c))) {
                operatorIndexes.add((sequence.indexOf(c)));
            }
        }

        try {
            firstNumber = sequence.substring(0, operatorIndexes.get(0));
            operator = String.valueOf(sequence.charAt(operatorIndexes.get(0)));
            secondNumber = sequence.substring(operatorIndexes.get(0)+1, sequence.length());

        } catch (Exception x) {

        }
        Log.i("firstNumber", firstNumber);
        Log.i("operator", operator);
        Log.i("secondNumber", secondNumber);


        Log.i("operatorAmt", String.valueOf(operatorIndexes.size()));


        //CHECKS
        if (operatorIndexes.size() == 0) {
            //returns the current number being inputted
            return sequence;
        } else if (operatorIndexes.size() == 1) {

            if (secondNumber.length() == 0) {
                return sequence;
            } else {
                return secondNumber;
            }

        }
        try {
            return getResult(
                    Float.parseFloat(
                            sequence.substring(0, operatorIndexes.get(0))
                    ),
                    String.valueOf(sequence.charAt(operatorIndexes.get(0))),
                    Float.parseFloat(
                            sequence.substring(operatorIndexes.get(0),operatorIndexes.get(1))
                    ));
        } catch (Exception x) {
            return "hi";
        }

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
        try {
            return String.valueOf(Integer.parseInt(String.valueOf(result)));
        } catch (Exception x) {
            return String.valueOf(result);
        }
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
