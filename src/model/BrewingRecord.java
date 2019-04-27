package model;

import java.util.Date;

public class BrewingRecord {
    private int id;
    private Date brew_date;
    private int batch_size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBrew_date() {
        return brew_date;
    }

    public void setBrew_date(Date brew_date) {
        this.brew_date = brew_date;
    }

    public int getBatch_size() {
        return batch_size;
    }

    public void setBatch_size(int batch_size) {
        this.batch_size = batch_size;
    }
}
