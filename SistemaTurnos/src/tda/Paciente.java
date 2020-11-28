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
public class Paciente implements Serializable{
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;
    private String genero;
    private Sintoma sintoma;

    public Paciente(String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, int edad, String genero, Sintoma sintoma) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    @Override
    public String toString() {
        return "Paciente{" + "primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", edad=" + edad + ", genero=" + genero + ", sintoma=" + sintoma + '}';
    }
}
