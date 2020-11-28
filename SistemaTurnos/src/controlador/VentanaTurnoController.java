/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.VentanaPacienteController.turnosCreados;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<Turno> turnos;
    @FXML
    private TableColumn<String, Turno> colTurno;
    @FXML
    private TableColumn<Integer, Turno> colPuesto;
    
    private static VentanaTurnoController singleInstance;
    private ObservableList<Turno> tableList;
    public Queue<Puesto> puestosLibres;
    public Queue<Turno> turnosOriginados;
    private PriorityQueue<Turno> turnosAsignados;
    private VentanaAtencionController pantallaAternderPaciente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarRegistroView();
        asignarPuestoATurno();
        //initMediaPlayer(video, urlsVideos); 
    }    

    public VentanaRegistroController getrController() {
        return rController;
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

    public TableView getTurnos() {
        return turnos;
    }

    public void setTurnos(TableView turnos) {
        this.turnos = turnos;
    }

    public TableColumn<String, Turno> getColTurno() {
        return colTurno;
    }

    public void setColTurno(TableColumn<String, Turno> colTurno) {
        this.colTurno = colTurno;
    }

    public TableColumn<Integer, Turno> getColPuesto() {
        return colPuesto;
    }

    public void setColPuesto(TableColumn<Integer, Turno> colPuesto) {
        this.colPuesto = colPuesto;
    }

    public static VentanaTurnoController getSingleInstance() {
        return singleInstance;
    }

    public static void setSingleInstance(VentanaTurnoController singleInstance) {
        VentanaTurnoController.singleInstance = singleInstance;
    }

    public ObservableList<Turno> getTableList() {
        return tableList;
    }

    public void setTableList(ObservableList<Turno> tableList) {
        this.tableList = tableList;
    }

    public Queue<Puesto> getPuestosLibres() {
        return puestosLibres;
    }

    public void setPuestosLibres(Queue<Puesto> puestosLibres) {
        this.puestosLibres = puestosLibres;
    }

    public PriorityQueue<Turno> getTurnosAsignados() {
        return turnosAsignados;
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
    
    public VentanaTurnoController() {
        singleInstance = this;
        tableList = FXCollections.observableArrayList();
        puestosLibres = VentanaPuestosController.puestosCreados;
        turnosOriginados = VentanaPacienteController.turnosCreados;
        turnosAsignados = new PriorityQueue<>((Turno t1, Turno t2) -> t1.getPaciente().getSintoma().getPrioridad() - t2.getPaciente().getSintoma().getPrioridad());
        iniciarPuestosVacios();
        
    }
    
    /*private void initMediaPlayer(final MediaView mediaView, final Iterator<String> urls) {
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

    }*/
    
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
    
    public void asignarPuestoATurno() {
        try{
           if (puestosLibres.size() > 0 && !turnosOriginados.isEmpty()) {
            tableList.add(turnosOriginados.poll());
            turnos.setItems(tableList);
            } 
        }catch(NullPointerException ex){
            System.err.println(puestosLibres.size());
            System.err.println(turnosOriginados.isEmpty());
            System.err.println(VentanaPacienteController.getTurnosCreados());
        }
        
    }
}
