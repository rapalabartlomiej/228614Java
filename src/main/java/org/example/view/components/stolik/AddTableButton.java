package org.example.view.components.stolik;

import org.example.model.Table;
import org.example.repository.TableREPOSITORY;

import javax.swing.*;


public class AddTableButton extends JButton {
    public AddTableButton(String text, TableREPOSITORY model, TableInputPanel input) {
        super(text);
        addActionListener(e -> {
            if (!validateInput(input)) return;

            Table table = createTable(input);
            model.addTable(table);
            input.clear();
        });
    }

    public boolean validateInput(TableInputPanel input) {
        if (input.getNumber().isEmpty() || input.getSeats().isEmpty()) {
            showError("Wszystkie pola muszą być wypełnione!");
            return false;
        }

        try {
            Integer.parseInt(input.getNumber());
            Integer.parseInt(input.getSeats());
        } catch (NumberFormatException ex) {
            showError("Nieprawidłowy format liczbowy!");
            return false;
        }
        return true;
    }

    public Table createTable(TableInputPanel input) {
        return new Table(
                Integer.parseInt(input.getNumber()),
                Integer.parseInt(input.getSeats())
        );
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Błąd",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
