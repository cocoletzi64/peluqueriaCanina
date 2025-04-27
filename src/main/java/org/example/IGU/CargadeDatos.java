package org.example.IGU;

import org.example.logica.ControladoraLogica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CargadeDatos extends JDialog {
    ControladoraLogica control=new ControladoraLogica();
    private JTextField Nombre;
    private JTextField Raza;
    private JTextField Color;
    private JTextField NombreDueño;
    private JTextField CelDueño;
    private JPanel CargaDatos;
    private JButton limpiarButton;
    private JButton guardarButton;
    private JTextArea Observaciones;
    private JComboBox Alergioco;
    private JComboBox Atencion;

    public CargadeDatos(Principal val) {
        super(val);
        setTitle("Carga de Datos");
        setContentPane(CargaDatos);
        setMinimumSize(new Dimension(550, 500));
        setModal(true);
        setLocationRelativeTo(val);
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nombre.setText("");
                Raza.setText("");
                Color.setText("");
                NombreDueño.setText("");
                CelDueño.setText("");
                Observaciones.setText("");
                Alergioco.setSelectedIndex(0);
                Atencion.setSelectedIndex(0);
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombremasco=Nombre.getText();
                String raza=Raza.getText();
                String color=Color.getText();
                String nombreDue=NombreDueño.getText();
                String celDue=CelDueño.getText();
                String obser=Observaciones.getText();
                String alergico=(String)Alergioco.getSelectedItem();
                String atencion=(String)Atencion.getSelectedItem();

                control.guardar(nombremasco,raza,color,nombreDue,celDue,
                        obser,alergico,atencion);

                JOptionPane.showMessageDialog(null,"guardado con exito");
                setVisible(true);

            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        CargadeDatos carga=new CargadeDatos(null);
    }

}

