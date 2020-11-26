/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

/**
 *
 * @author pc-4k
 */
public class Puesto {
    
    private int numero; 
    private String medico;
    
    
    public Puesto(int numero, String medico) {
        this.numero = numero;
        this.medico = medico;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
    
    @Override
    public String toString(){
        return "[ Puesto # "+numero+" - Médico: "+medico+" ]";
    }

    
    
    
}
