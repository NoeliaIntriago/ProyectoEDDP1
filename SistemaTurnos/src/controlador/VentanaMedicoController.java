/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistematurnos.SistemaTurnos;
import tda.Medico;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VentanaMedicoController implements Initializable {
    public static LinkedList<Medico> doctoresRegistrados = new LinkedList<>();
    @FXML
    private Button atras;
    @FXML
    private Button guardar;
    @FXML
    private TextField nombres;
    @FXML
    private TextField apellidos;
    @FXML
    private ComboBox<String> especialidad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        especialidad.getItems().setAll((new String[]{"Medicina General", "Alergología", "Cardiología", "Angiología", "Cirugía General", "Dermatología", "Endocrinología", "Ecografía", "Hematología"}));
    }    
    
    public void crear(ActionEvent event){
        String[] lnombres = nombres.getText().split(" ");
        String[] lapellidos = apellidos.getText().split(" ");
        if(lnombres.length != 2 || lapellidos.length != 2){
            mostrarAlerta("Por favor, rellene correctamente los campos. \n2 nombres \n2 apellidos \n1 especialidad", Alert.AlertType.ERROR);
        }else{
            Medico m = new Medico(lnombres[0], lnombres[1], lapellidos[0], lapellidos[1], especialidad.getValue());
            if(!doctoresRegistrados.contains(m)){
                doctoresRegistrados.add(m);
                System.out.println(doctoresRegistrados);
            }else{
                mostrarAlerta("El doctor ya se encuentra registrado!", Alert.AlertType.ERROR);
            }
        }
        vaciarInputsMedicos();
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
    
    public void vaciarInputsMedicos() {
        nombres.setText("");
        apellidos.setText("");
        especialidad.setValue(null);
    }
    
    public static void serializar(){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/resources/medicos.dat"))){
            os.writeObject(doctoresRegistrados);
            System.out.println("Serializado con exito");
            os.close();
        }catch(IOException e){
            System.err.println(e);
        }    
    }
    
    public static void deserializar(){
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("src/recursos/medicos.dat"))){   
            doctoresRegistrados = (LinkedList<Medico>)is.readObject();
            System.out.println("Proceso de deserializacion culminado con exito");
            is.close();
        }catch(FileNotFoundException ex){
            System.out.println("No existen médicos registrados aún.");
        }catch(IOException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SistemaTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }  
}
