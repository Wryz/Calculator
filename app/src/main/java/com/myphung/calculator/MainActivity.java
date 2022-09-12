package com.myphung.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //string of numbers and operators
    //EX. [1231], [+], [521]
    public static ArrayList<String> sequence = new ArrayList<>();

    //current number to be added to the sequence
    public static String currentNumber = "";

    private void clearSequence() {
        sequence.clear();
    }

    private void addSequence(String str) {
        sequence.add(str);
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    /**
     * Adds the digit "num" to the end of the current number
     * if "num" is an integer
     * @param num digit to add
     */
    public void addCurrentNumber(String num) {
        try {
            Integer.parseInt(num);
        } catch (Exception x) {
            throw new Error(x.fillInStackTrace());
        }
        currentNumber += num;
    }

    /**
     * Adds digit "num" to the end of the current number
     * @param num digit to add
     */
    public void addCurrentNumber(Integer num) {
        currentNumber += num;
    }

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

    //use recursion to loop through sequence

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        VARIABLES
         */

        TextView result = findViewById(R.id.result);
        Button ac = findViewById(R.id.buttonAC);

        final ArrayList<Integer> operatorIDs = new ArrayList<Integer>() {
            {
                add(R.id.buttonNegPos);
                add(R.id.buttonMod);
                add(R.id.buttonDiv);
                add(R.id.buttonMulti);
                add(R.id.buttonSub);
                add(R.id.buttonAdd);
                add(R.id.buttonEqual);
            }
        };


        NumberButtons numberButtons = new NumberButtons();

        for (Integer id: NumberButtons.numberIDs) {
            Button button = findViewById(id);
            button.setOnClickListener(numberButtons.getClickAction(id));
        }


        /*
        for (int i : operatorIDs) {
            Button button = findViewById(i);

            //when button is clicked add the number to the sequence
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sequence.add(currentNumber);
                    sequence.add(button.getText().toString());
                    currentNumber = "";

                    displayResult(result);
                }
            });
        }

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sequence.clear();
                result.setText(String.valueOf(0));
            }
        });
         */


    }

    /**
     * Displays the result of the sequences in the results view
     */
    public void displayResult(TextView view) {

        //result
        int resultingNumber = 0;
        if (sequence.size() >2) resultingNumber = Integer.parseInt(sequence.get(0));

        //previous number
        
        int num = 0;

        //iterate through each element to produce a resulting number

        /*
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

         */
        for (int i = 0; i<sequence.size(); i++) {

            Log.i("TAG", "Sequence "+sequence);

            Log.i("TAG", "ResultingNumber "+resultingNumber);

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
        }
    }
}