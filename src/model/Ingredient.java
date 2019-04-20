package model;

public class Ingredient {
    private int id;
    private String name;
    private double amount;
    private double unit;
    //TODO: change the unit of identifier "unit" from "double" to "Unit", need to add a class for "unit" called "Unit"

    public Ingredient(int id, String name,double amount,double unit) {
        //TODO: all these attributes should be read from database after insert successfully
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getAmount()
    {
        return this.amount;
    }

    //TODO: replace "double" to "unit"
    public double getUnit()
    {
        return this.unit;
    }

    public void setUnit(double unit)
    {
        this.unit = unit;
    }
}
