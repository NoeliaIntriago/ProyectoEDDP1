/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Noelia Intriago
 */
public class VentanaRegistroController implements Initializable {
    @FXML
    Button medico;
    @FXML
    Button paciente;
    @FXML
    Button turno;
    @FXML
    Button asignacion;
    @FXML
    Button puesto;
    
    private VentanaRegistroController principal;
    /**
     * Initializes the controller class.
     */
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
        
    public void crearPuesto(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/vista/VentanaPuestos.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
    public void crearMedico(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/vista/VentanaMedico.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
    public void crearPaciente(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/vista/VentanaPaciente.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
    public void eliminarPuesto(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/vista/VentanaEliminacion.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();
        }catch(IOException e){
            System.err.println(e);
        }
    }

    public VentanaRegistroController getPrincipal() {
        return principal;
    }

    public void setPrincipal(VentanaRegistroController principal) {
        this.principal = principal;
    }
    
}
