/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cg21038.vet_pet;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ucruz
 */
public class ContadorUnico {


    private static final String NOMBRE_ARCHIVO_CONTADOR = "contador.txt";

    public static int generarNumeroUnico() {
        int contador = leerContadorDesdeArchivo();

        contador++;

        guardarContadorEnArchivo(contador);

        return contador;
    }

    private static int leerContadorDesdeArchivo() {
        try (FileReader fileReader = new FileReader(NOMBRE_ARCHIVO_CONTADOR)) {
            int valor = fileReader.read();
            return (valor == -1) ? 0 : valor;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void guardarContadorEnArchivo(int contador) {
        try (FileWriter fileWriter = new FileWriter(NOMBRE_ARCHIVO_CONTADOR)) {
            fileWriter.write(contador);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
