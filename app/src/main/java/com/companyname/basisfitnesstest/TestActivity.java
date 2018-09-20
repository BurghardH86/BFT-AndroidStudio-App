package com.companyname.basisfitnesstest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class TestActivity extends AppCompatActivity {

    private DataProcessingModel analysisOfInput;


    //private TextView sprintTimeTextView = findViewById(R.id.sprintTimeResultTextView);
    /*private TextView sprintPointsTextView = findViewById(R.id.sprintPointTextView);
    private TextView sprintGradeTextView = findViewById(R.id.sprintGradeTextView);

    private TextView pullUpTimeTextView = findViewById(R.id.klimmhangTimeTextView);
    private TextView pullUpPointsTextView = findViewById(R.id.klimmhangPointTextView);
    private TextView pullUpGradeTextView = findViewById(R.id.klimmhangGradeTextView);

    private TextView runTimeTextView = findViewById(R.id.laufTimeTextView);
    private TextView runPointsTextView = findViewById(R.id.laufPointTextView);
    private TextView runGradeTextView = findViewById(R.id.laufGradeTextView);

    private TextView ratingTextView = findViewById(R.id.ratingTextView);
    private TextView gradeTextView = findViewById(R.id.gradeTextView);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView testTextView = findViewById(R.id.textView);
    }

/*
    public void setValues()
    {
        String sprintTimeString = "Zeit: " + String.valueOf(analysisOfInput.SprintTime) + " s";
        sprintTimeTextView.setText(sprintTimeString);
        String sprintPointsString = String.valueOf(analysisOfInput.BerechneDisziplinpunkteSprint()) + " Punkte";
        sprintPointsTextView.setText(sprintPointsString);
        String sprintGradeString = "Note: " + String.format(Locale.GERMANY, "%.2f", analysisOfInput.NoteSprint()) + " (" + analysisOfInput.BewertungDerSprintpunkte() + ")";
        sprintGradeTextView.setText(sprintGradeString);
        setBackgroundColorToLabel(sprintGradeTextView, analysisOfInput.NoteSprint());

        String pullUpTimeString = "Zeit: " + String.valueOf(analysisOfInput.KlimmhangTime) + " s";
        pullUpTimeTextView.setText(pullUpTimeString);
        String pullUpPointsString = String.valueOf(analysisOfInput.BerechneDisziplinpunkteKlimmhang()) + " Punkte";
        pullUpPointsTextView.setText(pullUpPointsString);
        String pullUpGradeString = "Note: " + String.format(Locale.GERMANY, "%.2f", analysisOfInput.NoteKlimmhang()) + " (" + analysisOfInput.BewertungDerKlimmhangpunkte() + ")";
        pullUpGradeTextView.setText(pullUpGradeString);
        setBackgroundColorToLabel(pullUpGradeTextView, analysisOfInput.NoteKlimmhang());

        String runTimeString = "Zeit: " + String.valueOf(analysisOfInput.LaufTime) + " s";
        runTimeTextView.setText(runTimeString);
        String runPointsString = String.valueOf(analysisOfInput.BerechneDisziplinpunkteLauf()) + " Punkte";
        runPointsTextView.setText(runPointsString);
        String runGradeString = "Note: " + String.format(Locale.GERMANY, "%.2f", analysisOfInput.NoteLauf()) + " (" + analysisOfInput.BewertungDerLaufpunkte() + ")";
        runGradeTextView.setText(runGradeString);
        setBackgroundColorToLabel(runGradeTextView, analysisOfInput.NoteLauf());


        ratingTextView.setText(analysisOfInput.BewertungDerGesamtnote());
        String completeGrade = "Note: " + String.format(Locale.GERMANY, "%.2f", analysisOfInput.Gesamtnote());
        gradeTextView.setText(completeGrade);
        setBackgroundColorToLabel(gradeTextView, analysisOfInput.Gesamtnote());

    }*/
/*
    public void setBackgroundColorToLabel(TextView currentTextView, double grade)
    {
        if (grade == 5.00)
        {
            currentTextView.setBackgroundColor(Color.RED);
        }
        else if (grade <= 4.49 && grade >= 3.50)
        {
            currentTextView.setBackgroundColor(Color.rgb(255, 192, 55));
        }
        else if (grade < 3.50 && grade >= 2.50)
        {
            currentTextView.setBackgroundColor(Color.rgb(254, 255, 74));
        }
        else if (grade < 2.50 && grade >= 1.50)
        {
            currentTextView.setBackgroundColor(Color.rgb(0, 173, 236));
        }
        else if (grade < 1.50 && grade >= 1.00)
        {
            currentTextView.setBackgroundColor(Color.rgb(140, 209, 96));
        }
        else
        {
            currentTextView.setBackgroundColor(Color.RED);
        }
    }*/
}
