package org.example.view.components.stolik;

import org.example.repository.TableREPOSITORY;
import org.example.view.components.common.DeleteRow;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    public final JTable table;
    public final TableREPOSITORY tableModel;
    public final TableInputPanel inputPanel;

    public TablePanel(TableREPOSITORY model) {
        this.tableModel = model;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Zarządzanie stolikami"));

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        inputPanel = new TableInputPanel();
        AddTableButton addButton = new AddTableButton("Dodaj stolik", tableModel, inputPanel);
        inputPanel.addButton(addButton);

        DeleteRow.addDeleteFunctionality(table, () -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) tableModel.removeTable(selectedRow);
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }




}