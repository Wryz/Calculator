package com.myphung.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //string of numbers and operators
    //EX. [1231], [+], [521]
    private static ArrayList<String> sequence = new ArrayList<>();

    /**
     * Clears the current sequence cache
     */
    public static void clearSequence() {
        sequence.clear();
    }

    /**
     * Adds an element to the current sequence cache
     * @param str Sequence element to be added
     */
    public static void addSequence(String str) {
        sequence.add(str);
    }

    /**
     * Adds an element to the current sequence cache
     * @return the cached sequence
     */
    public static ArrayList<String> getSequence() {
        return sequence;
    }

    //current number to be added to the sequence
    private static String currentNumber = "";

    /**
     * Returns the current number
     * @return currentNumber
     */
    public static String getCurrentNumber() {
        return currentNumber;
    }

    /**
     * Clears the current number cache
     */
    public static void clearCurrentNumber() {
        currentNumber = "";
    }

    /**
     * Adds the digit "num" to the end of the current number
     * if "num" is an integer
     * @param num digit to add
     */
    public static void addCurrentNumber(String num) {
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
    public static void addCurrentNumber(Integer num) {
        currentNumber += num;
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


        //registers the listener for all number buttons
        for (Integer id: NumberButton.numberIDs) {

            Button button = findViewById(id);
            NumberButton numberButton = new NumberButton(button, result);

            button.setOnClickListener(numberButton.getClickAction());
        }

        //registers the listener for all operator buttons
        for (Integer id: OperatorButton.operatorIDs) {

            Button button = findViewById(id);
            OperatorButton operatorButton = new OperatorButton(button, result);

            button.setOnClickListener(operatorButton.getClickAction());
        }

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.clearSequence();
                MainActivity.clearCurrentNumber();
                result.setText(String.valueOf(0));
            }
        });



    }

}