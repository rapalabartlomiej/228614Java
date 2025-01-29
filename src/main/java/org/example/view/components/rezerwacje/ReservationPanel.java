package org.example.view.components.rezerwacje;

import org.example.logic.ReservationManager;
import org.example.model.Reservation;
import org.example.model.Table;
import org.example.repository.ReservationTableModel;
import org.example.repository.TableREPOSITORY;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class ReservationPanel extends JPanel {
    private final ReservationManager reservationManager = new ReservationManager();
    private final TableREPOSITORY tableModel;
    private final JComboBox<Table> tableComboBox;
    private final JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
    private final JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
    private final JTextField durationField = new JTextField(4);
    private final JTextField customerField = new JTextField(20);
    private final ReservationTableModel reservationTableModel;
    private final DefaultComboBoxModel<Table> tableComboBoxModel;


    public ReservationPanel(TableREPOSITORY tableModel) {
        this.tableModel = tableModel;
        this.reservationTableModel = new ReservationTableModel(reservationManager); // Inicjalizacja modelu tabeli rezerwacji
        this.tableComboBoxModel = new DefaultComboBoxModel<>(tableModel.getTables().toArray(new Table[0]));
        this.tableComboBox = new JComboBox<>(tableComboBoxModel);

        configureComponents();
        buildLayout();
    }
    public void update(){
        tableComboBox.setModel(new DefaultComboBoxModel<>(tableModel.getTables().toArray(new Table[0])));
    }

    private void configureComponents() {
        // Konfiguracja spinnerów daty i godziny
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm"));

        // Konfiguracja renderera dla JComboBox
        tableComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Table) {
                    Table table = (Table) value;
                    value = "Stolik #" + table.getNumber() + " (" + table.getSeats() + " os.)";
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        // Ustaw domyślny stolik, jeśli lista nie jest pusta
        if (!tableModel.getTables().isEmpty()) {
            tableComboBox.setSelectedIndex(0);
        }
    }

    private void buildLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Rezerwacje"));

        // Panel formularza
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.add(new JLabel("Stolik:")); formPanel.add(tableComboBox);
        formPanel.add(new JLabel("Data:")); formPanel.add(dateSpinner);
        formPanel.add(new JLabel("Godzina:")); formPanel.add(timeSpinner);
        formPanel.add(new JLabel("Czas trwania (h):")); formPanel.add(durationField);
        formPanel.add(new JLabel("Klient:")); formPanel.add(customerField);

        // Przycisk dodawania rezerwacji
        JButton addButton = new JButton("Dodaj rezerwację");
        addButton.addActionListener(e -> addReservation());

        // Tabela rezerwacji
        JTable reservationTable = new JTable(reservationTableModel);
        JScrollPane scrollPane = new JScrollPane(reservationTable);

        // Panel dolny z formularzem i przyciskiem
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.NORTH);
        bottomPanel.add(addButton, BorderLayout.SOUTH);

        // Dodanie komponentów do głównego panelu
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addReservation() {
        try {
            tableComboBox.setModel(new DefaultComboBoxModel<>(tableModel.getTables().toArray(new Table[0])));
            Table table = (Table) tableComboBox.getSelectedItem();
            if (table == null) {
                JOptionPane.showMessageDialog(this, "Wybierz stolik!", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Pobierz datę i godzinę
            LocalDateTime start = LocalDateTime.of(
                    ((java.util.Date) dateSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(),
                    LocalTime.parse(((JSpinner.DefaultEditor) timeSpinner.getEditor()).getTextField().getText())
            );

            // Pobierz czas trwania i dane klienta
            int duration = Integer.parseInt(durationField.getText());
            String customer = customerField.getText().trim();

            // Sprawdź dostępność stolika
            if (!reservationManager.isTableAvailable(table, start, start.plusHours(duration))) {
                JOptionPane.showMessageDialog(this, "Stolik zajęty w tym terminie!", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Dodaj rezerwację
            reservationManager.addReservation(new Reservation(start, start.plusHours(duration), table, customer));
            reservationTableModel.fireTableDataChanged(); // Powiadom tabelę o zmianach

            // Odśwież widok tabeli
            JTable reservationTable = (JTable) ((JScrollPane) getComponent(0)).getViewport().getView();
            reservationTable.revalidate();
            reservationTable.repaint();

            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowe dane!", "Błąd", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Debugowanie: wypisz błąd
        }
    }

    private void clearForm() {
        durationField.setText("");
        customerField.setText("");
    }


}