/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.common.Conversation;
import doctor_chat.common.User;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
    private ViewBehavior behavior;
    
    private User account;
    private HashSet<User> contacts;
    private HashSet<Conversation> conversations;
    
    private ViewController() {
        login();
        //chat();
    }
    
    public void login() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                            "ConnectionView.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    behavior = new ViewBehaviorLogin(loader.getController());
                } catch (IOException ex) {
                    Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
    }
    
      public void chat() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                            "ChatView.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    behavior = new ViewBehaviorChat(loader.getController());
                } catch (IOException ex) {
                    Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }}
        );
    }
    public String askContact() {
        ; //TODO. For now, mock.
        return "jules verne";
    }
    public User getAccount() {
        return account;
    }
    public void setAccount(User account) {
        this.account = account;
    }
    public HashSet<User> getContacts() {
        return contacts;
    }
    public void setContacts(HashSet<User> contacts) {
        this.contacts = contacts;
    }
    public HashSet<Conversation> getConversations() {
        return conversations;
    }
    public void setConversations(HashSet<Conversation> conversations) {
        this.conversations = conversations;
    }

    public ViewBehavior getBehavior() {
        return behavior;
    }
    public void setBehavior(ViewBehavior behavior) {
        this.behavior = behavior;
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
