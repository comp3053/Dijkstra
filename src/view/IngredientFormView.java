package view;

import utils.UnitEnum;

import javax.swing.*;
import java.awt.*;

public class IngredientFormView extends View {

    public IngredientFormView(){
        this.setTitle("Brew Day! - Ingredient Form"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel mainBody = new JPanel();
        mainBody.setLayout(new BoxLayout(mainBody, BoxLayout.Y_AXIS));

        JPanel nameField = new JPanel();
        nameField.setLayout(new BoxLayout(nameField, BoxLayout.Y_AXIS));
        nameField.add(new JLabel("Name"));
        JTextField nameTextField = new JTextField();
//        nameTextField.setColumns(20);
        nameField.add(nameTextField);

        JPanel amountField = new JPanel();
        amountField.setLayout(new BoxLayout(amountField, BoxLayout.Y_AXIS));
        amountField.add(new JLabel("Amount"));
        JTextField amountTextField = new JTextField();
//        amountTextField.setColumns(20);
        amountField.add(amountTextField);

        JPanel unitField = new JPanel();
        unitField.setLayout(new BoxLayout(unitField, BoxLayout.Y_AXIS));
        unitField.add(new JLabel("Unit"));
        JComboBox<UnitEnum> unitSelect = new JComboBox<>();
        for(UnitEnum unit : UnitEnum.values()){
            unitSelect.addItem(unit);
        }
        unitField.add(unitSelect);

        mainBody.add(nameField);
        mainBody.add(amountField);
        mainBody.add(unitField);
        this.add(mainBody, BorderLayout.CENTER);

        JPanel pageEndButtonGroup = new JPanel();
        pageEndButtonGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton btn1 = new JButton("Save");
        JButton btn2 = new JButton("Canel");
        pageEndButtonGroup.add(btn1);
        pageEndButtonGroup.add(btn2);
        this.add(pageEndButtonGroup, BorderLayout.PAGE_END);
    }
    @Override
    public void update() {

    }
}
