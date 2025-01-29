package org.example.repository;

import org.example.model.Table;
import org.example.view.components.rezerwacje.ReservationPanel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableREPOSITORY extends AbstractTableModel {
    private final List<Table> tables = new ArrayList<>();
    private final String[] columns = {"Numer", "Miejsca", "Status"};
    public ReservationPanel reservationPanel;


    public void reservation(ReservationPanel reservationPanel){
        this.reservationPanel = reservationPanel;
    }
    @Override
    public int getRowCount() { return tables.size(); }
    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public Object getValueAt(int row, int col) {
        Table table = tables.get(row);
        switch(col) {
            case 0: return table.getNumber();
            case 1: return table.getSeats();
            case 2: return table.isOccupied() ? "Zajęty" : "Wolny";
            default: return null;
        }
    }

    @Override
    public String getColumnName(int col) { return columns[col]; }

    public void addTable(Table table) {
        tables.add(table);
        System.out.println("dodaje stolik");
        reservationPanel.update();
        fireTableRowsInserted(tables.size()-1, tables.size()-1);
        System.out.println(getTables().toString());
    }
    public void removeTable(int row) {
        tables.remove(row);
        fireTableRowsDeleted(row, row);
    }
    public List<Table> getTables() {
        return new ArrayList<>(tables);
    }
}