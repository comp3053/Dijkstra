package model;

public class Equipment {
    private String name;
    private int volume;

    public Equipment(String name, int volume) {
        this.name = name;
        this.volume = volume;
        // TODO: save data to the database
    }

    public String getName() {
        // TODO: get data from database
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        // TODO save "name" to the database
    }

    public int getVolume() {
        // TODO: get data from database

        return this.volume;

    }

    public void setVolume(int volume) {
        if(volume >= 0)
            this.volume = volume;
        else
            System.out.println("Invalid Input");
        // TODO save "volume" to the database
    }
}