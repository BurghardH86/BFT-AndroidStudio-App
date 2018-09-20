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
}
