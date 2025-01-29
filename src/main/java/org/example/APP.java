package org.example;

import org.example.model.Table;
import org.example.repository.DishTableModel;
import org.example.view.components.dish.DishPanel;
import org.example.view.components.rezerwacje.ReservationPanel;
import org.example.view.components.stolik.TablePanel;
import org.example.logic.ReservationManager;
import org.example.repository.TableREPOSITORY;

import javax.swing.*;

public class APP {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Restaurant Manager 2.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);





            DishTableModel dishTableModel =  new DishTableModel(); //repo
            DishPanel dishPanel = new DishPanel(dishTableModel); //panel
            TableREPOSITORY TableREPOSITORY = new TableREPOSITORY(); //repo
            TablePanel tablePanel = new TablePanel(TableREPOSITORY);





            ReservationPanel reservationPanel = new ReservationPanel(TableREPOSITORY);

            TableREPOSITORY.reservation(reservationPanel);
            dishTableModel.reservation(reservationPanel);



            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Dania", dishPanel);
            tabbedPane.addTab("Stoliki", tablePanel);
            tabbedPane.addTab("Rezerwacje", reservationPanel);

            frame.add(tabbedPane);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}