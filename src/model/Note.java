package model;

import java.util.Date;

public class Note {
    private int id;
    private Date create_date;
    private String content;

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id=id;
    }

    public Date getCreateDate(){
        return this.create_date;
    }

    public void setCreateDate(Date create_date){
        this.create_date=create_date;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content=content;
    }
}

