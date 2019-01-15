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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
}
