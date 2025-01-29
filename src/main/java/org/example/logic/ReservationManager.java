package org.example.logic;

import org.example.model.Reservation;
import org.example.model.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private final List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean isTableAvailable(Table table, LocalDateTime start, LocalDateTime end) {
        for (Reservation res : reservations) {
            if (res.getTable().equals(table) && !(end.isBefore(res.getStartTime()) || start.isAfter(res.getEndTime()))) {
                return false;
            }
        }
        return true;
    }
}