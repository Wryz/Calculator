package com.myphung.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class OperatorButton extends ResultsDisplay {

    /**
     * IDs of all operator buttons
     */
    static ArrayList<Integer> operatorIDs = new ArrayList<Integer>() {
        {
            add(R.id.buttonAdd);
            add(R.id.buttonSub);
            add(R.id.buttonMulti);
            add(R.id.buttonDiv);
            add(R.id.buttonMod);
        }
    };

    private final Button button;
    private final TextView result;

    OperatorButton(Button button, TextView result) {
        this.button = button;
        this.result = result;
    }

    /**
     * @return Saved Button instance
     */
    public Button getButton() {
        return button;
    }

    /**
     * @return Saved TextView instance
     */
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
                MainActivity.addSequence(MainActivity.getCurrentNumber());
                MainActivity.addSequence(button.getText().toString());
                MainActivity.clearCurrentNumber();

                //displays operator
                displayResult(getResult());
            }
        };
    }

    /**
     * Changes the color of the operator button
     */
    @Override
    public void displayResult(TextView view) {
        super.displayResult(view);
        //TODO add a clicked-button style that changes the color of the background
    }
}
