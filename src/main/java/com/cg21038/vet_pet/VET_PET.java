/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.cg21038.vet_pet;

import Citas.FormularioCitas;
import Paciente.FormularioPacientes;
import Vacunas.FormularioVacunas;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author ucruz
 */
public class VET_PET {

    public static void main(String[] args) {
            VetPetManager.getInstance().cargarDatosDesdeJSON();
            SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Menú Principal");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridLayout(4, 1));

            JButton btnFormularioVacunas = new JButton("Formulario de Vacunas");
            btnFormularioVacunas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FormularioVacunas formularioVacunas = new FormularioVacunas();
                    formularioVacunas.mostrarFormulario();
                }
            });
            panel.add(btnFormularioVacunas);

            JButton btnFormularioPacientes = new JButton("Formulario de Pacientes");
            btnFormularioPacientes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FormularioPacientes formularioPacientes = new FormularioPacientes();
                    formularioPacientes.mostrarFormulario();
                }
            });
            panel.add(btnFormularioPacientes);

            JButton btnFormularioCitas = new JButton("Formulario de Citas");
            btnFormularioCitas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FormularioCitas formularioCitas = new FormularioCitas();
                    formularioCitas.mostrarFormulario();
                }
            });
            panel.add(btnFormularioCitas);

            frame.getContentPane().add(panel);
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    }
   

