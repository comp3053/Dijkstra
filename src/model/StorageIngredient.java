package model;

import utils.EmptyNameException;
import utils.InvalidInputException;
import utils.UnitEnum;

public class StorageIngredient extends Ingredient {
    public StorageIngredient(String name, double amount, UnitEnum unit) throws EmptyNameException,
            InvalidInputException {
        super(name, amount, unit);
    }

    public StorageIngredient(int id, String name, double amount, UnitEnum unit) throws EmptyNameException,
            InvalidInputException {
        super(id, name, amount, unit);
    }

    public void addAmount(double addition_amount) throws InvalidInputException {
        if (addition_amount <= 0) {
            throw new InvalidInputException("Addition amount should be greater than 0!");
        } else {
            this.setAmount(this.getAmount() + addition_amount);
        }
    }
}
