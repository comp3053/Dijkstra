package controller;

import view.IngredientListView;

public class IngredientFormController {
    public IngredientFormController(){

    }

    public void saveIngredient(String Name,String Amount,int Unit){
        //Unfinish
        System.out.println("Parameters: "+ Name + Amount + Unit);
        String strUnit = "ERROR";
        switch(Unit) {
            case 0:
                strUnit = "MILLILITER";
                break;
            case 1:
                strUnit = "LITER";
                break;
            case 2:
                strUnit = "GRAM";
                break;
            case 3:
                strUnit = "KILOGRAM";
                break;
        }
        System.out.println("Parameters: "+ Name + Amount + strUnit);
        /*
            Ingredient ingredient = new Ingredient(Name,Amount,Unit);
            IngredientController ic = new IngredientController();
            return ic.update(ingredient);
        */
        System.out.println("Saved");
    }

    public void cancel(){
        // TODO: Pass in a Ingredient ArrayList
        IngredientListController ilc = new IngredientListController();
        IngredientListView ilv = new IngredientListView(ilc);
        ilv.setVisible(true);
    }
}
