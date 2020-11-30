/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import tda.CircularDoublyLinkedList;
import tda.Puesto;
import tda.Turno;
import tda.TurnoPuesto;
import tda.Video;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VentanaTurnoController implements Initializable {
    private VentanaRegistroController rController;
    
    @FXML
    private MediaView video;
    @FXML
    public TableView<TurnoPuesto> turnos;
    @FXML
    private TableColumn<String, TurnoPuesto> numeros;
    @FXML
    private TableColumn<Integer, TurnoPuesto> puestos;
    
    public static TurnoPuesto select;
    private static VentanaTurnoController singleInstance;
    private ObservableList<TurnoPuesto> tableList;
    public Queue<Puesto> puestosLibres;
    public Queue<Turno> turnosAsignados;
    private VentanaAtencionController pantallaAternderPaciente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        numeros.setCellValueFactory(new PropertyValueFactory("Turno"));
        puestos.setCellValueFactory(new PropertyValueFactory("Puesto"));

        initMediaPlayer(video, urlsVideos); 
    }    
    
    public VentanaTurnoController() {
        singleInstance = this;
        tableList = FXCollections.observableArrayList();
        puestosLibres = new LinkedList<>();
        turnosAsignados = new PriorityQueue<>((Turno t1, Turno t2) -> t1.getPaciente().getSintoma().getPrioridad() - t2.getPaciente().getSintoma().getPrioridad());
        iniciarRegistroView();
        iniciarPuestosVacios();
    }
    
    public TableView<TurnoPuesto> getTurnos() {
        return turnos;
    }

    public void setTurnos(TableView<TurnoPuesto> turnos) {
        this.turnos = turnos;
    }
       
    
    public VentanaTurnoController getrController() {
        return singleInstance;
    }

    public void setrController(VentanaRegistroController rController) {
        this.rController = rController;
    }

    public MediaView getVideo() {
        return video;
    }

    public void setVideo(MediaView video) {
        this.video = video;
    }

    public Queue<Turno> getTurnosAsignados() {
        return turnosAsignados;
    }

    public void setTurnos(Queue<Turno> turnosAsignados) {
        this.turnosAsignados = turnosAsignados;
    }

    public TableColumn<String, TurnoPuesto> getNumeros() {
        return numeros;
    }

    public void setNumeros(TableColumn<String, TurnoPuesto> numeros) {
        this.numeros = numeros;
    }

    public TableColumn<Integer, TurnoPuesto> getPuestos() {
        return puestos;
    }

    public void setPuestos(TableColumn<Integer, TurnoPuesto> puestos) {
        this.puestos = puestos;
    }

    public static VentanaTurnoController getSingleInstance() {
        return singleInstance;
    }

    public static void setSingleInstance(VentanaTurnoController singleInstance) {
        VentanaTurnoController.singleInstance = singleInstance;
    }

    public ObservableList<TurnoPuesto> getTableList() {
        return tableList;
    }

    public void setTableList(ObservableList<TurnoPuesto> tableList) {
        this.tableList = tableList;
    }

    public Queue<Puesto> getPuestosLibres() {
        return puestosLibres;
    }

    public void setPuestosLibres(Queue<Puesto> puestosLibres) {
        this.puestosLibres = puestosLibres;
    }

    public void setTurnosAsignados(PriorityQueue<Turno> turnosAsignados) {
        this.turnosAsignados = turnosAsignados;
    }

    public VentanaAtencionController getPantallaAternderPaciente() {
        return pantallaAternderPaciente;
    }

    public void setPantallaAternderPaciente(VentanaAtencionController pantallaAternderPaciente) {
        this.pantallaAternderPaciente = pantallaAternderPaciente;
    }

    public CircularDoublyLinkedList<String> getUrls() {
        return urls;
    }

    public void setUrls(CircularDoublyLinkedList<String> urls) {
        this.urls = urls;
    }

    public Iterator<String> getUrlsVideos() {
        return urlsVideos;
    }

    public void setUrlsVideos(Iterator<String> urlsVideos) {
        this.urlsVideos = urlsVideos;
    }

    CircularDoublyLinkedList<String> urls = Video.leerArchivo("src/resources/videos.txt");
    Iterator<String> urlsVideos = urls.iterator();
    
    private void initMediaPlayer(final MediaView mediaView, final Iterator<String> urls) {
        if (urlsVideos.hasNext()) {
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(urlsVideos.next()).toExternalForm()));
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    initMediaPlayer(mediaView, (Iterator<String>) urls);
                }
            });
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }
    
    public void iniciarRegistroView() {
        try {
            Stage anotherStage = new Stage();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/vista/VentanaRegistro.fxml"));
            Parent root1 = loader1.load();
            rController = loader1.getController();

            Scene scene1 = new Scene(root1);
            rController.setPrincipal(rController);

            anotherStage.setScene(scene1);
            anotherStage.show();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public void iniciarPuestosVacios() {
        LinkedList<Puesto> puestosExistentes = VentanaPuestosController.puestosCreados;
        puestosLibres.addAll(puestosExistentes);
    }
    
    public static VentanaTurnoController getInstance() {
        if (singleInstance == null) {
            singleInstance = new VentanaTurnoController();
        }
        return singleInstance;
    }
    
    public void asignarPuestoATurno(ActionEvent event) {
        if (puestosLibres.size() > 0 && !VentanaPacienteController.turnosCreados.isEmpty()) {
            Puesto p = puestosLibres.poll();
            Turno t = VentanaPacienteController.turnosCreados.removeFirst();
            TurnoPuesto tp = new TurnoPuesto(t,p);
            System.out.println(tp);
            tableList.add(tp);
            turnos.setItems(tableList);
        }
    }
    
    @FXML
    private void mostrarPantalla(MouseEvent event) {
        try {
            Turno turnoSelect = turnos.getSelectionModel().getSelectedItem().getTurno();
            select = turnos.getSelectionModel().getSelectedItem();
            Stage anotherStage = new Stage();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/vista/VentanaAtencion.fxml"));
            Parent root1 = loader1.load();
            pantallaAternderPaciente = loader1.getController();
            pantallaAternderPaciente.fillInData(turnoSelect.getPaciente());
            Scene scene1 = new Scene(root1);
            pantallaAternderPaciente.setPrincipal(this);
            anotherStage.setScene(scene1);
            anotherStage.show();
            turnos.getItems().remove(select);
        }catch(NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("No ha seleccionado un turno o la tabla está vacía");
            alert.setContentText("Añada pacientes para llenar la tabla o de clic en un turno existente.");
            alert.show();
        } 
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
