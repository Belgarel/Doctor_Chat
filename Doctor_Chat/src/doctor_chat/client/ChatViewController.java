/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.client.connection.Client;
import doctor_chat.client.connection.ConnectionNotInitializedException;
import doctor_chat.common.Conversation;
import doctor_chat.common.Message;
import doctor_chat.common.User;
import doctor_chat.common.connection.ContactRequest;
import doctor_chat.common.connection.ConversationCreateRequest;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     
    private Button addContact;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateContacts();
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
    
    public void updateContacts() {
        //Affichage de tous les contacts
        ArrayList<Button> buttons = new ArrayList<Button>();
        for (User contact : ViewController.instance().getContacts()) {
            Button button = new Button(contact.getLogin());
            button.setId("chatWith" + contact.getLogin()); //identifiant qui permet de repérer le bouton
            button.setOnAction(this::chatWith);
            buttons.add(button);
        }
        if (this.addContact == null)
            this.addContact = new Button("Nouveau contact");
        this.addContact.setOnAction(this::addContact);
        buttons.add(this.addContact);
        
        ObservableList<Button> contacts = FXCollections.observableArrayList(buttons);
        contactList.setItems(contacts);
    }
    
    private void chatWith(ActionEvent ae) {
        String contactLogin = ((Button)ae.getSource()).getId().substring(8);
        System.out.println("Chat with " + contactLogin);
        
        //on vérifie que la conversation ne se trouve pas parmi les conversations existantes.
        boolean found = false;
        Iterator<Conversation> it = ViewController.instance().getConversations().iterator();
        Conversation current = null;
        while (!found && it.hasNext()) {
            current = it.next();
            found = (current.getMembers().size() == 2) //c'est une conversation entre Account et Contact
                    && (current.isMember(contactLogin));
        }
        
        if (found) //Si la conversation existe déjà chez le client, pas la peine de demander au server.
            ViewController.instance().getBehavior().inviteToConversation(current);
        else {
            System.out.println("Starting a new conversation.");
            try {
                //On demande au serveur de créer une nouvelle conversation contenant :
                Conversation newConv = new Conversation();
                newConv.addMember(ViewController.instance().getAccount()); //notre compte
                //et le compte du contact :
                Iterator<User> ite = ViewController.instance().getContacts().iterator();
                User curr = null;
                while (!found && ite.hasNext()) {
                    curr = ite.next();
                    found = curr.getLogin().equals(contactLogin);
                    if (found)
                        newConv.addMember(curr);
                }
                if (!found) //ne devrait JAMAIS arriver !
                    throw new Exception("Internal error: user " + contactLogin + " was not in the list of contacts");

                Client.instance().sendMessage(new ConversationCreateRequest(newConv));
            } catch (Exception ex) {
                Logger.getLogger(ChatViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addContact(ActionEvent ae) {
        String contactLogin = ViewController.instance().askContact();
    }
    
    public void showError(String err) {
        areaConv.clear();
        areaConv.setText(err);
    }
    
    public void showConversation (Conversation conversation) {
        areaConv.clear();
        for (Message m : conversation.getMessages()) {
            areaConv.appendText(m.getAuthor().getLogin() + " :\n" + m.getContent() + "\n\n");
        }
    }
    
}
