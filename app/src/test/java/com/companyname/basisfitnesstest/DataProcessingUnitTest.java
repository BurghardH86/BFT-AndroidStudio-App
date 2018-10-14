package com.companyname.basisfitnesstest;

import org.junit.Assert;
import org.junit.Test;

public class DataProcessingUnitTest {

    @Test
    public void BewertungDerPunkte_Unter100_Nichtbestanden2() {
        //Arange
        DataProcessingModel Auswertung = new DataProcessingModel();

        //Act
        String result = Auswertung.BewertungDerPunkte(0);

        //Assert
        Assert.assertEquals("Nicht bestanden!", result);
    }
    
    @Test
    public void BewertungDerPunkte_Unter100_Nichtbestanden()
    {
        //Arange
        DataProcessingModel Auswertung = new DataProcessingModel();

        //Act
        String result = Auswertung.BewertungDerPunkte(99);

        //Assert
        Assert.assertEquals("Nicht bestanden!", result);
    }

    @Test
    public void BewertungDerPunkte_150Punkte_StringAusreichend()
    {
        //Arange
        DataProcessingModel Auswertung = new DataProcessingModel();

        //Act
        String result = Auswertung.BewertungDerPunkte(150);

        //Assert
        Assert.assertEquals("Ausreichend", result);
    }

    @Test
    public void BewertungDerPunkte_250Punkte_StringZufriendstellend()
    {
        //Arange
        DataProcessingModel Auswertung = new DataProcessingModel();

        //Act
        String result = Auswertung.BewertungDerPunkte(250);

        //Assert
        Assert.assertEquals("Zufriedenstellend", result);
    }

    @Test
    public void BewertungDerPunkte_350Punkte_StringGut()
    {
        //Arange
        DataProcessingModel Auswertung = new DataProcessingModel();

        //Act
        String result = Auswertung.BewertungDerPunkte(350);

        //Assert
        Assert.assertEquals("Gut", result);
    }

    @Test
    public void BewertungDerPunkte_450Punkte_StringSehrGut()
    {
        //Arange
        DataProcessingModel Auswertung = new DataProcessingModel();

        //Act
        String result = Auswertung.BewertungDerPunkte(450);

        //Assert
        Assert.assertEquals("Sehr gut", result);
    }

