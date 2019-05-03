package test;

import model.BrewingRecord;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BrewingRecordTest {

    private static BrewingRecord brewingRecord;
    java.util.Date date = new java.util.Date();

    @Before
    public void setUp() throws Exception {
//        brewingRecord = new BrewingRecord(date,1,10);
// TODO: Need to fix this @Richard
    }

    @Test
    public void getID() {
        assertEquals(1,brewingRecord.getID());
    }

    @Test
    public void setID() {
        brewingRecord.setID(2);
        assertEquals(2,brewingRecord.getID());
    }

    @Test
    public void getBrewDate() {
        assertEquals(date,brewingRecord.getBrewDate());
    }

    @Test
    public void setBrewDate() {
        java.util.Date modifyDate = new java.util.Date();
        brewingRecord.setBrewDate(modifyDate);
        assertEquals(modifyDate,brewingRecord.getBrewDate());
    }

    @Test
    public void getBatchSize() {
        assertEquals(10,brewingRecord.getBatchSize());
    }

    @Test
    public void setBatchSize() {
        brewingRecord.setBatchSize(20);
        assertEquals(20,brewingRecord.getBatchSize());
    }
}