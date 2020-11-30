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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
import tda.Generador;
import tda.Paciente;
import tda.Sintoma;
import tda.Turno;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VentanaPacienteController implements Initializable {
    private VentanaTurnoController principal = new VentanaTurnoController();
    public static ArrayList<Paciente> pacientesRegistrados = new ArrayList<>();
    public static LinkedList<Turno> turnosCreados = new LinkedList<>();
    @FXML
    private Button atras;
    @FXML
    private Button guardar;
    @FXML
    private TextField nombres;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField edad;
    @FXML
    private ComboBox<String> genero;
    @FXML
    private ComboBox<Sintoma> sintoma;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genero.getItems().setAll((new String[]{"Femenino", "Masculino"}));
        LinkedList<Sintoma> sintomas = Sintoma.leerSintomas("/src/resources/sintomas.txt");
        sintoma.getItems().setAll(sintomas);
    }    

    public VentanaTurnoController getPrincipal() {
        return principal;
    }

    public void setPrincipal(VentanaTurnoController principal) {
        this.principal = principal;
    }
    
    public void crear(ActionEvent event){
        String[] lnombres = nombres.getText().split(" ");
        String[] lapellidos = apellidos.getText().split(" ");
        String laedad = edad.getText();        
        
        if(lnombres.length != 2 || lapellidos.length != 2 || edad.getText().isBlank()){
            mostrarAlerta("Por favor, rellene correctamente los campos. \n2 nombres\n2 apellidos \nEdad (en números)", Alert.AlertType.ERROR);
        }else if(laedad.length()>2){
            mostrarAlerta("Por favor ingrese una edad válida", Alert.AlertType.ERROR);            
        }else if(Character.isDigit(laedad.charAt(0))==false){
            mostrarAlerta("Por favor, ingrese un dato numérico. No alfanumérico. No palabras.", Alert.AlertType.ERROR);            
        }
        else{
            Paciente p = new Paciente(lnombres[0], lnombres[1], lapellidos[0], lapellidos[1], Integer.parseInt(edad.getText()), genero.getValue(), sintoma.getValue());
            if(!pacientesRegistrados.contains(p)){
                pacientesRegistrados.add(p);
                Turno t = Generador.generarTurnoConPaciente(p);
                turnosCreados.add(t);
                System.out.println("Turnos creados: "+turnosCreados);
            }else{
                mostrarAlerta("El usuario ya se encuentra registrado!", Alert.AlertType.ERROR);
            }
        }
        
        vaciarInputsPacientes();
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
    
    
    
    public void vaciarInputsPacientes() {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        sintoma.setValue(null);
        genero.setValue(null);
    }
    
    public static void serializar(){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/resources/pacientes.dat"))){
            os.writeObject(pacientesRegistrados);
            System.out.println("Serializado con exito");
            os.close();
        }catch(IOException e){
            System.err.println(e);
        }    
    }
    
    public static void deserializar(){
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("src/recursos/pacientes.dat"))){   
            pacientesRegistrados = (ArrayList<Paciente>)is.readObject();
            System.out.println("Proceso de deserializacion culminado con exito");
            is.close();
        }catch(FileNotFoundException ex){
            System.out.println("No existen pacientes registrados aún.");
        }catch(IOException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SistemaTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 

}
