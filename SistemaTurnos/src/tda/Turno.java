/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.util.Map;

/**
 *
 * @author Noelia Intriago
 */
public class Turno {
    private Paciente paciente;
    private String turno;
    private static Map<Integer,String> asignacion;

    public Turno(Paciente paciente, String turno) {
        this.paciente = paciente;
        this.turno = turno;
    }
    
    /**
     * 
     * @param prioridad int
     * @return String
     */
    public static String getLetraPrioridad(int prioridad){
        return asignacion.get(prioridad);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Turno{" + "paciente=" + paciente + ", turno=" + turno + '}';
    }
    
    
    
}
