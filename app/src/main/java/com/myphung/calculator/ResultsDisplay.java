package com.myphung.calculator;

import android.widget.TextView;

import java.util.ArrayList;

public class ResultsDisplay {

    /**
     * Called before the action of every button
     * Uhhh... I have no idea why this is necessary
     */
    public void displayResult(TextView view) {
        view.setText(getSequenceResult(MainActivity.getSequence()));
    }

    /**
     * Recursive method to get the result of the sequence
     * @param sequence array list of strings containing numbers or operators
     * @return the product of all the arithmetic
     */
    private String getSequenceResult(ArrayList<String> sequence) {
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

                sequence.set(2, String.valueOf(result)); //sets index 2 to result
                sequence.remove(0);
                sequence.remove(0);
                return getSequenceResult(sequence);
            } catch (Exception x) {
                throw new Error(x.fillInStackTrace());
            }

        } else if (!sequence.isEmpty()) {
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
            case "=":
                result = Float.parseFloat(getSequenceResult(MainActivity.getSequence()));
        }
        return result;
    }
}
