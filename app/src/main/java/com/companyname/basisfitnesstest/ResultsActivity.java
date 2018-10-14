package com.companyname.basisfitnesstest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    String sprintTimeString;
    String sprintPointsString;
    String sprintGradeString;
    String pullUpTimeString;
    String pullUpPointsString;
    String pullUpGradeString;
    String runTimeString;
    String runPointsString;
    String runGradeString;
    String ratingGrade;
    String completeGrade;

    private String resultsAsContentText;

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
        sprintTimeString = "Zeit: " + String.valueOf(analysisOfInput.SprintTime) + " s";
        sprintTimeTextView.setText(sprintTimeString);
        sprintPointsString = String.valueOf(analysisOfInput.BerechneDisziplinpunkteSprint()) + " Punkte";
        sprintPointsTextView.setText(sprintPointsString);
        sprintGradeString = "Note: " + String.format(Locale.GERMANY, "%.2f", analysisOfInput.NoteSprint()) + " (" + analysisOfInput.BewertungDerSprintpunkte() + ")";
        sprintGradeTextView.setText(sprintGradeString);
        setBackgroundColorToLabel(sprintGradeTextView, analysisOfInput.NoteSprint());

        pullUpTimeString = "Zeit: " + String.valueOf(analysisOfInput.KlimmhangTime) + " s";
        pullUpTimeTextView.setText(pullUpTimeString);
        pullUpPointsString = String.valueOf(analysisOfInput.BerechneDisziplinpunkteKlimmhang()) + " Punkte";
        pullUpPointsTextView.setText(pullUpPointsString);
        pullUpGradeString = "Note: " + String.format(Locale.GERMANY, "%.2f", analysisOfInput.NoteKlimmhang()) + " (" + analysisOfInput.BewertungDerKlimmhangpunkte() + ")";
        pullUpGradeTextView.setText(pullUpGradeString);
        setBackgroundColorToLabel(pullUpGradeTextView, analysisOfInput.NoteKlimmhang());

        runTimeString = "Zeit: " + String.valueOf(analysisOfInput.LaufTime) + " s";
        runTimeTextView.setText(runTimeString);
        runPointsString = String.valueOf(analysisOfInput.BerechneDisziplinpunkteLauf()) + " Punkte";
        runPointsTextView.setText(runPointsString);
        runGradeString = "Note: " + String.format(Locale.GERMANY, "%.2f", analysisOfInput.NoteLauf()) + " (" + analysisOfInput.BewertungDerLaufpunkte() + ")";
        runGradeTextView.setText(runGradeString);
        setBackgroundColorToLabel(runGradeTextView, analysisOfInput.NoteLauf());

        ratingGrade = String.valueOf(analysisOfInput.BewertungDerGesamtnote());
        ratingTextView.setText(ratingGrade);
        completeGrade = "Note: " + String.format(Locale.GERMANY, "%.2f", analysisOfInput.Gesamtnote());
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

    public void clickErgebnisTeilen(View view) {
        getResultsAsTextContent();
        sendMail();

    }

    private void getResultsAsTextContent() {
        resultsAsContentText = "Basis Fitness Test\n\n";
        resultsAsContentText += "Ergebnis vom DATUM \n\n";

        resultsAsContentText += "11 x 10 m Sprinttest\n";
        resultsAsContentText += sprintTimeString + "\n";
        resultsAsContentText += sprintPointsString + "\n";
        resultsAsContentText += sprintGradeString + "\n\n";

        resultsAsContentText += "Klimmhang\n";
        resultsAsContentText += pullUpTimeString + "\n";
        resultsAsContentText += pullUpPointsString + "\n";
        resultsAsContentText += pullUpGradeString + "\n\n";

        resultsAsContentText += "1000-m-Lauf\n";
        resultsAsContentText += runTimeString + "\n";
        resultsAsContentText += runPointsString + "\n";
        resultsAsContentText += runGradeString + "\n\n";

        resultsAsContentText += "Gesamtbewertung\n";
        resultsAsContentText += completeGrade + "(" + ratingGrade + ")";
    }

    private void sendMail() {

        Intent mailSendIntent = new Intent(Intent.ACTION_SEND);
        mailSendIntent.setType("text/plain");
        mailSendIntent.putExtra(Intent.EXTRA_SUBJECT, "Ergebnisse BFT, DATUM");
        mailSendIntent.putExtra(Intent.EXTRA_TEXT, resultsAsContentText);

        //mailSendIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(mailSendIntent, "App zum Teilen wählen"));
    }
}
