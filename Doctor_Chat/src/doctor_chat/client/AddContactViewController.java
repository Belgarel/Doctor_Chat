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
    private TextField login;
    @FXML
    private Label errorLabel;

    @FXML
    /**
     * This function checks if a contact login is in the list of the user's contacts, then coordinates with the server to make sure it is.
     * It is called by an add-contact pop-up and it does not close it.
     * @param contactLogin login of the requested contact
     */
    private void send(ActionEvent event) throws ConnectionNotInitializedException {
       
        String contactLogin = login.getText();
        
        //Vérifier que quelque chose a été renvoyé.
        if (contactLogin == null || "".equals(contactLogin))
            return;
        //Vérifier que le login ne fait pas déjà partie des contacts.
        boolean found = false;
        String login = "";
        
        for (User u : ViewController.instance().getContacts())
        {
            login = u.getLogin();
            found = contactLogin.equals(login);
            System.out.println(found);
            if (found) {
                this.showError("Erreur : " + contactLogin + " fait déjà partie de vos contacts.");
                return;
            }
            try {
                Client.instance().sendMessage(new ContactRequest(ViewController.instance().getAccount(), contactLogin));
            } catch (ConnectionNotInitializedException ex) {
                Logger.getLogger(ChatViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancel(ActionEvent event) {  
          //
    }
    
    public void showError(String err) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                errorLabel.setText(err);
            }
        });
    }
}