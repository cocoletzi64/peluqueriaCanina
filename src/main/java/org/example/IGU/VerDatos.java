package org.example.IGU;

import org.example.logica.ControladoraLogica;
import org.example.logica.Mascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerDatos extends JDialog {
    ControladoraLogica controladoraLogica=new ControladoraLogica();

    private JTable tablaMascotas;
    private JButton eliminarButton;
    private JButton editarButton;
    private JPanel VerDattos;
    private JScrollPane scrollPane;
    private JPanel panelBotones; // Nuevo panel para contener los botones

    public VerDatos(JFrame dato) {
        super(dato);
        setTitle("Ver Datos");
        setContentPane(VerDattos);
        setMinimumSize(new Dimension(550, 500));
        setModal(true);
        setLocationRelativeTo(dato);

        // Inicializa la JTable
        tablaMascotas = new JTable();
        // Envuelve la JTable en un JScrollPane
        scrollPane = new JScrollPane(tablaMascotas);

        // Inicializa los botones
        eliminarButton = new JButton("Eliminar");
        editarButton = new JButton("Editar");

        // Crea un nuevo JPanel para los botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Puedes usar otro LayoutManager si lo prefieres
        panelBotones.add(editarButton);
        panelBotones.add(eliminarButton);

        // Establece el layout del JPanel principal
        VerDattos.setLayout(new BorderLayout());
        // Agrega el JScrollPane al centro
        VerDattos.add(scrollPane, BorderLayout.CENTER);
        // Agrega el panel de botones al sur (puedes elegir otra posición)
        VerDattos.add(panelBotones, BorderLayout.SOUTH);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent evt) {
                cargarTabla();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controlo que la tabla no este vacia
                if (tablaMascotas.getRowCount()>0){
                    //controlo que se haya seleccionado un registro
                    if(tablaMascotas.getSelectedRow()!=-1){
                        int num_cliente =Integer.parseInt(String.valueOf(tablaMascotas.getValueAt(tablaMascotas.getSelectedRow(),0)));
                        controladoraLogica.borrarMascota(num_cliente);
                        mostrarMensaje("Mascota eliminada correctamente","Info","Borrado de mascota");
                        setVisible(true);
                        ;
                    }
                    else {
                        mostrarMensaje("No se selecciono ninguna mascota","Error", "Error al eliminar");
                        setVisible(true);
                    }
                }
                else {
                    mostrarMensaje("No hay nada para eliminar en la tabla","Error","Error al eliminar");
                    setVisible(true);
                }
            }
        });
        setVisible(true);
    }
    public void mostrarMensaje(String mensaje, String tipo, String titulo){
        if(tipo.equals("Info")){
            JOptionPane.showMessageDialog(null,mensaje);
            cargarTabla();
            setVisible(true);
        } else if (tipo.equals("Error")) {
            JOptionPane.showMessageDialog(null,mensaje);
            setVisible(true);
            cargarTabla();
        }

    }


    private void cargarTabla() {
        // definir modelo de la tabla
        DefaultTableModel tabla = new DefaultTableModel() {
            // fila y columna no sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // establecemos nombres de columnas
        Object titulos[] = {"Num", "Nombre", "Color", "Raza", "Alergico", "At. Especial", "Dueño", "Cel"};
        tabla.setColumnIdentifiers(titulos);

        // carga datos desde base de datos
        List<Mascota> listaMascotas = controladoraLogica.traerMascotas();

        // recorrer lista
        if (listaMascotas != null) {
            for (Mascota masc : listaMascotas) {
                Object[] objeto = {
                        masc.getNum_cliente(),
                        masc.getNombre(),
                        masc.getColor(),
                        masc.getRaza(),
                        masc.getAlergico(),
                        masc.getEspecial(),
                        masc.getDuenio().getNombre(),
                        masc.getDuenio().getCel()
                };
                tabla.addRow(objeto);
            }
            tablaMascotas.setModel(tabla);
        }
    }
}
