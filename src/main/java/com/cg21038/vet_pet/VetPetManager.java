package com.cg21038.vet_pet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Paciente.Paciente;
import Vacunas.Vacuna;
import Citas.Citas;
import Citas.VentanaCitas;
import Expediente.Expediente;
import Paciente.VentanaPacientes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author ucruz
 */


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.SwingUtilities;

public class VetPetManager {
    private static VetPetManager instance;

    private List<Paciente> pacientes;
    private List<Citas> citas;
    private List<Vacuna> vacunas;
    private List<Expediente> expedientes;

    private VetPetManager() {
        this.pacientes = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.vacunas = new ArrayList<>();
        this.expedientes = new ArrayList<>();
    }

    public static VetPetManager getInstance() {
        if (instance == null) {
            instance = new VetPetManager();
        }
        return instance;
    }
    
   public void cargarDatosDesdeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            VetPetManager clinicaExistente = objectMapper.readValue(new File("src/main/java/vet_pet_db.json"), VetPetManager.class);

            // Copiar datos cargados al objeto actual
            this.pacientes.addAll(clinicaExistente.getPacientes());
            this.citas.addAll(clinicaExistente.getCitas());
            this.vacunas.addAll(clinicaExistente.getVacunas());
            this.expedientes.addAll(clinicaExistente.getExpedientes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // Métodos para pacientes
    public void agregarPaciente(Paciente paciente) {
        paciente.setID(ContadorUnico.generarNumeroUnico());
        pacientes.add(paciente);
        guardarDatosEnJSON();
    }

    public void editarPaciente(int idPaciente, Paciente pacienteActualizado) {
        Optional<Paciente> pacienteEncontrado = buscarPacientePorId(idPaciente);
        pacienteEncontrado.ifPresent(paciente -> {
            paciente.setNombre(pacienteActualizado.getNombre());
            paciente.setNombrePropietario(pacienteActualizado.getNombrePropietario());
        });
        guardarDatosEnJSON();
    }

    public void eliminarPaciente(int idPaciente) {
        pacientes.removeIf(paciente -> paciente.getID() == idPaciente);
        guardarDatosEnJSON();
    }

    // Métodos para citas
    public void agregarCita(Citas cita) {
        cita.setId_cita(ContadorUnico.generarNumeroUnico());
        citas.add(cita);
        guardarDatosEnJSON();
    }

    public void agregarVacuna(Vacuna vacuna, int idPaciente) {
        vacuna.setId(ContadorUnico.generarNumeroUnico());
        vacuna.setId_paciente(idPaciente);
        vacunas.add(vacuna);
        guardarDatosEnJSON();
    }

    // Métodos para expedientes
    public void agregarExpediente(Expediente expediente) {
        expedientes.add(expediente);
        guardarDatosEnJSON();
    }

    // Métodos auxiliares para búsqueda por ID
    private Optional<Paciente> buscarPacientePorId(int idPaciente) {
        return pacientes.stream().filter(p -> p.getID() == idPaciente).findFirst();
    }

    public void mostrarListaPaciente() {
    SwingUtilities.invokeLater(() -> {
        new VentanaPacientes(pacientes);
    });
}
    public void mostrarListaCitas(){
        SwingUtilities.invokeLater(()->{
        new VentanaCitas(citas);
        });
    }

    // Método para guardar datos en JSON
    public void guardarDatosEnJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File("src/main/java/vet_pet_db.json"), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Otros métodos y funcionalidades según sea necesario

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Citas> getCitas() {
        return citas;
    }

    public void setCitas(List<Citas> citas) {
        this.citas = citas;
    }

    public List<Vacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }

    public List<Expediente> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(List<Expediente> expedientes) {
        this.expedientes = expedientes;
    }
}



