package com.myphung.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //string of numbers and operators
    //EX. "12+321"
    private static String sequence = "";

    /**
     * Clears the current sequence cache
     */
    public static void clearSequence() {
        sequence = "";
    }

    /**
     * Clears the current sequence cache
     */
    public static void setSequence(String newSequence) {
        sequence = newSequence;
    }

    /**
     * Adds an element to the current sequence cache
     * @param str Sequence element to be added
     */
    public static void addSequence(String str) {
        sequence += str;
    }

    /**
     * Adds an element to the current sequence cache
     * @return the cached sequence
     */
    public static String getSequence() {
        return sequence;
    }

    public static String getFormattedNumber(String number) {
        int num = Math.round(Float.parseFloat(number));
        float tempNum = Float.parseFloat(number);

        if (tempNum / num == 1.0) {
            return String.valueOf(num);
        } else {
            return String.valueOf(tempNum);
        }
    }

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
                result.setText(String.valueOf(0));
            }
        });



    }

}