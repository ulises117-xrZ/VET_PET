package Citas;

import Paciente.Paciente;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ucruz
 */
public class VentanaCitas extends JFrame {
    private JTable tablaCitas;

    public VentanaCitas(List<Citas> listaCitas) {
        setTitle("Lista de Citas");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("ID paciente");
        modelo.addColumn("Nombre Paciente");
        modelo.addColumn("Fecha Cita");
        modelo.addColumn("Motivo");

        // Agregar filas al modelo
        for (Citas cita : listaCitas) {
            Object[] fila = {
                    cita.getId_cita(),
                    cita.getId_paciente(),
                    cita.getNombrePaciente(),
                    formatDate(cita.getFechaCita()),
                    cita.getMotivoCita(),
            };
            modelo.addRow(fila);
        }

        // Crear tabla con el modelo
        tablaCitas = new JTable(modelo);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaCitas);

        // Agregar el JScrollPane a la ventana
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
        return dateFormat.format(date);
    }
}
