/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.util.LinkedList;

/**
 *
 * @author Viviana Vera
 */
public class Turno {
    
    private String codigo;
    private Puesto puesto;
    private Paciente paciente;
    
    public Turno(){     
        codigo = null;
        puesto = null;
        paciente = null;
    }
    
    public Turno(Puesto puesto, Paciente p){
        //this.codigo = generarCodigo;
        this.puesto = puesto;
        this.paciente = paciente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public String generarCodigo(Paciente p){
        String s = p.getSintoma();
        LinkedList<String> alfabeto = new LinkedList<>();
        
        return "";
    }
    
    
    @Override
    public String toString(){
        return codigo;
    }
    
}
