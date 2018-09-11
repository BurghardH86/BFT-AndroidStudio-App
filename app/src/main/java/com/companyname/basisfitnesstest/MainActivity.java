package com.companyname.basisfitnesstest;

import android.content.DialogInterface;
import android.graphics.Color;
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

    private Switch genderSwitch = null;
    private TextView genderTextView = null;
    private EditText ageEditText;
    private EditText sprintEditText;
    private EditText pullUpEditText;
    private EditText runEditText;

    private boolean genderSwitchIsChecked = false;
    private int age = 0;
    private double sprintTime = 0.0;
    private double pullUpTime = 0.0;
    private double runTime = 0.0;

    private boolean inputAlertTriggered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initializeSwitch() {
        genderTextView = findViewById(R.id.männlichWeiblichTextView);
        genderSwitch = findViewById(R.id.geschlechtSwitch);
        genderSwitchIsChecked = genderSwitch.isChecked();
        genderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    genderSwitchIsChecked = genderSwitch.isChecked();
                    genderTextView.setText("Weiblich");  //To change the text near to switch
                    Log.d("You are :", "Checked");
                }
                else {
                    genderSwitchIsChecked = !genderSwitch.isChecked();
                    genderTextView.setText("Männlich");  //To change the text near to switch
                    Log.d("You are :", " Not Checked");
                }
            }
        });
    }

    private void initializeAgeEditText(){
        ageEditText = findViewById(R.id.alterEditText);
        checkForValidAge();
    }

    private void checkForValidAge(){
        age = Integer.parseInt(ageEditText.getText().toString());
        if (age < 16 || age > 65)
        {
            ageEditText.setBackgroundColor(Color.RED);
            displayWrongAgeAlert();
        }
        else
        {
            ageEditText.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void initializeSprintEditText(){
        sprintEditText = findViewById(R.id.sprintEditText);
        sprintTime = checkForValidSprintTime();
    }

    private double checkForValidSprintTime(){
        double validTime = checkForValidTime(sprintEditText);
        if (validTime <= 0)
        {
            sprintEditText.setBackgroundColor(Color.RED);
            displayMinTimeInputAlert();
        }
        return validTime;
    }

    private void initializePullUpEditText(){
        pullUpEditText = findViewById(R.id.klimmhangEditText);
        pullUpTime = checkForValidPullUpTime();
    }

    private double checkForValidPullUpTime(){
        double validTime = checkForValidTime(pullUpEditText);
        if (validTime < 0.0)
        {
            sprintEditText.setBackgroundColor(Color.RED);
            displayMinTimeInputAlert();
        }
        return validTime;
    }

    private void initializeRunEditText() {
        runEditText = findViewById(R.id.laufEditText);
        checkForValidRunTime();
    }

    private void checkForValidRunTime(){
        runEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String runTimeString = s.toString();
                if (!isAllDigits(runTimeString))
                {
                    displayWrongRunTimeAlert();
                }
                else{
                    runTime = Double.parseDouble(runTimeString);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void clickFunction(View view){
        inputAlertTriggered = false;
        //TODO: Unfortunately is this implementation wrong: Is has to be event-driven. Better next time.
        initializeSwitch();
        initializeAgeEditText();
        initializeRunEditText();
        initializeSprintEditText();
        initializePullUpEditText();
        //BFTErgebnisBerechnung Auswertung = new BFTErgebnisBerechnung(genderSwitchIsChecked, alter, sprintTime, klimmhangTime, laufTime);
        //if (!inputAlertTriggered)
        //{
            //await Navigation.PushAsync(new ViewResult(Auswertung));
        //}
    }


    //Alerts
    private void displayWrongRunTimeAlert()
    {
        if (!inputAlertTriggered)
        {
            displayAlert("Achtung", "Es dürfen nur Zahlen und ':' eingegeben werden!", "OK");
        }
    }

    private void displayWrongInputAlert()
    {
        if (!inputAlertTriggered)
        {
            displayAlert("Achtung", "Der Wert wurde nicht als Zahl eingegeben!", "OK");
            inputAlertTriggered = true;
        }
    }

    private void displayWrongAgeAlert()
    {
        if (!inputAlertTriggered)
        {
            displayAlert("Achtung", "Das Alter muss über 16 und unter 65 Jahren liegen!", "OK");
            inputAlertTriggered = true;
        }
    }

    private void displayMinTimeInputAlert()
    {
        if (!inputAlertTriggered)
        {
            displayAlert("Achtung", "Zeiten müssen größer als Null sein!", "OK");
            inputAlertTriggered = true;
        }
    }

    //Supporting methods
    private void displayAlert(String titleString, String messageString, String buttonString) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(titleString);
        alertDialog.setMessage(messageString);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, buttonString,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public boolean isAllDigits(String inputString)
    {
        for (int i=1; i<inputString.length(); i++)
        {
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

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private double checkForValidTime(EditText inputEditText)
    {
        double validTime = 0.0;
        if (!isInteger(inputEditText.getText().toString()))
        {
            inputEditText.setBackgroundColor(Color.RED);
            displayWrongInputAlert();
        }
        else
        {
            inputEditText.setBackgroundColor(Color.TRANSPARENT);
            validTime = Double.parseDouble(inputEditText.getText().toString());
        }
        return validTime;
    }

}
