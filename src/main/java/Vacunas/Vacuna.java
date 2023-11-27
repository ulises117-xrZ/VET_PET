/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vacunas;

import java.time.LocalDate;

/**
 *
 * @author ucruz
 */
public class Vacuna {
    private String fechaVacuna;
    private String nombreVacuna;
    private double peso;
    private double altura;
    private int edad;
    private int id_paciente;
    private int id;

    public Vacuna() {
    }

    
    
    public String getFechaVacuna() {
        return fechaVacuna;
    }

    public void setFechaVacuna(String fechaVacuna) {
        this.fechaVacuna = fechaVacuna;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
