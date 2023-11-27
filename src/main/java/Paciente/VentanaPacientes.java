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

        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Propietario");
        modelo.addColumn("Edad");
        modelo.addColumn("Categoría");
        modelo.addColumn("Fecha Inscripción");

        
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

        
        tablaPacientes = new JTable(modelo);

        
        JScrollPane scrollPane = new JScrollPane(tablaPacientes);

        
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null); 
        setVisible(true);
    }
}

