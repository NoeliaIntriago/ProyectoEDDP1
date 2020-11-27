/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author pc-4k
 */
public class Sintoma {
    private String nombre;
    private int prioridad;
    
    public Sintoma(){
        nombre =null;
        prioridad=Integer.MIN_VALUE;
    }
    
    public Sintoma(String nombre, int prioridad){
        this.nombre= nombre;
        this.prioridad=prioridad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }
    
    public void setPrioridad(int prioridad){
        this.prioridad = prioridad;
    }
    
    public static LinkedList<Sintoma> obtenerSintomas(String archivo) throws FileNotFoundException{
        LinkedList<Sintoma> sintomas = new LinkedList<>();
        try{
            Scanner sc = new Scanner(new File(archivo));
            while(sc.hasNextLine()){
                String linea = sc.nextLine();                
                String[] arrayLineas = linea.split("\\|");
                // System.out.println(arrayLineas[1]+"-----" + arrayLineas[0]);
                Sintoma x = new Sintoma(arrayLineas[0], Integer.parseInt(arrayLineas[1]));
                sintomas.add(x);
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return sintomas;
    }
    
    @Override
    
    public String toString(){
        return "Sintoma [" + nombre+"] | Prioridad ["+prioridad+"]";
    }
    
    // para probar no mas xd
    /*
    public static void main (String [] args) throws FileNotFoundException{
        System.out.println(obtenerSintomas("sintomas.txt"));
    
        si funciona :D
    }
    */
}
