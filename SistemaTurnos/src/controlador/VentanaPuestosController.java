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
import java.util.ListIterator;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistematurnos.SistemaTurnos;
import tda.Medico;
import tda.Puesto;

/**
 * FXML Controller class
 *
 * @author Noelia Intriago
 */
public class VentanaPuestosController implements Initializable {
    
    public static LinkedList<Puesto> puestosCreados = new LinkedList<>();
    public LinkedList<Medico> medicosRegistrados;
    private VentanaRegistroController principal;
    private VentanaTurnoController vturno = VentanaTurnoController.getSingleInstance();
    
    @FXML
    private TextField txtnumero;
    @FXML
    private ComboBox<Medico> medico;            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        medicosRegistrados = VentanaMedicoController.doctoresRegistrados;
        medico.getItems().setAll(medicosRegistrados);
    }    
    
    @FXML
    public void crearPuesto(ActionEvent event){
        try{
            if(Character.isDigit(txtnumero.getText().charAt(0))==false){
                mostrarAlerta("Por favor, ingrese un dato numérico. No alfanumérico. No palabras.", Alert.AlertType.ERROR);            
            }
            String num= txtnumero.getText();
            int num_1= Integer.parseInt(num);
            Puesto p = new Puesto(num_1, medico.getValue());
            medico.getValue().setPuesto(p);
            vaciarInputPuestos();                       
            actualizarListaDoctores();
            guardarPuesto(p);
        }catch(Exception e){
            System.out.println("Ingrese bien pues >:c");
        }
    }  
    
    public void guardarPuesto(Puesto p) {
        vturno.getPuestosLibres().add(p);
        puestosCreados.add(p);
        txtnumero.setText("");
        medico.setValue(null);
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
    
    public void vaciarInputPuestos(){
        txtnumero.setText("");
        medico.setValue(null);
        medico.hide();
    }
    
    public static void serializar(){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/resources/puestos.dat"))){
            os.writeObject(puestosCreados);
            System.out.println("Serializado con exito");
            os.close();
        }catch(IOException e){
            System.err.println(e);
        }    
    }
    
    public static void deserializar(){
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("src/recursos/puestos.dat"))){   
            puestosCreados = (LinkedList<Puesto>)is.readObject();
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
    
    public void mostrarAlerta(String mensaje, Alert.AlertType e){
        Alert alert = new Alert(e);
        alert.setTitle(" ");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    
    private void actualizarListaDoctores() {
        ListIterator<Medico> lit = medicosRegistrados.listIterator();
        while (lit.hasNext()) {
            if (lit.next().getPuesto() != null) {
                lit.remove();
            }
        }
        medico.getItems().setAll(medicosRegistrados);
    }
    
    
}
