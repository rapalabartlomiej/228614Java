package org.example.view.components.common;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DeleteRow {
    public static void addDeleteFunctionality(JTable table, Runnable action) {
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    action.run();
                }
            }
        });
    }
}