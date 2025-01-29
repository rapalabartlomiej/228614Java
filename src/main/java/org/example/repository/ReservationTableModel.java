package org.example.repository;

import org.example.logic.ReservationManager;
import org.example.model.Reservation;

import javax.swing.table.AbstractTableModel;


public class ReservationTableModel extends AbstractTableModel {
    private final String[] columns = {"ID", "Stolik", "Od", "Do", "Klient"};
    private ReservationManager reservationManager;

    public ReservationTableModel(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    @Override
    public int getRowCount() {
        return reservationManager.getReservations().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Reservation res = reservationManager.getReservations().get(row);
        switch (col) {
            case 0: return res.getId();
            case 1: return "Stolik #" + res.getTable().getNumber();
            case 2: return res.getStartTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            case 3: return res.getEndTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            case 4: return res.getCustomerName();
            default: return null;
        }
    }
}