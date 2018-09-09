package com.companyname.basisfitnesstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Switch geschlechtSwitch = null;
    private TextView geschlechtTextView = null;
    private TextView alterTextView;
    private TextView sprintTextView;
    private TextView klimmhangTextView;
    private EditText laufEditText;


    public void clickFunction(View view){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeGeschlechtTextView();
        initializeSwitch();

        initializeLaufEditText();

    }

    private void initializeGeschlechtTextView() {
        geschlechtTextView = findViewById(R.id.männlichWeiblichTextView);
    }

    private void initializeSwitch() {
        geschlechtSwitch = (Switch)findViewById(R.id.geschlechtSwitch);
        geschlechtSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    geschlechtTextView.setText("Weiblich");  //To change the text near to switch
                    Log.d("You are :", "Checked");
                }
                else {
                    geschlechtTextView.setText("Männlich");  //To change the text near to switch
                    Log.d("You are :", " Not Checked");
                }
            }
        });
    }

    private void initializeLaufEditText() {
        laufEditText = findViewById(R.id.laufEditText);
        laufTimeEntered();
    }

    public void laufTimeEntered(){
        laufEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String laufTimeString = s.toString();
                if (IsAllDigits(laufTimeString))
                {
                    Log.i("TextChanged", "Ein Zeichen eingegeben: " + laufTimeString);
                }
                else{
                    //TODO: Display Alert if there is a not allowed character.
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public boolean IsAllDigits(String inputString)
    {
        for (int i=1; i<inputString.length(); i++)
        {
            /*Log.i("Schleife", "Postiton: " + i);
            Log.i("Schleife", "Länge: " + inputString.length());*/
            if (!(true == Character.isDigit(inputString.charAt(i))))
            {
                if (!(inputString.charAt(i) == ':'))
                {
                    return false;
                }
            }
        }
        return true;
    }


}
