package com.companyname.basisfitnesstest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private Intent startResultActivity = getIntent();
    private DataProcessingModel analysisOfInput;

    private TextView sprintTimeTextView = findViewById(R.id.sprintTimeTextView);
    private TextView sprintPointsTextView = findViewById(R.id.sprintPointTextView);
    private TextView sprintGradeTextView = findViewById(R.id.sprintGradeTextView);

    private TextView pullUpTimeTextView = findViewById(R.id.klimmhangTimeTextView);
    private TextView pullUpPointsTextView = findViewById(R.id.klimmhangPointTextView);
    private TextView pullUpGradeTextView = findViewById(R.id.klimmhangGradeTextView);

    private TextView laufTimeTextView = findViewById(R.id.laufTimeTextView);
    private TextView laufPointsTextView = findViewById(R.id.laufPointTextView);
    private TextView laufGradeTextView = findViewById(R.id.laufGradeTextView);

    private TextView ratingTextView = findViewById(R.id.ratingTextView);
    private TextView gradeTextView = findViewById(R.id.gradeTextView);


    public ResultsActivity(){
        analysisOfInput = (DataProcessingModel)startResultActivity.getSerializableExtra("analysedData");
        setValues();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }

    public void setValues()
    {
        //TODO: Lautzeit auch in Min:Sek ausgeben lassen. Zelle je nach Qualifikationsmerkmal einfärben
        sprintTimeTextView.setText("Zeit: " + String.valueOf(analysisOfInput.SprintTime) + " s");
        sprintPointsTextView.Text = Convert.ToString(analysisOfInput.BerechneDisziplinpunkteSprint()) + " Punkte";
        sprintGradeTextView.Text = "Note: " + String.Format("{0:F2}", analysisOfInput.NoteSprint()) + " (" + analysisOfInput.BewertungDerSprintpunkte() + ")"; ;
        setBackgroundColorToLabel(sprintGradeTextView, analysisOfInput.NoteSprint());

        pullUpTimeTextView.Text = "Zeit: " + Convert.ToString(analysisOfInput.KlimmhangTime) + " s";
        pullUpPointsTextView.Text = Convert.ToString(analysisOfInput.BerechneDisziplinpunkteKlimmhang()) + " Punkte";
        pullUpGradeTextView.Text = "Note: " + String.Format("{0:F2}", analysisOfInput.NoteKlimmhang()) + " (" + analysisOfInput.BewertungDerKlimmhangpunkte() + ")";
        setBackgroundColorToLabel(pullUpGradeTextView, analysisOfInput.NoteKlimmhang());

        laufTimeTextView.Text = "Zeit: " + Convert.ToString(analysisOfInput.LaufTime) + " s";
        laufPointsTextView.Text = Convert.ToString(analysisOfInput.BerechneDisziplinpunkteLauf()) + " Punkte";
        laufGradeTextView.Text = "Note: " + String.Format("{0:F2}", analysisOfInput.NoteLauf()) + " (" + analysisOfInput.BewertungDerLaufpunkte() + ")";
        setBackgroundColorToLabel(laufGradeTextView, analysisOfInput.NoteLauf());

        ratingTextView.Text = analysisOfInput.BewertungDerGesamtnote();
        gradeTextView.Text = "Note: " + String.Format("{0:F2}", analysisOfInput.Gesamtnote());
        setBackgroundColorToLabel(gradeTextView, analysisOfInput.Gesamtnote());

    }

    public void setBackgroundColorToLabel(Label aktuellesLabel, double note)
    {
        if (note == 5.00)
        {
            aktuellesLabel.BackgroundColor = Color.Red;
        }
        else if (note <= 4.49 && note >= 3.50)
        {
            aktuellesLabel.BackgroundColor = Color.FromRgb(255, 192, 55);
        }
        else if (note < 3.50 && note >= 2.50)
        {
            aktuellesLabel.BackgroundColor = Color.FromRgb(254, 255, 74);
        }
        else if (note < 2.50 && note >= 1.50)
        {
            aktuellesLabel.BackgroundColor = Color.FromRgb(0, 173, 236);
        }
        else if (note < 1.50 && note >= 1.00)
        {
            aktuellesLabel.BackgroundColor = Color.FromRgb(140, 209, 96);
        }
        else
        {
            aktuellesLabel.BackgroundColor = Color.Red;
        }
    }
}
