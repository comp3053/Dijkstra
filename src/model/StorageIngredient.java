package model;

import utils.EmptyIngredientNameException;
import utils.InvalidIngredientAmountException;
import utils.UnitEnum;

public class StorageIngredient extends Ingredient {
    public StorageIngredient(String name, double amount, UnitEnum unit) throws EmptyIngredientNameException,
            InvalidIngredientAmountException {
        super(name, amount, unit);
    }

    public StorageIngredient(int id, String name, double amount, UnitEnum unit) throws EmptyIngredientNameException,
            InvalidIngredientAmountException {
        super(id, name, amount, unit);
    }

    public void addAmount(double addition_amount) throws InvalidIngredientAmountException {
        if (addition_amount <= 0) {
            throw new InvalidIngredientAmountException("Addition amount should be greater than 0!");
        } else {
            this.setAmount(this.getAmount() + addition_amount);
        }
    }
}
