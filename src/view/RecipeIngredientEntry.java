package view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

class RecipeIngredientEntry extends JPanel {
    private JComboBox IngredientSelector;
    private JTextField inputBoxText;
    private JButton addField;
    private JButton minusField;
    private RecipeIngredientEntryList parent;

    /**
     * Setup a new recipe ingredient entry.
     * @param comboBox Component to choose ingredient.
     * @param textFieldText The amount of current recipe ingredient.
     * @param list All the RecipeIngredients in the form.
     */
    public RecipeIngredientEntry(JComboBox comboBox, String textFieldText, RecipeIngredientEntryList list) {
        this.IngredientSelector = comboBox;
        this.parent = list;
        this.addField = new JButton(new AddEntryAction());
        this.minusField = new JButton(new RemoveEntryAction());
        this.inputBoxText = new JTextField(10);
        this.inputBoxText.setText(textFieldText);
        add(this.IngredientSelector);
        add(this.inputBoxText);
        add(this.addField);
        add(this.minusField);
    }

    JComboBox getIngredientSelector() {
        return IngredientSelector;
    }

    JTextField getInputBoxText() {
        return inputBoxText;
    }

    /**
     * Initialize AddEntry button and setup action for it.
     */
    public class AddEntryAction extends AbstractAction {

        public AddEntryAction() {
            super("+");
        }

        public void actionPerformed(ActionEvent e) {
            parent.cloneEntry(RecipeIngredientEntry.this);
        }

    }

    /**
     * Initialize RemoveEntry button and setup action for it.
     */
    public class RemoveEntryAction extends AbstractAction {

        public RemoveEntryAction() {
            super("-");
        }

        public void actionPerformed(ActionEvent e) {
            parent.removeItem(RecipeIngredientEntry.this);

        }
    }

    /**
     * Control whether the add button could be clicked.
     * @param enabled Target status of add button.
     */
    void enableAdd(boolean enabled) {
        this.addField.setEnabled(enabled);
    }

    /**
     * Control whether the minus button could be clicked.
     * @param enabled Target status of minus button.
     */
    void enableMinus(boolean enabled) {
        this.minusField.setEnabled(enabled);
    }
}
