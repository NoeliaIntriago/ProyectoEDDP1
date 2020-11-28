/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistematurnos;

import controlador.VentanaMedicoController;
import controlador.VentanaPacienteController;
import controlador.VentanaPuestosController;
import controlador.VentanaTurnoController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Noelia Intriago
 */
public class SistemaTurnos extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
        System.out.println(VentanaMedicoController.doctoresRegistrados);
        System.out.println(VentanaPacienteController.pacientesRegistrados);
        System.out.println(VentanaPuestosController.puestosCreados);
        System.out.println(VentanaTurnoController.getSingleInstance().puestosLibres);
        System.out.println(VentanaTurnoController.getSingleInstance().getUrls());
        System.out.println(VentanaTurnoController.getSingleInstance().getTurnosAsignados());
        System.out.println(VentanaPacienteController.getTurnosCreados());
        System.out.println(VentanaTurnoController.getSingleInstance().turnosOriginados);
    }

    @Override
    public void start(Stage stage){
        try{
            Parent p = FXMLLoader.load(getClass().getResource("/vista/VentanaTurno.fxml"));
            Scene sc = new Scene(p);
            stage.setScene(sc);
            stage.setTitle("Sistema de Turnos");
            stage.show();
        }catch(IOException e){
            System.err.println(e);
        }
    }
}
