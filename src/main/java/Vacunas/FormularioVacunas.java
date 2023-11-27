/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vacunas;

/**
 *
 * @author ucruz
 */
import com.cg21038.vet_pet.VetPetManager;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormularioVacunas{

    private final VetPetManager manager = VetPetManager.getInstance();;

    private JTextField campoFechaVacuna;
    private JTextField campoNombreVacuna;
    private JTextField campoPeso;
    private JTextField campoAltura;
    private JTextField campoEdad;
    private JTextField campoIdPacienteVacuna;

    public FormularioVacunas() {
    }

    public void mostrarFormulario() {
        JFrame frame = new JFrame("Formulario de Vacunas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        campoFechaVacuna = new JTextField(20);
        campoNombreVacuna = new JTextField(20);
        campoPeso = new JTextField(20);
        campoAltura = new JTextField(20);
        campoEdad = new JTextField(20);
        campoIdPacienteVacuna = new JTextField(20);

        JButton btnAgregarVacuna = new JButton("Agregar Vacuna");
        btnAgregarVacuna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVacuna();
            }
        });

        panel.add(new JLabel("Fecha de la Vacuna:"));
        panel.add(campoFechaVacuna);
        panel.add(new JLabel("Nombre de la Vacuna:"));
        panel.add(campoNombreVacuna);
        panel.add(new JLabel("Peso:"));
        panel.add(campoPeso);
        panel.add(new JLabel("Altura:"));
        panel.add(campoAltura);
        panel.add(new JLabel("Edad:"));
        panel.add(campoEdad);
        panel.add(new JLabel("ID del Paciente:"));
        panel.add(campoIdPacienteVacuna);
        panel.add(btnAgregarVacuna);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private void agregarVacuna() {
        String fechaVacuna = campoFechaVacuna.getText();
        String nombreVacuna = campoNombreVacuna.getText();
        String pesoStr = campoPeso.getText();
        String alturaStr = campoAltura.getText();
        String edadStr = campoEdad.getText();
        String idPacienteStr = campoIdPacienteVacuna.getText();
        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);
        int edad = Integer.parseInt(edadStr);
        int idPaciente = Integer.parseInt(idPacienteStr);

        Vacuna nuevaVacuna = new Vacuna();
        nuevaVacuna.setFechaVacuna(fechaVacuna);
        nuevaVacuna.setNombreVacuna(nombreVacuna);
        nuevaVacuna.setPeso(peso);
        nuevaVacuna.setAltura(altura);
        nuevaVacuna.setEdad(edad);
        nuevaVacuna.setId_paciente(idPaciente);

        manager.agregarVacuna(nuevaVacuna, idPaciente);

        campoFechaVacuna.setText("");
        campoNombreVacuna.setText("");
        campoPeso.setText("");
        campoAltura.setText("");
        campoEdad.setText("");
        campoIdPacienteVacuna.setText("");
    }
}
