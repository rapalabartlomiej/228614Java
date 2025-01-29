package org.example.view.components.stolik;

import javax.swing.*;
import java.awt.*;


public class TableInputPanel extends JPanel {
    public final JTextField numberField = new JTextField(10);
    public final JTextField seatsField = new JTextField(10);

    public TableInputPanel() {
        setLayout(new GridLayout(3, 2, 5, 5));
        add(new JLabel("Numer stolika:"));
        add(numberField);
        add(new JLabel("Liczba miejsc:"));
        add(seatsField);
    }

    public void addButton(JButton button) {
        add(new JLabel());
        add(button);
    }

    public String getNumber() { return numberField.getText().trim(); }
    public String getSeats() { return seatsField.getText().trim(); }
    public void clear() {
        numberField.setText("");
        seatsField.setText("");
    }
}