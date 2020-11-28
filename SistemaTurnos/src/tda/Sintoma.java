/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

/**
 *
 * @author Noelia Intriago
 */
public class Sintoma implements Serializable{
    private String descripcion;
    private Integer prioridad;

    public Sintoma(String descripcion, Integer prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioriodad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
    public static LinkedList<Sintoma> leerSintomas(String nombre){
        LinkedList<Sintoma> sintomas = new LinkedList<>();
        try {
            java.util.List<String> lineas = Files.readAllLines(Paths.get("src/resources/sintomas.txt"));
            for (String linea : lineas) {
                String[] tokens = linea.split("\\|");
                Sintoma s = new Sintoma(tokens[0], Integer.parseInt(tokens[1]));
                sintomas.add(s);
            }
        } catch (IOException e) {
            System.err.println("Archivo no encontrado\n"+e);
        }
        return sintomas;
    }
}
