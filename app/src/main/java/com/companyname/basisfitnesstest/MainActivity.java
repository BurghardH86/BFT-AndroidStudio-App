package com.companyname.basisfitnesstest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

public class MainActivity extends AppCompatActivity {

    private Switch geschlechtSwitch = null;
    private TextView geschlechtTextView = null;
    private EditText alterEditText;
    private EditText sprintEditText;
    private EditText klimmhangEditText;
    private EditText laufEditText;

    private boolean inputAlertTriggered;
    private boolean genderSwitchIsChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeGeschlechtTextView();
        initializeSwitch();

        initializeLaufEditText();

        alterEditText = findViewById(R.id.alterEditText);
        sprintEditText = findViewById(R.id.sprintEditText);
        klimmhangEditText = findViewById(R.id.klimmhangEditText);

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
                    genderSwitchIsChecked = geschlechtSwitch.isChecked();
                    geschlechtTextView.setText("Weiblich");  //To change the text near to switch
                    Log.d("You are :", "Checked");
                }
                else {
                    genderSwitchIsChecked = geschlechtSwitch.isChecked();
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
                if (!IsAllDigits(laufTimeString))
                {
                    laufTimeEnteredAlert();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void laufTimeEnteredAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Achtung");
        alertDialog.setMessage("Es dürfen nur Zahlen und ':' eingegeben werden!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
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

    public void clickFunction(View view){
        inputAlertTriggered = false;
        int alter = Integer.parseInt(alterEditText.getText().toString());
        double sprintTime = Double.parseDouble(sprintEditText.getText().toString());
        double klimmhangTime = Double.parseDouble(klimmhangEditText.getText().toString());
        double laufTime = Double.parseDouble(laufEditText.getText().toString());
        //BFTErgebnisBerechnung Auswertung = new BFTErgebnisBerechnung(genderSwitchIsChecked, alter, sprintTime, klimmhangTime, laufTime);
        if (!inputAlertTriggered)
        {
            //await Navigation.PushAsync(new ViewResult(Auswertung));
        }

    }


}
