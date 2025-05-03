package org.example.IGU;

import org.example.logica.ControladoraLogica;
import org.example.logica.Mascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarDatos extends JDialog{
    ControladoraLogica control=null;
    Mascota masco;
    int num_cliente;
    private JTextField Nombre;
    private JTextField Raza;
    private JTextField Color;
    private JTextField Cel_due;
    private JTextArea Observ;
    private JButton guardarCambiosButton;
    private JButton limpiarButton;
    private JComboBox Alergico;
    private JComboBox AtencionEsp;
    private JPanel ModificadroDatos;
    private JTextField NombreDue;


    public ModificarDatos(ModificarDatos val,int num_cliente) {
        super(val);
        control =new ControladoraLogica();
        setTitle("Carga de Datos");
        setContentPane(ModificadroDatos);
        setMinimumSize(new Dimension(550, 500));
        setModal(true);
        setLocationRelativeTo(val);
        this.num_cliente=num_cliente;




        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nombre.setText("");
                Raza.setText("");
                Color.setText("");
                NombreDue.setText("");
                Cel_due.setText("");
                Observ.setText("");
                Alergico.setSelectedIndex(0);
                AtencionEsp.setSelectedIndex(0);

            }
        });
        cargarDatos(num_cliente);


        guardarCambiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombremasco=Nombre.getText();
                String raza=Raza.getText();
                String color=Color.getText();
                String nombreDue=NombreDue.getText();
                String celDue=Cel_due.getText();
                String obser=Observ.getText();
                String alergico=(String)Alergico.getSelectedItem();
                String atencion=(String)AtencionEsp.getSelectedItem();

                control.modificarMascota(masco,nombremasco,raza,color,nombreDue,celDue,obser,alergico,atencion);
                JOptionPane.showMessageDialog(null,"edicion realizada correctamente");
                VerDatos pantalla=new VerDatos(null);
                dispose();
            }
        });
        setVisible(true);

    }


    private void cargarDatos(int num_cliente) {
        this.masco=control.traerMascota(num_cliente);
        Nombre.setText(masco.getNombre());
        Raza.setText(masco.getRaza());
        Color.setText(masco.getColor());
        NombreDue.setText(masco.getDuenio().getNombre());
        Cel_due.setText(masco.getDuenio().getCel());
        Observ.setText(masco.getObservaciones());
        if (masco.getAlergico().equals("SI")) {
            Alergico.setSelectedIndex(1);
        }
        else if (masco.getAlergico().equals("NO")){
            Alergico.setSelectedIndex(2);
        }
        else {
            Alergico.setSelectedIndex(0);
        }
        if (masco.getEspecial().equals("SI")) {
            AtencionEsp.setSelectedIndex(1);
        }
        else if (masco.getEspecial().equals("NO")){
            AtencionEsp.setSelectedIndex(2);
        }
        else {
            AtencionEsp.setSelectedIndex(0);
        }
    }

}
