/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Noelia Intriago
 */
public class Video {
    private String url;
    
    public Video(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Video{" + "url=" + url + '}';
    }
    
    public static CircularDoublyLinkedList<String> leerArchivo(String nombre){
        CircularDoublyLinkedList<String> videos = new CircularDoublyLinkedList<>();
        try {
            java.util.List<String> lineas = Files.readAllLines(Paths.get("src/resources/videos.txt"));
            for (String linea : lineas) {
                Video v = new Video(linea);
                videos.addLast(v.getUrl());
            }
        } catch (IOException e) {
            System.err.println("Archivo no encontrado\n"+e);
        }
        return videos;
    }
}