    @Test
    public void BerechneDisziplinpunkteSprint_Mindestleistung60s_100Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 60, 5, 390);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteSprint();
        //Assert
        Assert.assertEquals(100, result);
    }

    @Test
    public void BerechneDisziplinpunkteSprint_NichtBestanden61s_0Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 61, 5, 390);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteSprint();
        //Assert
        Assert.assertEquals(0, result);
    }

    @Test
    public void NoteDisziplinpunkteSprint_Nichtbestanden61s_500()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 61, 30, 170);
        //Act
        double result = Auswertung.NoteSprint();
        //Assert
        Assert.assertEquals(5.00, result, 0.001);
    }

    @Test
    public void BerechneDisziplinpunkteKlimmhang_Mindestleistung5s_100Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 60, 5, 390);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteKlimmhang();
        //Assert
        Assert.assertEquals(100, result);
    }

    @Test
    public void BerechneDisziplinpunkteKlimmhang_NichtBestanden4s_0Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 60, 4, 390);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteKlimmhang();
        //Assert
        Assert.assertEquals(0, result);
    }

    @Test
    public void NoteDisziplinpunkteKlimmhang_Nichtbestanden4s_500()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 60, 4, 170);
        //Act
        double result = Auswertung.NoteKlimmhang();
        //Assert
        Assert.assertEquals(5.00, result, 0.001);
    }

    @Test
    public void BerechneDisziplinpunkteLauf_Mindestleistung390s_100Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 60, 5, 390);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteLauf();
        //Assert
        Assert.assertEquals(100, result);
    }

    @Test
    public void BerechneDisziplinpunkteLauf_NichtBestanden391s_0Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 60, 4, 391);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteLauf();
        //Assert
        Assert.assertEquals(0, result);
    }

    @Test
    public void NoteDisziplinpunkteLauf_Nichtbestanden391s_500()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 60, 4, 391);
        //Act
        double result = Auswertung.NoteLauf();
        //Assert
        Assert.assertEquals(5.00, result, 0.001);
    }

    @Test
    public void BerechneDisziplinpunkteSprint_Bestleistung35s_517Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 86, 170);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteSprint();
        //Assert
        Assert.assertEquals(517, result);
    }

    @Test
    public void BerechneDisziplinpunkteKlimmhang_Bestleistung86s_505Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 86, 170);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteKlimmhang();
        //Assert
        Assert.assertEquals(505, result);
    }

    @Test
    public void BerechneDisziplinpunkteLauf_Bestleistung170s_500Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 86, 170);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteLauf();
        //Assert
        Assert.assertEquals(500, result);
    }


    @Test
    public void BerechneDisziplinpunkteSprint_FrauBestleistung35s_595Punke()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(true, 18, 35, 86, 170);
        //Act
        int sprint = Auswertung.BerechneDisziplinpunkteSprint();
        //Assert
        Assert.assertEquals(595, sprint);
    }


    @Test
    public void BerechneDisziplinpunkteKlimmhang_FrauBestleistung86s_505Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(true, 18, 35, 86, 170);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteKlimmhang();
        //Assert
        Assert.assertEquals(707, result);
    }

    @Test
    public void BerechneDisziplinpunkteLauf_FrauBestleistung170s_575Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(true, 18, 35, 86, 170);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteLauf();
        //Assert
        Assert.assertEquals(575, result);
    }


    @Test
    public void BerechneDisziplinpunkteSprint_MannAlter50J40s_430Punke()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 50, 40, 60, 230);
        //Act
        int sprint = Auswertung.BerechneDisziplinpunkteSprint();
        //Assert
        Assert.assertEquals(465, sprint);
    }

    @Test
    public void BerechneDisziplinpunkteKlimmhang_MannAlter50J60s_403Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 50, 40, 60, 230);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteKlimmhang();
        //Assert
        Assert.assertEquals(403, result);
    }

    @Test
    public void BerechneDisziplinpunkteLauf_MannAlter50J230s_420Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 50, 40, 60, 230);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteLauf();
        //Assert
        Assert.assertEquals(420, result);
    }


    @Test
    public void NoteDisziplinpunkteKlimmhang_Bestleistung86s_1Punkt00()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 86, 170);
        //Act
        double result = Auswertung.NoteKlimmhang();
        //Assert
        Assert.assertEquals(1.00, result, 0.001);
    }

    @Test
    public void NoteDisziplinpunkteKlimmhang_30s_1Punkt00()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 30, 170);
        //Act
        double result = Auswertung.NoteKlimmhang();
        //Assert
        Assert.assertEquals(3.24, result, 0.001);
    }

    @Test
    public void DisziplinpunkteKlimmhang_30s_225Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 30, 170);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteKlimmhang();
        //Assert
        Assert.assertEquals(225, result);
    }

    @Test
    public void DisziplinpunkteKlimmhang_45s_300Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 45, 170);
        //Act
        int result = Auswertung.BerechneDisziplinpunkteKlimmhang();
        //Assert
        Assert.assertEquals(300, result);
    }

    @Test
    public void NoteDisziplinpunkteKlimmhang_45s_1Punkt00()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 45, 170);
        //Act
        double result = Auswertung.NoteKlimmhang();
        //Assert
        Assert.assertEquals(2.49, result, 0.001);
    }


    @Test
    public void BerechnungDerNote_225Punkte_Result()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 30, 170);
        //Act
        double result = Auswertung.BerechnungDerNote(225);
        //Assert
        Assert.assertEquals(3.24, result, 0.001);
    }

    @Test
    public void BerechnungDerNote_455Punkte_100()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 35, 30, 170);
        //Act
        double result = Auswertung.BerechnungDerNote(455);
        //Assert
        Assert.assertEquals(1.00, result, 0.001);
    }

    @Test
    public void Gesamtnote_Bestleistungen_Sehrgut()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 30, 90, 170);
        //Act
        double result = Auswertung.Gesamtnote();
        //Assert
        Assert.assertEquals(1.00, result, 0.001);
    }


    @Test
    public void Gesamtnote_MannAlter50J_417Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 50, 40, 60, 230);
        //Act
        double result = Auswertung.Gesamtnote();
        //Assert
        Assert.assertEquals(1.25, result, 0.001);
    }

    @Test
    public void Gesamtnote_Mann35Bestzeiten_507Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 35, 35, 86, 170);
        //Act
        double result = Auswertung.Gesamtnote();
        //Assert
        Assert.assertEquals(1.00, result, 0.001);
    }

    @Test
    public void BerechneGesamtnote_Frau35Bestzeiten_626Punkte()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(true, 35, 35, 86, 170);
        //Act
        double result = Auswertung.Gesamtnote();
        //Assert
        Assert.assertEquals(1.00, result, 0.001);
    }

    @Test
    public void Gesamtbewertung_Bestleistungen_Sehrgut()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 30, 90, 170);
        //Act
        String result = Auswertung.BewertungDerGesamtnote();
        //Assert
        Assert.assertEquals("Sehr gut", result);
    }


    @Test
    public void Gesamtbewertung_MannAlter50J_Sehrgut()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 50, 40, 60, 230);
        //Act
        String result = Auswertung.BewertungDerGesamtnote();
        //Assert
        Assert.assertEquals("Sehr gut", result);
    }

    @Test
    public void Gesamtbewertung_Mann35Bestzeiten_Sehrgut()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 35, 35, 86, 170);
        //Act
        String result = Auswertung.BewertungDerGesamtnote();
        //Assert
        Assert.assertEquals("Sehr gut", result);
    }

    @Test
    public void BerechneGesamtbewertung_Frau35Bestzeiten_Sehrgut()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(true, 35, 35, 86, 170);
        //Act
        String result = Auswertung.BewertungDerGesamtnote();
        //Assert
        Assert.assertEquals("Sehr gut", result);
    }

    @Test
    public void Gesamtbewertung_MannMindestleistung_Ausreichend()
    {
        //Arrange
        DataProcessingModel Auswertung = new DataProcessingModel(false, 18, 60, 5, 390);
        //Act
        String result = Auswertung.BewertungDerGesamtnote();
        //Assert
        Assert.assertEquals("Ausreichend", result);
    }
}
