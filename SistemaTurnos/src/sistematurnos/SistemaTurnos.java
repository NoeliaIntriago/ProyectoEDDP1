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
import java.util.logging.Logger;
import java.util.logging.Level;
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
    private VentanaTurnoController vt; 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VentanaMedicoController.deserializar();
        VentanaPacienteController.deserializar();
        VentanaPuestosController.deserializar();
        Application.launch(args);
        VentanaMedicoController.serializar();
        VentanaPacienteController.serializar();
        VentanaPuestosController.serializar();
    }

    @Override
    public void start(Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VentanaTurno.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(SistemaTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
