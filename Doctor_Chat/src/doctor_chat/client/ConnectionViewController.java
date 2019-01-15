/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.client.connection.Client;
import doctor_chat.client.connection.ConnectionNotInitializedException;
import doctor_chat.common.connection.AuthentificationRequest;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class ConnectionViewController implements Initializable {

    @FXML
    private Label labelError;
    @FXML
    private TextField fieldServer;
    @FXML
    private TextField fieldId;
    @FXML
    private PasswordField fieldMdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void connection(ActionEvent event) throws ConnectionNotInitializedException {
        String[] server = fieldServer.getText().split(":");
        boolean serverError = (server.length != 2);
        int port = serverError ? 0 : Integer.valueOf(server[1]);
        if (serverError)
            setError("Le champ Server doit être de la forme : \"localhost:9043\"");
        else if ("".equals(fieldId.getText()))
            setError("Le champ Identifiant ne peut pas être vide.");
        else {
            try {
                //établissement de la connection
                Client.instance(server[0], port);
                //envoi du message de connection
                Client.instance().sendMessage(new AuthentificationRequest(
                        fieldId.getText(),
                        fieldMdp.getText()));
            } catch (IOException ex) {
                setError("La connection n'a pas pu être établie.");
            }
        }
    }

    @FXML
    private void inscription(ActionEvent event) {
    }
    
    public void reactTo() { //méthode où un message est reçu 
        ;
    }
    public void setError(String err) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                labelError.setText(err);
            }
        });
    }
    
    
}
