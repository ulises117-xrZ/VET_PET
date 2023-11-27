/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paciente;

/**
 *
 * @author ucruz
 */import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaPacientes extends JFrame {
    private JTable tablaPacientes;

    public VentanaPacientes(List<Paciente> listaPacientes) {
        setTitle("Lista de Pacientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Propietario");
        modelo.addColumn("Edad");
        modelo.addColumn("Categoría");
        modelo.addColumn("Fecha Inscripción");

        // Agregar filas al modelo
        for (Paciente paciente : listaPacientes) {
            Object[] fila = {
                    paciente.getID(),
                    paciente.getNombre(),
                    paciente.getNombrePropietario(),
                    paciente.getEdad(),
                    paciente.getCategoria(),
                    paciente.getFechaInscripcion()
            };
            modelo.addRow(fila);
        }

        // Crear tabla con el modelo
        tablaPacientes = new JTable(modelo);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaPacientes);

        // Agregar el JScrollPane a la ventana
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }
}

