package model;

import java.util.Date;

public class Note {
    private int id;
    private Date createDate;
    private String content;

    public Note(int id,Date create_date,String content){
        setID(id);
        setCreateDate(create_date);
        setContent(content);
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id=id;
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

