package com.companyname.basisfitnesstest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class ResultsActivity extends AppCompatActivity {

    private DataProcessingModel analysisOfInput;

    private TextView sprintTimeTextView;
    private TextView sprintPointsTextView;
    private TextView sprintGradeTextView;

    private TextView pullUpTimeTextView;
    private TextView pullUpPointsTextView;
    private TextView pullUpGradeTextView;

    private TextView runTimeTextView;
    private TextView runPointsTextView;
    private TextView runGradeTextView;

    private TextView ratingTextView;
    private TextView gradeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent startResultActivity = getIntent();
        analysisOfInput = (DataProcessingModel)startResultActivity.getSerializableExtra("analysedData");

        this.sprintTimeTextView = findViewById(R.id.sprintTimeResultTextView);
        this.sprintPointsTextView = findViewById(R.id.sprintPointResultTextView);
        this.sprintGradeTextView = findViewById(R.id.sprintGradeResultTextView);

        this.pullUpTimeTextView = findViewById(R.id.klimmhangTimeResultTextView);
        this.pullUpPointsTextView = findViewById(R.id.klimmhangPointResultTextView);
        this.pullUpGradeTextView = findViewById(R.id.klimmhangGradeResultTextView);

        this.runTimeTextView = findViewById(R.id.laufTimeResultTextView);
        this.runPointsTextView = findViewById(R.id.laufPointResultTextView);
        this.runGradeTextView = findViewById(R.id.laufGradeResultTextView);

        this.ratingTextView = findViewById(R.id.ratingTextView);
        this.gradeTextView = findViewById(R.id.gradeTextView);

        setValues();
    }

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

    }

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
    }
}
