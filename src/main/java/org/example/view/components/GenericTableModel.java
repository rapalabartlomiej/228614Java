package org.example.view.components;

public interface GenericTableModel<T> {
    void addItem(T item);
    void removeItem(int index);
    T getItem(int index);
}
