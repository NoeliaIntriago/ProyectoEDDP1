/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tda.Paciente;
import tda.TurnoPuesto;

/**
 * FXML Controller class
 *
 * @author Noelia Intriago
 */
public class VentanaAtencionController implements Initializable {

    private VentanaTurnoController principal;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtSintoma;
    @FXML
    private TextField txtDiagnostico;
    @FXML
    private TextField txtReceta;
    @FXML
    private Button btnConfirmar;
    
    public static TurnoPuesto deleteElement;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       deleteElement = VentanaTurnoController.select;
    }    
    
    public VentanaTurnoController getPrincipal() {
        return principal;
    }

    public void setPrincipal(VentanaTurnoController principal) {
        this.principal = principal;
    }
    
    public void guardarDatos(ActionEvent event) {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter("receta.diagnostico"+txtApellidos.getText()+".txt", true))) {
            bw.write(txtNombres.getText() + txtApellidos.getText() + "|" + txtReceta.getText()
                    + "|" + txtDiagnostico.getText() + "\n");
            vaciarInputsAtenderPaciente();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void vaciarInputsAtenderPaciente() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtGenero.setText("");
        txtEdad.setText("");
        txtSintoma.setText("");
        txtReceta.setText("");
        txtDiagnostico.setText("");
    }
    
    public void fillInData(Paciente paciente){
            this.getTxtEdad().setText(String.valueOf(paciente.getEdad()));
            this.getTxtGenero().setText(paciente.getGenero());
            this.getTxtSintoma().setText(paciente.getSintoma().getDescripcion());
            this.getTxtNombres().setText(paciente.getPrimerNombre()+" "+paciente.getSegundoNombre());
            this.getTxtApellidos().setText(paciente.getApellidoPaterno()+" "+paciente.getApellidoMaterno());   
    }

    public TextField getTxtNombres() {
        return txtNombres;
    }

    public TextField getTxtApellidos() {
        return txtApellidos;
    }

    public TextField getTxtEdad() {
        return txtEdad;
    }

    public TextField getTxtGenero() {
        return txtGenero;
    }

    public TextField getTxtSintoma() {
        return txtSintoma;
    }

    public TextField getTxtDiagnostico() {
        return txtDiagnostico;
    }

    public TextField getTxtReceta() {
        return txtReceta;
    }

    public Button getBtnConfirmar() {
        return btnConfirmar;
    }
    
    
}
