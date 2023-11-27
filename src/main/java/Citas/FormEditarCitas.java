/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Citas;

import Paciente.Paciente;
import com.cg21038.vet_pet.VetPetManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author ucruz
 */
public class FormEditarCitas {

    private final VetPetManager manager = VetPetManager.getInstance();

    private JTextField campoDiaHora;
    private JComboBox<Paciente> listaPacientes;
    private JTextField campoMotivoCita;

    private Citas citaExistente;
    private VentanaCitas ventanaPadre; 

    public FormEditarCitas(VentanaCitas ventanaPadre, Citas citaExistente) {
        this.citaExistente = citaExistente;
        this.ventanaPadre = ventanaPadre;
    }

    public void mostrarFormulario() {
        JFrame frame = new JFrame("Formulario de Edición de Citas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##-##-#### - ##:##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        campoDiaHora = new JFormattedTextField(mask);
        listaPacientes = new JComboBox<>(manager.getPacientes().toArray(new Paciente[0])); // Inicializar el JComboBox
        listaPacientes.setRenderer(new PacienteCellRenderer()); // Establecer el renderer personalizado
        campoMotivoCita = new JTextField(20);

        JButton btnGuardarEdicion = new JButton("Guardar Cambios");
        btnGuardarEdicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        campoDiaHora.setText(manager.formatDate(citaExistente.getFechaCita()));
        listaPacientes.setSelectedItem(buscarPacientePorId(citaExistente.getId_paciente()));
        campoMotivoCita.setText(citaExistente.getMotivoCita());

        panel.add(new JLabel("Día y Hora de la Cita (dd-MM-yyyy - HH:mm):"));
        panel.add(campoDiaHora);
        panel.add(new JLabel("Nombre del Paciente:"));
        panel.add(listaPacientes);
        panel.add(new JLabel("Motivo de la Cita:"));
        panel.add(campoMotivoCita);
        panel.add(btnGuardarEdicion);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private void guardarCambios() {
        String diaHora = campoDiaHora.getText();
        Paciente pacienteSeleccionado = (Paciente) listaPacientes.getSelectedItem();
        String motivoCita = campoMotivoCita.getText();

        if (pacienteSeleccionado != null && manager.validarFecha(diaHora)) {
            Citas citaActualizada = new Citas();
            citaActualizada.setId_cita(citaExistente.getId_cita());
            citaActualizada.setId_paciente(pacienteSeleccionado.getID());
            citaActualizada.setNombrePaciente(pacienteSeleccionado.getNombre());
            citaActualizada.setFechaCita(manager.parseFecha(diaHora));
            citaActualizada.setMotivoCita(motivoCita);
            manager.editarCita(citaActualizada.getId_cita(), citaActualizada);

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(campoDiaHora);
            parentFrame.dispose();
            JOptionPane.showMessageDialog(null, "Datos actualizados con exito", "Exito", JOptionPane.OK_OPTION);
            ventanaPadre.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un paciente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Paciente buscarPacientePorId(long idPaciente) {
        for (Paciente paciente : manager.getPacientes()) {
            if (paciente.getID() == idPaciente) {
                return paciente;
            }
        }
        return null;
    }

    private static class PacienteCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Paciente) {
                value = ((Paciente) value).getNombre();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
}
