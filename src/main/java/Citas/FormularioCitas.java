/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Citas;


import com.cg21038.vet_pet.VetPetManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import Citas.Citas;
import Paciente.Paciente;

/**
 *
 * @author ucruz
 */


public class FormularioCitas {

    private final VetPetManager manager = VetPetManager.getInstance();
    private JFormattedTextField campoDiaHora;
    private JComboBox<Paciente> listaPacientes;
    private JTextField campoMotivoCita;

    public FormularioCitas() {
    }

    public void mostrarFormulario() {
        JFrame frame = new JFrame("Formulario de Citas");
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

        listaPacientes = new JComboBox<>(manager.getPacientes().toArray(new Paciente[0]));
        listaPacientes.setRenderer(new PacienteCellRenderer());
        campoMotivoCita = new JTextField(20);

        JButton btnAgregarCita = new JButton("Agregar Cita");
        btnAgregarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCita();
            }
        });

        JButton btnMostrarCitas = new JButton("Mostrar citas");
        btnMostrarCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.mostrarListaCitas();
            }
        });

        panel.add(new JLabel("Día y Hora de la Cita (dd-MM-yyyy - HH:mm):"));
        panel.add(campoDiaHora);
        panel.add(new JLabel("Nombre del Paciente:"));
        panel.add(listaPacientes);
        panel.add(new JLabel("Motivo de la Cita:"));
        panel.add(campoMotivoCita);
        panel.add(btnAgregarCita);
        panel.add(btnMostrarCitas);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void agregarCita() {
        String diaHora = campoDiaHora.getText();
        Paciente pacienteSeleccionado = (Paciente) listaPacientes.getSelectedItem();
        String motivoCita = campoMotivoCita.getText();

        if (pacienteSeleccionado != null && validarFecha(diaHora)) {
            Citas nuevaCita = new Citas();
            nuevaCita.setFechaCita(parseFecha(diaHora));
            nuevaCita.setNombrePaciente(pacienteSeleccionado.getNombre());
            nuevaCita.setId_paciente(pacienteSeleccionado.getID());
            nuevaCita.setMotivoCita(motivoCita);
            manager.agregarCita(nuevaCita);

            campoDiaHora.setText("");
            listaPacientes.setSelectedIndex(0);
            campoMotivoCita.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese una fecha y hora válida y seleccione un paciente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarFecha(String fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private Date parseFecha(String fecha) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy - HH:mm").parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class PacienteCellRenderer extends DefaultListCellRenderer {
        @Override
        public java.awt.Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Paciente) {
                value = ((Paciente) value).getNombre();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
}

