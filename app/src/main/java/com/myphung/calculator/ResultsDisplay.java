package com.myphung.calculator;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsDisplay {

    /**
     * Called before the action of every button
     * Uhhh... I have no idea why this is necessary
     */
    public void displayResult(TextView view) {

        //TODO if the previous input was a number and the input before that was not a number (it was an operator) then show the sequence result

        view.setText(getSequenceResult(MainActivity.getSequence()));

        /*//result
        int resultingNumber = 0;
        if (sequence.size() > 2) resultingNumber = Integer.parseInt(sequence.get(0));

        //previous number

        int num = 0;

        //iterate through each element to produce a resulting number

        *//*
        1. User presses a number
        2. Number displays on the screen
        3. User presses an operator
        - If user presses number, add integer to end of existing number
        4. Number with the operator to the right displays on the screen
        5. User presses a number
        6. Full arithmetic expression is displayed on the screen
        7. User presses enter
        - If expression is incomplete (5 * 4 + ) disregard last operator
        8. Result of arithmetic expression is displayed on the screen

         *//*
        for (int i = 0; i < sequence.size(); i++) {

            Log.i("TAG", "Sequence " + sequence);

            Log.i("TAG", "ResultingNumber " + resultingNumber);

            try {
                //succeeds if current element in sequence is a number
                num = Integer.parseInt(sequence.get(i));

                view.setText(String.valueOf(resultingNumber));
            } catch (Exception x) {

                if (sequence.size() < 2) break;

                //check if element is an operator
                switch (sequence.get(i)) {
                    case "*":
                        resultingNumber *= num;
                        break;
                    case "/":
                        resultingNumber /= num;
                        break;
                    case "+":
                        resultingNumber += num;
                        break;
                    case "-":
                        resultingNumber -= num;
                        break;
                    case "%":
                        resultingNumber %= num;
                        break;
                }
                view.setText(String.valueOf(resultingNumber));

            }
        }*/
    }

    /**
     * Recursive method to get the result of the sequence
     * @param sequence array list of strings containing numbers or operators
     * @return the product of all the arithmetic
     */
    private String getSequenceResult(ArrayList<String> sequence) {
        Log.i("divider", "-----------------n");

        if (sequence.size() > 2) {
            float firstNumber;
            String operator;
            float secondNumber;
            float result;
            try {
                firstNumber = Float.parseFloat(sequence.get(0));
                operator = sequence.get(1);
                secondNumber = Float.parseFloat(sequence.get(2));
                result = getResult(firstNumber, operator, secondNumber);

                Log.i("firstNumber", String.valueOf(firstNumber));
                Log.i("operator", String.valueOf(operator));
                Log.i("secondNumber", String.valueOf(secondNumber));
                Log.i("result", String.valueOf(result));

                sequence.set(2, String.valueOf(result)); //sets index 2 to result
                sequence.remove(0);
                sequence.remove(0);
            } catch (Exception x) {
                throw new Error(x.fillInStackTrace());
            }

            return getSequenceResult(sequence);


        } else if (!sequence.isEmpty()) {
            Log.i("TextView Result", String.valueOf(sequence.get(0)));
            return sequence.get(0);
        } else {
            return MainActivity.getCurrentNumber();
        }

    }

    /**
     * Calculate the result of two numbers and a string operator
     * @param numOne first number
     * @param operator operator
     * @param numTwo second number
     * @return the result of the arithmetic
     */
    private float getResult(float numOne, String operator, float numTwo) {
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
        }
        return result;
    }
}
