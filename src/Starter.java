import controller.HomeController;
import model.Recipe;
import view.HomeView;
import view.RecipeFormView;
import view.RecipeView;
import view.RecommendIngredientListView;

public class Starter {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // run that code on the event dispatch thread
            @Override
            public void run() {
//                HomeController hc = new HomeController();
//                HomeView hv = new HomeView(hc);
//                hv.setVisible(true);
//                RecipeView rv = new RecipeView();
//                rv.setVisible(true);
//                RecommendIngredientListView hv = new RecommendIngredientListView();
//                hv.setVisible(true);
                RecipeFormView rfv = new RecipeFormView();
                rfv.setVisible(true);
            }
        });
    }
}
