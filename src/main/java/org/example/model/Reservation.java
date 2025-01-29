package org.example.model;

import java.time.LocalDateTime;

public class Reservation {
    private static int nextId = 1;
    private final int id;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Table table;
    private final String customerName;
    private final Dish dish;

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Table table, String customerName,Dish dish) {
        this.id = nextId++;
        this.startTime = startTime;
        this.endTime = endTime;
        this.table = table;
        this.customerName = customerName;
        this.dish = dish;

        table.setReservationTime(endTime);
    }

    public int getId() { return id; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Table getTable() { return table; }
    public String getCustomerName() { return customerName; }
    public String getDishName() { return dish.getName(); }
}