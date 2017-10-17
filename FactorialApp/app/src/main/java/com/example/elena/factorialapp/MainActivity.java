package com.example.elena.factorialapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mNumberEditText;
    private TextView mResultTextView;
    private Button mResetButton, mCalculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTextView.setText("");
                mNumberEditText.clearFocus();
                mNumberEditText.setText("");
                hideKeyboard();
            }
        });
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    displayResult();
                }catch (Exception e){
                    Log.v("excep",e.toString());
                    mResultTextView.setText(getString(R.string.error));
                    mResultTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                }

                mNumberEditText.clearFocus();
                hideKeyboard();
            }
        });

    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mNumberEditText.getWindowToken(),0);
    }

    private void findViews(){
        mNumberEditText = (EditText) findViewById(R.id.edit_text_number);
        mResultTextView = (TextView) findViewById(R.id.text_view_result);
        mResetButton = (Button) findViewById(R.id.button_reset);
        mCalculateButton = (Button) findViewById(R.id.button_calculate);
    }

    private double calculateFactorial(double n){
        if(n==0 || n==1)
            return 1;
        return n * calculateFactorial(n-1);
    }

    private void displayResult(){

        double inputNumber = Double.valueOf(mNumberEditText.getText().toString());
        double factorial = calculateFactorial(inputNumber);
        String result = getString(R.string.result, inputNumber+"", factorial+"");

        mResultTextView.setText(result);
        mResultTextView.setTextColor(getResources().getColor(R.color.black));
    }
}
