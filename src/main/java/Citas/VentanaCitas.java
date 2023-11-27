package Citas;

import com.cg21038.vet_pet.VetPetManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ucruz
 */
public class VentanaCitas extends JFrame {

    private JTable tablaCitas;
    private List<Citas> listaCitas;

    public VentanaCitas(List<Citas> listaCitas) {
        this.listaCitas = listaCitas;
        setTitle("Lista de Citas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("ID paciente");
        modelo.addColumn("Nombre Paciente");
        modelo.addColumn("Fecha Cita");
        modelo.addColumn("Motivo");

        for (Citas cita : listaCitas) {
            Object[] fila = {
                cita.getId_cita(),
                cita.getId_paciente(),
                cita.getNombrePaciente(),
                VetPetManager.getInstance().formatDate(cita.getFechaCita()),
                cita.getMotivoCita(),};
            modelo.addRow(fila);
        }

        tablaCitas = new JTable(modelo);
        tablaCitas.getColumnModel().getColumn(0).setMinWidth(0);
        tablaCitas.getColumnModel().getColumn(0).setMaxWidth(0);

        JScrollPane scrollPane = new JScrollPane(tablaCitas);

        JButton btnEditarCita = new JButton("Editar Cita");
        btnEditarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaCitas.getSelectedRow();
                if (selectedRow != -1) {
                    Citas citaSeleccionada = listaCitas.get(selectedRow);
                    editarCita(citaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una cita para editar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnEliminarCita = new JButton("Eliminar Cita");
        btnEliminarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaCitas.getSelectedRow();
                if (selectedRow != -1) {
                    Citas citaSeleccionada = listaCitas.get(selectedRow);
                    eliminarCita(citaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una cita para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEditarCita);
        panelBotones.add(btnEliminarCita);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addFormEditarCitasClosedListener(WindowAdapter windowAdapter) {
        addWindowListener(windowAdapter);
    }

    private void editarCita(Citas cita) {
        FormEditarCitas formularioEdicion = new FormEditarCitas(this, cita);
        this.addFormEditarCitasClosedListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dispose(); // Cerrar la ventana padre
            }
        });
        formularioEdicion.mostrarFormulario();
    }

    private void eliminarCita(Citas cita) {
        boolean showClose = VetPetManager.getInstance().eliminarCita(cita.getId_cita());
        if (showClose) {
            JOptionPane.showMessageDialog(null, "Cita eliminada exitosamente", "Exito", JOptionPane.OK_OPTION);
            this.dispose();

        }
    }
}
