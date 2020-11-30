/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Noelia Intriago
 */
public class Puesto implements Serializable{
    private int numero;
    private Medico medicoAsignado;
    
    public Puesto(){
        numero= 0;
        medicoAsignado = null;
    }
    
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
    public LinkedList LeerArchivo(String s) {
        LinkedList<Puesto> lista = new LinkedList<>();
        try  {
            File f = new File("puestos.ser");
            if(f.exists()){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                Object nuevo = ois.readObject();
                if(nuevo!=null){
                    lista = (LinkedList<Puesto>) nuevo;
                }
                ois.close();
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Puesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
