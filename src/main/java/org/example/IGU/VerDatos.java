package org.example.IGU;

import org.example.logica.ControladoraLogica;
import org.example.logica.Mascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VerDatos extends JDialog{
    ControladoraLogica controladoraLogica=new ControladoraLogica();
    private JTable table1;
    private JButton eliminarButton;
    private JButton editarButton;
    private JPanel VerDattos;

    private void formWindowOpened(java.awt.event.WindowEvent evt){
        cargarTabla();
    }

    public VerDatos (JFrame dato){
        super(dato);
        setTitle("Ver Datos");
        setContentPane(VerDattos);
        setMinimumSize(new Dimension(550, 500));
        setModal(true);
        setLocationRelativeTo(dato);
        setVisible(true);
    }
    private void cargarTabla() {
    //definir modelo de la tabla
        DefaultTableModel tabla=new DefaultTableModel(){

            //fila y columna no sean editables
            public boolean isCellEditable (int row, int column){
                return false;
            }
        };
        //establecemos nombres de columnas
        String titulos[]={"Num", "Nombre","Color","Raza","Alergico","At. Especial","Dueño","Cel"};
        tabla.setColumnIdentifiers(titulos);

        //carga datos desde base de datos
        List<Mascota>listaMascotas =controladoraLogica.traerMasoctas();

        //recorrer lista

    }

}
