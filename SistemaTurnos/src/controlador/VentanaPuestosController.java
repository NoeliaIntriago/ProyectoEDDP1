/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.VentanaPacienteController.pacientesRegistrados;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sistematurnos.SistemaTurnos;
import tda.Puesto;


/**
 * FXML Controller class
 *
 * @author Noelia Intriago
 */
public class VentanaPuestosController implements Initializable {
    
    public static ArrayList<Puesto> puestosRegistrados = new ArrayList<>();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      public void mostrarAlerta(String mensaje, Alert.AlertType e){
        Alert alert = new Alert(e);
        alert.setTitle(" ");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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
    
    public static void serializar(){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/resources/puestos.dat"))){
            os.writeObject(puestosRegistrados);
            System.out.println("Serializado con exito");
            os.close();
        }catch(IOException e){
            System.err.println(e);
        }    
    }
    
    public static void deserializar(){
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("src/recursos/puestos.dat"))){   
            puestosRegistrados = (ArrayList<Puesto>)is.readObject();
            System.out.println("Proceso de deserializacion culminado con exito");
            is.close();
        }catch(FileNotFoundException ex){
            System.out.println("No existen puestos registrados aún.");
        }catch(IOException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SistemaTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
}
