package com.companyname.basisfitnesstest;

public class DataProcessingModel {

    public boolean Weiblich;
    public int Alter;
    public double SprintTime;
    public double KlimmhangTime;
    public double LaufTime;
    private boolean NichtBestanden = false;


    public DataProcessingModel()
    {
        Weiblich = false;

    }

    public DataProcessingModel(boolean weiblich, int alter, double sprintTime, double klimmhangTime, double tausendMeterLaufTime)
    {
        this.Weiblich = weiblich;
        this.Alter = alter;
        this.SprintTime = sprintTime;
        this.KlimmhangTime = klimmhangTime;
        this.LaufTime = tausendMeterLaufTime;
    }

    private double Geschlechtszuschlag(int Basispunkte, double prozentualerGeschlechtszuschlagDerDisziplin)
    {
        if (Weiblich)
        {
            return Basispunkte * prozentualerGeschlechtszuschlagDerDisziplin;
        }
        else
        {
            return 0;
        }
    }

    private double Alterszuschlag(int Basispunkte)
    {
        int zuschlagAbDiesemAlter = 35;
        double prozentualerAlterszuschlag = 0.005;
        double Alterszuschlag = 0;
        if (Alter > zuschlagAbDiesemAlter)
        {
            Alterszuschlag = Basispunkte * (Alter - zuschlagAbDiesemAlter) * prozentualerAlterszuschlag;
        }
        else
        {
            Alterszuschlag = 0;
        }

        return Alterszuschlag;
    }

    private int BerechneBasispunkteSprint(double sprintZeit)
    {
        double mindestleistungSprintZeit = 60.0;
        if (sprintZeit > mindestleistungSprintZeit)
        {
            return 0;
        }
        return (int)Math.round(1100 - 16.667 * sprintZeit);
    }

    public int BerechneDisziplinpunkteSprint()
    {
        int Basispunkte = BerechneBasispunkteSprint(SprintTime);
        double prozentualerGeschlechtszuschlagSprint = 0.15;
        double Disziplinpunkte = Basispunkte + Alterszuschlag(Basispunkte) + Geschlechtszuschlag(Basispunkte, prozentualerGeschlechtszuschlagSprint);

        return (int)Math.round(Disziplinpunkte);
    }

    private int BerechneBasispunkteKlimmhang(double klimmhangZeit)
    {
        double mindestleistungKlimmhang = 5.0;
        if (klimmhangZeit < mindestleistungKlimmhang)
        {
            return 0;
        }
        return (int)Math.round(75 + 5 * klimmhangZeit);
    }

    public int BerechneDisziplinpunkteKlimmhang()
    {
        int Basispunkte = BerechneBasispunkteKlimmhang(KlimmhangTime);
        double prozentualerGeschlechtszuschlagKlimmhang = 0.4;
        double Disziplinpunkte = Basispunkte + Alterszuschlag(Basispunkte) + Geschlechtszuschlag(Basispunkte, prozentualerGeschlechtszuschlagKlimmhang);

        return (int)Math.round(Disziplinpunkte);
    }

    private int BerechneBasispunkteLauf(double laufZeit)
    {
        double mindestleistungLauf = 390.0;
        if (laufZeit > mindestleistungLauf)
        {
            return 0;
        }
        return (int)Math.round(100 + ((390 - laufZeit) * 1.81818181));
    }

    public int BerechneDisziplinpunkteLauf()
    {
        int Basispunkte = BerechneBasispunkteLauf(LaufTime);
        double prozentualerGeschlechtszuschlagLauf = 0.15;
        double Disziplinpunkte = Basispunkte + Alterszuschlag(Basispunkte) + Geschlechtszuschlag(Basispunkte, prozentualerGeschlechtszuschlagLauf);

        return (int)Math.round(Disziplinpunkte);
    }

    public double BerechnungDerNote(int punkte)
    {
        double maxPunkte = 449.00;
        double minPunkte = 100.00;
        double doublepunkte = (double)punkte;
        double ergebnisNote;

        if (doublepunkte >= maxPunkte)
        {
            ergebnisNote = 1.00;
        }
        else if (doublepunkte < minPunkte)
        {
            ergebnisNote = 5.00;
            NichtBestanden = true;
        }
        else
        {
            double prozentualerAnteil = doublepunkte / maxPunkte;
            double prozentualerUnterschiedZwischenNoten = 1 - (448.0 / 449.0);
            ergebnisNote = 1 + (0.01 * (1 - prozentualerAnteil) / prozentualerUnterschiedZwischenNoten);
        }
        return Math.round(ergebnisNote);
    }

