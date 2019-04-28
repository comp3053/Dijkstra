import view.RecommendIngredientListView;

public class Starter {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // run that code on the event dispatch thread
            @Override
            public void run() {
                RecommendIngredientListView hv = new RecommendIngredientListView();
                hv.setVisible(true);
            }
        });
    }
}
