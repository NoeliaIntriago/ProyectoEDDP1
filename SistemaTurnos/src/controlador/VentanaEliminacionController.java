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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tda.Puesto;

/**
 * FXML Controller class
 *
 * @author Noelia Intriago
 */
public class VentanaEliminacionController implements Initializable {

    @FXML
    private ComboBox puesto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        puesto.getItems().addAll(VentanaPuestosController.puestosCreados);
    }    
    
    public void regresar(ActionEvent event ){
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/vista/VentanaRegistro.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();            
        } catch (IOException ex) {
            System.err.println("No se pudo crear la ventana");
        }
    }
    
    public void eliminar(ActionEvent event){
        if (puesto.getValue() == null){
            mostrarAlerta("Seleccione el puesto a eliminar", Alert.AlertType.ERROR);
        }
        Puesto p = (Puesto) puesto.getValue();
        VentanaPuestosController.puestosCreados.remove(p);
        vaciarInputPuestos();
    }
    
    public void mostrarAlerta(String mensaje, Alert.AlertType e){
        Alert alert = new Alert(e);
        alert.setTitle(" ");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public void vaciarInputPuestos(){
        puesto.setValue(null);
    }
}
