package utils;

public class InvalidIngredientAmountException extends Exception {
    public InvalidIngredientAmountException(String msg) {
        super(msg);
    }
}