package view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RecipeIngredientEntry extends JPanel {
    private JComboBox IngredientSelector;
    private JTextField inputBoxText;
    private JButton addField;
    private JButton minusField;
    private RecipeIngredientEntryList parent;

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

    public JComboBox getIngredientSelector() {
        return IngredientSelector;
    }

    public JTextField getInputBoxText(){
        return inputBoxText;
    }

    public class AddEntryAction extends AbstractAction {

        public AddEntryAction() {
            super("+");
        }

        public void actionPerformed(ActionEvent e) {
            parent.cloneEntry(RecipeIngredientEntry.this);
        }

    }

    public class RemoveEntryAction extends AbstractAction {

        public RemoveEntryAction() {
            super("-");
        }

        public void actionPerformed(ActionEvent e) {
            parent.removeItem(RecipeIngredientEntry.this);

        }
    }

    public void enableAdd(boolean enabled) {
        this.addField.setEnabled(enabled);
    }
    public void enableMinus(boolean enabled) {
        this.minusField.setEnabled(enabled);
    }
}
