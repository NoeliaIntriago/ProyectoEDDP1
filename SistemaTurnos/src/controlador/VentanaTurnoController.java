/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import media.MediaView;

import tda.CircularDoublyLinkedList;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VentanaTurnoController implements Initializable {
    
    private MediaView media;
    private MediaPlayer mediaPlayer;
    private String ruta;
    CircularDoublyLinkedList<String> rutas = MediaView.leerArchivos();
    Iterator<String> videos = rutas.iterator();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        initMediaPlayer(media, videos);

    }
    
    private void initMediaPlayer(final media.MediaView mediaView, final Iterator<String> videos){
        try{
            
            String ruta = rutas.getFirst();
            if(ruta!=null){
                Media media = new Media(ruta);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    
    }
