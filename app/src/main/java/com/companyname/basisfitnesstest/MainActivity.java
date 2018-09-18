package com.companyname.basisfitnesstest;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch genderSwitch = null;
    private TextView genderTextView = null;
    private EditText sprintEditText;
    private EditText pullUpEditText;
    private EditText runEditText;
    private SeekBar ageSeekBar;
    private TextView ageChangeTextView;

    private boolean genderSwitchIsChecked = false;
    private int age = 0;
    private double sprintTime = 0.0;
    private double pullUpTime = 0.0;
    private double runTime = 0.0;
    private int maxValue = 65;
    private int seekBarValue = 0;

    private boolean inputAlertTriggered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genderTextView = findViewById(R.id.männlichWeiblichTextView);
        genderSwitch = findViewById(R.id.geschlechtSwitch);
        sprintEditText = findViewById(R.id.sprintEditText);
        pullUpEditText = findViewById(R.id.klimmhangEditText);
        runEditText = findViewById(R.id.laufEditText);
        ageSeekBar = findViewById(R.id.alterSeekBar);
        ageChangeTextView = findViewById(R.id.alterChangeTextView);

        switchCheckedChangedListener();
        runTimeTextChangedListener(runEditText);
        initializeSeekBar();
        seekBarChangedListener();
    }

    private void switchCheckedChangedListener() {
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

    private void initializeSeekBar(){
        maxValue = ageSeekBar.getMax(); // get maximum value of the Seek bar
        seekBarValue = ageSeekBar.getProgress() + 16; // get progress value from the Seek bar
    }

    private void seekBarChangedListener(){
        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue = progress + 16;
                ageChangeTextView.setText(String.valueOf(seekBarValue));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void runTimeTextChangedListener(EditText inputEditText){
        //Because of the restrictions regarding the possible input, it is important to use a listener for changing text here.
        inputEditText.addTextChangedListener(new TextWatcher() {
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
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initializeSprintEditText(){
        checkForValidSprintTime();
    }

    private void checkForValidSprintTime(){
        double validTime = checkForValidTime(sprintEditText);
        if (validTime <= 0)
        {
            sprintEditText.setBackgroundColor(Color.RED);
            displayMinTimeInputAlert();
        }
        sprintTime = validTime;
    }

    private void initializePullUpEditText(){
        checkForValidPullUpTime();
    }

    private void checkForValidPullUpTime(){
        double validTime = checkForValidTime(pullUpEditText);
        if (validTime < 0.0)
        {
            sprintEditText.setBackgroundColor(Color.RED);
            displayMinTimeInputAlert();
        }
        pullUpTime = validTime;
    }

    private void initializeRunEditText() {
        String runTimeString = runEditText.getText().toString();
        checkForValidRunTime(runTimeString);
    }

    private void checkForValidRunTime(String runTimeString) {
        String[] arrayOfRunTimeString = runTimeString.split(":", 2);
        double secondsPerMinute = 60.0;
        double runTimeMinutes = 0.0;
        double runTimeSeconds = 0.0;
        if (tryParseDouble(arrayOfRunTimeString[0]) && tryParseDouble(arrayOfRunTimeString[1])) {
            runTimeMinutes = Double.parseDouble(arrayOfRunTimeString[0]);
            runTimeSeconds = Double.parseDouble(arrayOfRunTimeString[1]);
            runEditText.setBackgroundColor(Color.TRANSPARENT);
        }
        else{
            runEditText.setBackgroundColor(Color.RED);
            displayWrongRunTimeAlertExplanation();
        }
        runTime = runTimeMinutes*secondsPerMinute + runTimeSeconds;
    }

    public void clickFunction(View view){
        inputAlertTriggered = false;
        initializeRunEditText();
        initializeSprintEditText();
        initializePullUpEditText();
        Intent startResultActivity = new Intent(this, ResultsActivity.class);
        startActivity(startResultActivity);
        DataProcessingModel Auswertung = new DataProcessingModel(genderSwitchIsChecked, age, sprintTime, pullUpTime, runTime);
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

    private void displayWrongRunTimeAlertExplanation()
    {
        if (!inputAlertTriggered)
        {
            displayAlert("Achtung", "Es dürfen nur Zahlen in dem Format Minuten:Sekunden (mm:ss) eingetragen werden!", "OK");
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
            if (tryParseDouble(inputEditText.getText().toString())) {
                validTime = Double.parseDouble(inputEditText.getText().toString());
            }
        }
        return validTime;
    }

    public boolean tryParseDouble(String inputString){
        try {
            Double.parseDouble(inputString);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

}
