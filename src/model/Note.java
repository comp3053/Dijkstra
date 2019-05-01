package model;

import java.util.Date;

public class Note {
    private int id;
    private int brewID;
    private Date createDate;
    private String content;

    public Note(int brewID, String content){
        setBrewID(brewID);
        setContent(content);
    }

    public Note(int brewID, Date createDate,String content){
        setBrewID(brewID);
        setCreateDate(createDate);
        setContent(content);
    }

    public Note(int id, int brewID, Date createDate,String content){
        setID(id);
        setBrewID(brewID);
        setCreateDate(createDate);
        setContent(content);
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id=id;
    }

    private void setBrewID(int brewID) {
        this.brewID = brewID;
    }

    public int getBrewID() {
        return brewID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    private void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content=content;
    }
}

