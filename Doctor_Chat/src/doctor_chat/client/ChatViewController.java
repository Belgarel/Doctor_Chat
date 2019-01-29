/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.common.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class ChatViewController implements Initializable {

    @FXML
    private TextArea areaConv;
    @FXML
    private TextArea fieldMsg;
    @FXML
    private Button btnSendMsg;
    @FXML
    private Button btnClearMsg;
     @FXML
    private ListView contactList;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Affichage de tous les contacts
        ArrayList<Button> buttons = new ArrayList<Button>();
        for (User contact : ViewController.instance().getContacts()) {
            Button button = new Button(contact.getLogin());
            button.setId("chatWith" + contact.getLogin()); //identifiant qui permet de repérer le bouton
            button.setOnAction(this::chatWith);
            buttons.add(button);
        }
        ObservableList<Button> contacts = FXCollections.observableArrayList(buttons);
        contactList.setItems(contacts);
    }    

    @FXML
    private void sendMessage(ActionEvent event) {
        System.out.println(fieldMsg.getText());
        areaConv.setText(fieldMsg.getText());
    }
    @FXML
    private void clearMessage(ActionEvent event) {
        fieldMsg.clear();
    }
    
    private void chatWith(ActionEvent ae) {
System.out.println("Chat with " + ((Button)ae.getSource()).getId().substring(8));
    }
    
}
