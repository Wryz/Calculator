package com.myphung.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NumberButtons extends MainActivity {

    //list of all number buttons
    static ArrayList<Integer> numberIDs = new ArrayList<Integer>() {
        {
            add(R.id.button0);
            add(R.id.button1);
            add(R.id.button2);
            add(R.id.button3);
            add(R.id.button4);
            add(R.id.button5);
            add(R.id.button6);
            add(R.id.button7);
            add(R.id.button8);
            add(R.id.button9);
        }
    };

    public Button getButton(Integer index) {
        return findViewById(numberIDs.get(index));
    }

    public View.OnClickListener getClickAction(Integer id) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = findViewById(id);
                int number;

                try {
                    number = Integer.parseInt(button.getText().toString());
                } catch (Exception x) {
                    throw new Error(x.fillInStackTrace());
                }

                TextView result = findViewById(R.id.result);

                //add number to end of current number
                addCurrentNumber(number);

                //display number in result
                displayResult(result);
            }
        };
    }
}
