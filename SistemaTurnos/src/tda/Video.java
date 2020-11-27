/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import tda.CircularDoublyLinkedList;

/**
 *
 * @author Viviana Vera
 */

public class Video{    
    
    MediaView view;    
    private CircularDoublyLinkedList<Media> listaVideos;    
    
    public Video() throws FileNotFoundException{ 
        MediaView media = this.view;
        listaVideos = cargarVideos();        
    }
   
    public CircularDoublyLinkedList<Media> cargarVideos() throws FileNotFoundException{
        CircularDoublyLinkedList<Media> listaVideos = new CircularDoublyLinkedList<>();
        try{
            Scanner sc = new Scanner(new File("videos.txt"));
            while(sc.hasNextLine()){
                String linea = sc.nextLine();                               
                // System.out.println(linea);                
                Media video = new Media(new File(linea).toURI().toString().strip());
                listaVideos.addLast(video);
            }
        }catch(FileNotFoundException e){
            System.out.println("que no encuentra el archivo dice");
        }        
        return listaVideos;
    }
    
    public MediaView getMedia(){
        return this.view;
    }
    
        
    //prueba
    /*
    public static void main(String[] args) throws FileNotFoundException {
        
    }*/
    
}
