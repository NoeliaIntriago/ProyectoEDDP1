/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import tda.Video;

import tda.CircularDoublyLinkedList;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VentanaTurnoController implements Initializable {
    private MediaView media;    
    CircularDoublyLinkedList<Media> videos = new CircularDoublyLinkedList<>();
                    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        /*
        try {
            Video v = new Video();
            v.cargarVideos();
            initializeVideoPlayer(v.cargarVideos(), v.getMedia());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaTurnoController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        

    }
    
    private void initializeVideoPlayer(CircularDoublyLinkedList<Media> videos, MediaView media){
        Iterator<String> iterator = videos.iterator();
        if(iterator.hasNext()){
            Media videoActual = videos.getFirst();
            MediaPlayer mediaPlayer = new MediaPlayer(videoActual);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setOnEndOfMedia(() -> {
                initializeVideoPlayer(videos,media);
            });             
            media.setMediaPlayer(mediaPlayer);
    }}
    
    
    }
