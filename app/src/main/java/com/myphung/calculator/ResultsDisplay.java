package com.myphung.calculator;

public class ResultsDisplay {

    /**
     * Called before the action of every button
     * Uhhh... I have no idea why this is necessary
     */
    public void displayResult() {

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
     * Calculate the result of two numbers and a string operator
     * @param numOne first number
     * @param operator operator
     * @param numTwo second number
     * @return the result of the arithmetic
     */
    private double getResult(double numOne, String operator, double numTwo) {
        double result = 0;
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
