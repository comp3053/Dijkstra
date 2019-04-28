package model;

import java.util.Date;

public class BrewingRecord {
    private int id;
    private Date brew_date;
    private int batch_size;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Date getBrewDate() {
        return brew_date;
    }

    public void setBrewDate(Date brew_date) {
        this.brew_date = brew_date;
    }

    public int getBatchSize() {
        return batch_size;
    }

    public void setBatchSize(int batch_size) {
        this.batch_size = batch_size;
    }
}
