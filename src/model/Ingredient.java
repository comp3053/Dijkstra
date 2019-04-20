package model;

import utils.UnitEnum;

public class Ingredient {
    private int id;
    private String name;
    private double amount;
    private UnitEnum unit;

    public Ingredient(int id, String name, double amount, UnitEnum unit) throws EmptyIngredientNameException,
            InvalidIngredientAmountException {
        setID(id);
        setName(name);
        setAmount(amount);
        setUnit(unit);
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws EmptyIngredientNameException {
        if (name.isEmpty()) {
            throw new EmptyIngredientNameException("Ingredient name cannot be empty!");
        } else {
            this.name = name;
        }
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) throws InvalidIngredientAmountException {
        if (amount < 0) {
            throw new InvalidIngredientAmountException("Ingredient amount should be greater than 0!");
        } else {
            this.amount = amount;
        }
    }

    public UnitEnum getUnit() {
        return this.unit;
    }

    public void setUnit(UnitEnum unit) {
        this.unit = unit;
    }
}
