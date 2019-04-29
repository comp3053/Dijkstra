package model;

import java.util.Date;

public class BrewingRecord {
    private int id;
    private Date brewDate;
    private int batchSize;

    public BrewingRecord(Date brew_date,int batch_size){
        setBatchSize(batch_size);
        setBrewDate(brew_date);
    }

    public BrewingRecord(int id, Date brew_date,int batch_size){
        setID(id);
        setBatchSize(batch_size);
        setBrewDate(brew_date);
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Date getBrewDate() {
        return brewDate;
    }

    public void setBrewDate(Date brewDate) {
        this.brewDate = brewDate;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
}
