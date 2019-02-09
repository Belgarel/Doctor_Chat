/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.client.connection.Client;
import doctor_chat.client.connection.ConnectionNotInitializedException;
import doctor_chat.common.User;
import doctor_chat.common.connection.ContactRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Home
 */
public class AddContactViewController {
    
    @FXML
    private TextField loginField;
    @FXML
    private Label errorLabel;

    @FXML
    /**
     * This function checks if a contact login is in the list of the user's contacts, then coordinates with the server to make sure it is.
     * It is called by an add-contact pop-up and it does not close it.
     * @param contactLogin login of the requested contact
     */
    private void send(ActionEvent event) throws ConnectionNotInitializedException {
        String loginDB;
        String loginSearch = loginField.getText();
        String userName = ViewController.instance().getAccount().getLogin();
        boolean userFound = false;
        
        //Vérifier que quelque chose a été renvoyé.
        if (loginSearch == null || "".equals(loginSearch)) {
            this.showError("Erreur : le champ 'login' est vide");
            return;
        }
        else if (loginSearch.equals(userName))
        {
            // Auto ajout a la liste des contacts impossible
            this.showError("Erreur : impossible d'ajouter le contact, il s'agit de votre propre login...");
            return;
        }
        
        //Vérifier que le login ne fait pas déjà partie des contacts.
        for (User u : ViewController.instance().getContacts())
        {
            loginDB = u.getLogin();  
            userFound = loginSearch.equals(loginDB);
            if (userFound) {
                this.showError("Erreur : " + loginSearch + " fait déjà partie de vos contacts.");
                return;
            }   
        }
        
        // créér nouveau contact
        if (!userFound) {
            try {
               Client.instance().sendMessage(new ContactRequest(ViewController.instance().getAccount(), loginSearch));
            } catch (ConnectionNotInitializedException ex) {                
                Logger.getLogger(ChatViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancel(ActionEvent event) {  
          ViewController.instance().cancelAddContact();
    }
    
    public void showError(String err) {
        Platform.runLater(() -> {
            errorLabel.setText(err);
        });
    }
}