package org.example.IGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JDialog{
    private JPanel mainPanel;
    private JButton cargarDatosButton;
    private JButton verDatosButton;
    private JButton salirButton;
    private JPanel Display;

    public Principal (JFrame parent) {
        super(parent);
        setTitle("Menú Principal");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(550,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        cargarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargadeDatos cargaDatosDialog = new CargadeDatos(null);
            }
        });

        setVisible(true);
    }
    public static void main(String[] args) {
        Principal display = new Principal(null);
    }
}
