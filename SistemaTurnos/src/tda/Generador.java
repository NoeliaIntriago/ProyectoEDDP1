/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

/**
 *
 * @author Noelia Intriago
 */
public class Generador {
    
    public static Turno generarTurnoConPaciente(Paciente p){
        Sintoma s = p.getSintoma();
        int cant = 0;
        String letra = "";
        switch(s.getPrioridad()){
            case 0:
                cant = Asignador.getCantA();
                letra = "A";
                Asignador.setCantA(cant+1);
                break;
            case 1:
                cant = Asignador.getCantB();
                letra = "B";
                Asignador.setCantB(cant+1);
                break;
            case 2:
                cant = Asignador.getCantC();
                letra = "C";
                Asignador.setCantC(cant+1);
                break;
            case 3:
                cant = Asignador.getCantD();
                letra = "D";
                Asignador.setCantD(cant+1);
                break;
            case 4:
                cant = Asignador.getCantE();
                letra = "E";
                Asignador.setCantE(cant+1);
                break;
        }
        String nTurno = letra + cant;
        return new Turno(p,nTurno);
                
    }
}
