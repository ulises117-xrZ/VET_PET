/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expediente;

import Paciente.Paciente;
import Vacunas.Vacuna;

import java.time.LocalDate;

/**
 *
 * @author ucruz
 */
public class Expediente {

    private int id;
    private LocalDate fechaCitaPaciente;
    private String diagnostico;
    private String[] medicamentos;
    private Paciente informacionPaciente;
    private Vacuna vacuna;

    public Expediente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaCitaPaciente() {
        return fechaCitaPaciente;
    }

    public void setFechaCitaPaciente(LocalDate fechaCitaPaciente) {
        this.fechaCitaPaciente = fechaCitaPaciente;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String[] getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String[] medicamentos) {
        this.medicamentos = medicamentos;
    }

    public Paciente getInformacionPaciente() {
        return informacionPaciente;
    }

    public void setInformacionPaciente(Paciente informacionPaciente) {
        this.informacionPaciente = informacionPaciente;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }

}
