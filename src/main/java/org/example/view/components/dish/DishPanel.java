package org.example.view.components.dish;

import org.example.repository.DishTableModel;
import org.example.view.components.common.DeleteRow;

import javax.swing.*;
import java.awt.*;

public class DishPanel extends JPanel {
    private final DishTableModel tableModel;
    private final JTable table;
    private final InputPanel inputPanel = new InputPanel();

    public DishPanel(DishTableModel dishTableModel) {
        tableModel = dishTableModel;
        table = new JTable(tableModel);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Zarządzanie daniami"));

        AddDishButton addDishButton = new AddDishButton("Dodaj danie", tableModel, inputPanel);
        inputPanel.addButton(addDishButton);

        DeleteRow.addDeleteFunctionality(table, () -> {
            int row = table.getSelectedRow();
            if(row != -1) tableModel.removeDish(row);
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
}