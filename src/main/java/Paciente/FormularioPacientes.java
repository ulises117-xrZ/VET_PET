/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paciente;

import com.cg21038.vet_pet.VetPetManager;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormularioPacientes {

    private final VetPetManager manager = VetPetManager.getInstance();

    private JTextField campoNombrePaciente;
    private JTextField campoNombreDueno;
    private JFormattedTextField campoFechaNacimiento; // Cambiado a JFormattedTextField
    private JTextField campoEdad;
    private JTextField campoCategoria;
    private JTextField campoRaza;
    private JTextField campoSexo;
    private JTextField campoAltura;
    private JTextField campoPeso;
    private JTextField campoPelaje;

    public FormularioPacientes() {
    }

    public void mostrarFormulario() {
        JFrame frame = new JFrame("Formulario de Pacientes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        campoNombrePaciente = new JTextField(20);
        campoNombreDueno = new JTextField(20);

        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("##-##-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        campoFechaNacimiento = new JFormattedTextField(formatter);

        campoEdad = new JTextField(20);
        campoCategoria = new JTextField(20);
        campoRaza = new JTextField(20);
        campoSexo = new JTextField(20);
        campoAltura = new JTextField(20);
        campoPeso = new JTextField(20);
        campoPelaje = new JTextField(20);

        JButton btnAgregarPaciente = new JButton("Agregar Paciente");
        btnAgregarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPaciente();
            }
        });

        JButton btnMostrarPacientes = new JButton("Mostrar Pacientes");
        btnMostrarPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.mostrarListaPaciente();
            }
        });

        panel.add(new JLabel("Nombre del Paciente:"));
        panel.add(campoNombrePaciente);
        panel.add(new JLabel("Nombre del Dueño:"));
        panel.add(campoNombreDueno);
        panel.add(new JLabel("Fecha de Nacimiento (día-mes-año):"));
        panel.add(campoFechaNacimiento);
        panel.add(new JLabel("Edad del paciente:"));
        panel.add(campoEdad);
        panel.add(new JLabel("Categoria:"));
        panel.add(campoCategoria);
        panel.add(new JLabel("Raza:"));
        panel.add(campoRaza);
        panel.add(new JLabel("Sexo:"));
        panel.add(campoSexo);
        panel.add(new JLabel("Altura:"));
        panel.add(campoAltura);
        panel.add(new JLabel("Peso:"));
        panel.add(campoPeso);
        panel.add(new JLabel("Pelaje:"));
        panel.add(campoPelaje);
        panel.add(btnAgregarPaciente);
        panel.add(btnMostrarPacientes);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(400, 600);
        frame.setVisible(true);
    }

    private void agregarPaciente() {
        String nombre = campoNombrePaciente.getText();
        String nombreDueno = campoNombreDueno.getText();
        String fechaNacimiento = campoFechaNacimiento.getText();
        String edad = campoEdad.getText();
        String categoria = campoCategoria.getText();
        String raza = campoRaza.getText();
        String sexo = campoSexo.getText();
        String altura = campoAltura.getText();
        String peso = campoPeso.getText();
        String pelaje = campoPelaje.getText();
        
        if (nombre.isEmpty() || nombreDueno.isEmpty() || fechaNacimiento.isEmpty() || edad.isEmpty()
                || categoria.isEmpty() || raza.isEmpty() || sexo.isEmpty() || altura.isEmpty() || peso.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            dateFormat.parse(fechaNacimiento);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Utilice dd-MM-yyyy", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Integer.parseInt(edad);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        try {
            Double.parseDouble(altura);
            Double.parseDouble(peso);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La altura y el peso deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setNombre(nombre);
        nuevoPaciente.setNombrePropietario(nombreDueno);
        nuevoPaciente.setFecha_nacimiento(fechaNacimiento);
        nuevoPaciente.setFechaInscripcion(manager.formatDate(new Date()));
        nuevoPaciente.setEdad(Integer.parseInt(edad));
        nuevoPaciente.setCategoria(categoria);
        nuevoPaciente.setRaza(raza);
        nuevoPaciente.setSexo(sexo);
        nuevoPaciente.setPeso(Double.parseDouble(peso));
        nuevoPaciente.setAltura(Double.parseDouble(altura));
        nuevoPaciente.setPelaje(pelaje);
        manager.agregarPaciente(nuevoPaciente);
        campoNombrePaciente.setText("");
        campoNombreDueno.setText("");
        campoFechaNacimiento.setText("");
        campoEdad.setText("");
        campoRaza.setText("");
        campoCategoria.setText("");
        campoSexo.setText("");
        campoAltura.setText("");
        campoPelaje.setText("");
        campoPeso.setText("");
    }
}
