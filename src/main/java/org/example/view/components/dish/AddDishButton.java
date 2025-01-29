package org.example.view.components.dish;

import org.example.model.Dish;
import org.example.repository.DishTableModel;

import javax.swing.*;

public class AddDishButton extends JButton {
    public AddDishButton(String label, DishTableModel tableModel, InputPanel inputPanel) {
        super(label); // Ustawienie etykiety przycisku

        // Dodanie ActionListenera
        addActionListener(e -> {
            String name = inputPanel.getNameField().getText().trim();
            String priceText = inputPanel.getPriceField().getText().trim();
            String description = inputPanel.getDescriptionField().getText().trim();

            if (name.isEmpty() || priceText.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(inputPanel, "Wszystkie pola muszą być wypełnione!", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Float price = Float.parseFloat(priceText);
                Dish newDish = new Dish(name, price, description);
                tableModel.addDish(newDish); // Dodanie dania do tabeli

                // Czyszczenie pól tekstowych
                inputPanel.clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(inputPanel, "Cena musi być liczbą!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
