package org.example.model;

import java.time.LocalDateTime;

public class Table {
    private final int number;
    private final int seats;
    private boolean occupied;
    private LocalDateTime reservedUntil;

    public Table(int number, int seats) {
        this.number = number;
        this.seats = seats;
        this.occupied = false;
        this.reservedUntil = null;
    }

    public boolean isAvailableAt(LocalDateTime dateTime) {
        return !occupied || (reservedUntil != null && dateTime.isAfter(reservedUntil));
    }

    public void setReservationTime(LocalDateTime until) {
        this.reservedUntil = until;
        this.occupied = true;
    }

    public int getNumber() { return number; }
    public int getSeats() { return seats; }
    public boolean isOccupied() { return occupied; }
}