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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        VARIABLES
         */

        TextView result = findViewById(R.id.result);
        Button ac = findViewById(R.id.buttonAC);

        final ArrayList<Integer> numberIDs = new ArrayList<Integer>() {
            {
                add(R.id.button1);
                add(R.id.button2);
                add(R.id.button3);
                add(R.id.button4);
                add(R.id.button5);
                add(R.id.button6);
                add(R.id.button7);
                add(R.id.button8);
                add(R.id.button9);
                add(R.id.button0);
            }
        };
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


        //registers listener for all numbers
        for (int i : numberIDs) {
            Button button = findViewById(i);
            int number;

            try {
                number = Integer.parseInt(button.getText().toString());
            } catch (Exception x) {
                throw new Error(x.fillInStackTrace());
            }

            //when button is clicked add the number to the sequence
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //add number to end of current number
                    currentNumber += number;

                    sequence.add(currentNumber);

                    //display number in result
                    displayResult(result);
                }
            });
        }


        for (int i : operatorIDs) {
            Button button = findViewById(i);

            //when button is clicked add the number to the sequence
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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