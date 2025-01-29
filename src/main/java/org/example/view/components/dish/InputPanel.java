package org.example.view.components.dish;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField descriptionField;

    // Konstruktor
    public InputPanel() {
        setLayout(new GridLayout(4, 2, 5, 5));

        // Tworzenie pól tekstowych
        nameField = new JTextField(15);
        priceField = new JTextField(8);
        descriptionField = new JTextField(20);

        add(new JLabel("Nazwa dania:"));
        add(nameField);
        add(new JLabel("Cena (zł):"));
        add(priceField);
        add(new JLabel("Opis:"));
        add(descriptionField);
        add(new JLabel()); // Puste miejsce w układzie

    }


    public void addButton(AddDishButton addDishButton){
        add(addDishButton);
    }
    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    // Metoda do resetowania pól tekstowych
    public void clearFields() {
        nameField.setText("");
        priceField.setText("");
        descriptionField.setText("");
    }
}
