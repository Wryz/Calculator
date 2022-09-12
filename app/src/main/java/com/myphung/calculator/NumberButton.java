package com.myphung.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NumberButton {

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

    private final Button button;
    private final TextView result;

    NumberButton(Button button, TextView result) {
        this.button = button;
        this.result = result;
    }

    public Button getButton() {
        return button;
    }

    public TextView getResult() {
        return result;
    }

    /**
     * States the function of the button's on-click action
     * @return View.onClickListener instance
     */
    public View.OnClickListener getClickAction() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number;

                try {
                    number = Integer.parseInt(button.getText().toString());
                } catch (Exception x) {
                    throw new Error(x.fillInStackTrace());
                }

                //add number to end of current number
                MainActivity.addCurrentNumber(number);

                //display number in result
                MainActivity.displayResult(result);
            }
        };
    }
}
