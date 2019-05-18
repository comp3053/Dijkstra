package controller;

import model.StorageIngredient;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import utils.UnitEnum;
import view.IngredientListView;

public class IngredientFormController {
    private StorageIngredient m;

    public IngredientFormController(StorageIngredient m) {
        this.m = m;
    }

    /**
     * Save current ingredient to database.
     * @param Name Name of ingredient you want to save.
     * @param Amount Amount of ingredient you want to save.
     * @param Unit Unit of ingredient you want to save.
     * @return
     */
    public boolean saveIngredient(String Name, double Amount, int Unit) {
        try {
            m.setName(Name);
            m.setAmount(Amount);
        } catch (EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }

        switch (Unit) {
            case 0:
                m.setUnit(UnitEnum.valueOf("MILLILITER"));
                break;
            case 1:
                m.setUnit(UnitEnum.valueOf("LITER"));
                break;
            case 2:
                m.setUnit(UnitEnum.valueOf("GRAM"));
                break;
            case 3:
                m.setUnit(UnitEnum.valueOf("KILOGRAM"));
                break;
        }
        if (m.getID() == 0)
            return m.insert();
        return m.update();
    }

    /**
     * Cancel the edit of current ingredient and go back to ingredient list.
     */
    public void cancel() {
        IngredientListController ilc = new IngredientListController();
        try {
            IngredientListView ilv = new IngredientListView(ilc, StorageIngredient.getAll());
            ilv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }
}