    public double NoteSprint()
    {
        double note = BerechnungDerNote(BerechneDisziplinpunkteSprint());

        return note;
    }

    public double NoteKlimmhang()
    {
        double note = BerechnungDerNote(BerechneDisziplinpunkteKlimmhang());

        return note;
    }

    public double NoteLauf()
    {
        double note = BerechnungDerNote(BerechneDisziplinpunkteLauf());

        return note;
    }

    public double Gesamtnote()
    {
        double noteSprint = NoteSprint();
        double noteKlimmhang = NoteKlimmhang(); ;
        double noteLauf = NoteLauf();
        double[] enthaeltEinzelnoten = { noteSprint, noteKlimmhang, noteLauf };

        double total = 0;
        for (double element: enthaeltEinzelnoten) {
            total += element;
        }

        double noteGesamt = total / enthaeltEinzelnoten.length;
        if (NichtBestanden)
        {
            return Math.round(5.00);
        }
        return Math.round(noteGesamt);
    }

    public String BewertungDerPunkte(int jeweiligeDisziplinpunkte)
    {
        String bewertungsErgebnis = "Nicht bestanden";
        if (jeweiligeDisziplinpunkte >= 100 && jeweiligeDisziplinpunkte < 200)
        {
            bewertungsErgebnis = "Ausreichend";
        }
        else if (jeweiligeDisziplinpunkte >= 200 && jeweiligeDisziplinpunkte < 300)
        {
            bewertungsErgebnis = "Zufriedenstellend";
        }
        else if (jeweiligeDisziplinpunkte >= 300 && jeweiligeDisziplinpunkte < 400)
        {
            bewertungsErgebnis = "Gut";
        }
        else if (jeweiligeDisziplinpunkte >= 400)
        {
            bewertungsErgebnis = "Sehr gut";
        }
        else
        {
            bewertungsErgebnis = "Nicht bestanden";
            NichtBestanden = true;
        }
        return bewertungsErgebnis;
    }

    public String BewertungDerSprintpunkte()
    {
        String bewertung = BewertungDerPunkte(BerechneDisziplinpunkteSprint());

        return bewertung;
    }

    public String BewertungDerKlimmhangpunkte()
    {
        String bewertung = BewertungDerPunkte(BerechneDisziplinpunkteKlimmhang());

        return bewertung;
    }

    public String BewertungDerLaufpunkte()
    {
        String bewertung = BewertungDerPunkte(BerechneDisziplinpunkteLauf());

        return bewertung;
    }

    //public string BewertungDerGesamtnote()
    //{
    //    int gesamtpunkte = BerechnungDerPunkteAusGesamtnote(Gesamtnote());
    //    string bewertung = BewertungDerPunkte(gesamtpunkte);

    //    return bewertung;
    //}

    ////TODO: Berechnung der Bewertung aus der Gesamtnote. War mir jetzt zu kompliziert.
    //public int BerechnungDerPunkteAusGesamtnote(double note)
    //{
    //    double maxPunkte = 449.0;
    //    double prozentualerUnterschiedZwischenNoten = 1 - (448.0 / 449.0);
    //    double prozentualerAnteil = (((((note - 1) * 100) - 1) * prozentualerUnterschiedZwischenNoten) * (-1));
    //    double doublepunkte = maxPunkte * prozentualerAnteil;

    //    return (int)Math.Round(doublepunkte, 0);
    //}

    public String BewertungDerGesamtnote()
    {
        double gesamtnote = Gesamtnote();
        String bewertungsErgebnis = "Fehler!";
        if (NichtBestanden)
        {
            bewertungsErgebnis = "Nicht bestanden";
        }
        else if (gesamtnote <= 4.49 && gesamtnote >= 3.50)
        {
            bewertungsErgebnis = "Ausreichend";
        }
        else if (gesamtnote < 3.50 && gesamtnote >= 2.50)
        {
            bewertungsErgebnis = "Zufriedenstellend";
        }
        else if (gesamtnote < 2.50 && gesamtnote >= 1.50)
        {
            bewertungsErgebnis = "Gut";
        }
        else if (gesamtnote < 1.50 && gesamtnote >= 1.00)
        {
            bewertungsErgebnis = "Sehr gut";
        }
        else
        {
            bewertungsErgebnis = "Fehler!";
        }
        return bewertungsErgebnis;
    }


}
