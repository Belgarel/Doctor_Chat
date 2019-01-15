/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Home
 */
public class ViewController {
    private static ViewController instance = null;
    private static Stage stage = null;
    
    private Object currentView = null;
    
    private ViewController() {
        //login();
        chat();
    }
    
    public void login() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ConnectionView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void chat() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ChatView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static void setStage(Stage s) { stage = s; }
    
    public static ViewController instance() {
        if (instance == null)
        {
            synchronized(ViewController.class) {
                if (instance == null)
                    instance = new ViewController();
            }
        }
        return instance;
    }
    
}
