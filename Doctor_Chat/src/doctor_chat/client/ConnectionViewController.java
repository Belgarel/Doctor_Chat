/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import java.net.URL;
import java.util.ResourceBundle;
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
    private void connection(ActionEvent event) {
        System.out.println(fieldId.getText());
    }

    @FXML
    private void inscription(ActionEvent event) {
    }
    
    public void reactTo() { //méthode où un message est reçu 
        ;
    }
    
    
}
