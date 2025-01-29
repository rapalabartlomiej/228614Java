package org.example.repository;

import org.example.model.Dish;
import org.example.model.Table;
import org.example.view.components.rezerwacje.ReservationPanel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DishTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nazwa dania", "Cena (zł)", "Opis"};
    private final List<Dish> dishes = new ArrayList<>();
    private ReservationPanel reservationPanel;


    public void reservation(ReservationPanel reservationPanel){
        this.reservationPanel = reservationPanel;
    }



    @Override
    public int getRowCount() {
        return dishes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dish dish = dishes.get(rowIndex);
        switch (columnIndex) {
            case 0: return dish.getName();
            case 1: return dish.getPrice();
            case 2: return dish.getDescription();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
        reservationPanel.update();
        fireTableRowsInserted(dishes.size() - 1, dishes.size() - 1);
    }

    // Poprawnie dodana metoda removeDish w ramach istniejącej klasy
    public void removeDish(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < dishes.size()) {
            dishes.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }
    public List<Dish> getDishList() {
        return new ArrayList<>(dishes);
    }
}