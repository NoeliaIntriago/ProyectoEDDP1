/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.io.Serializable;

/**
 *
 * @author Noelia Intriago
 */
public class Puesto implements Serializable{
    private int numero;
    private Medico medicoAsignado;
    
    public Puesto(int numero, Medico medicoAsignado){
        this.numero = numero;
        this.medicoAsignado = medicoAsignado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    @Override
    public String toString() {
        return "Puesto: "+ numero + ", Médico asignado: " + medicoAsignado;
    }
}
