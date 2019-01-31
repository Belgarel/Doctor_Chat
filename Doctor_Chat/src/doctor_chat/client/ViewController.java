/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.client.connection.Client;
import doctor_chat.client.connection.ConnectionNotInitializedException;
import doctor_chat.common.Conversation;
import doctor_chat.common.User;
import doctor_chat.common.connection.ContactRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                            "ChatView.fxml")); //TODO: formulaire
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    
                    final Stage askContactDialog = new Stage();
                    askContactDialog.initModality(Modality.NONE);
                    askContactDialog.initOwner(stage);
                    askContactDialog.setScene(scene);
                    askContactDialog.show();
                } catch (IOException ex) {
                    Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }}
        );
        //TODO. For now, mock.
        return "jules verne";
    }
    /**
     * This function checks if a contact login is in the list of the user's contacts, then coordinates with the server to make sure it is.
     * @param contactLogin login of the requested contact
     */
    public void addContact(String contactLogin) {
        //Vérifier que quelque chose a été renvoyé.
        if (contactLogin == null || "".equals(contactLogin))
            return;
        //Vérifier que le login ne fait pas déjà partie des contacts.
        boolean found = false;
        for (User u : ViewController.instance().getContacts())
            found = contactLogin.equals(u.getLogin());
        if (found) {
            behavior.showError("Erreur : " + contactLogin + " fait déjà partie de vos contacts.");
            return;
        }
        try {
            Client.instance().sendMessage(new ContactRequest(ViewController.instance().getAccount(), contactLogin));
        } catch (ConnectionNotInitializedException ex) {
            Logger.getLogger(ChatViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
