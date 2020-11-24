/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistematurnos;

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
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{
            Parent p = FXMLLoader.load(getClass().getResource("/Vista/VentanaRegistro.fxml"));
            Scene sc = new Scene(p);
            stage.setScene(sc);
            stage.setTitle("Sistema de Turnos");
            stage.show();
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
}