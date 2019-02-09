/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.common.Conversation;
import doctor_chat.common.Message;
import doctor_chat.common.connection.AuthentificationFail;
import doctor_chat.common.connection.AuthentificationOK;
import doctor_chat.common.connection.ContactFail;
import doctor_chat.common.connection.ContactOK;
import doctor_chat.common.connection.ConversationInvite;
import doctor_chat.common.connection.MessagePosted;
import doctor_chat.common.connection.SignUpFail;
import java.util.Iterator;

/**
 *
 * @author Home
 */
public class ViewBehaviorChat implements ViewBehavior {
    protected ChatViewController controller;
    
    public ViewBehaviorChat(ChatViewController controller) {
        this.controller = controller;
    }

    @Override
    public void showError(String err) {
        controller.showError(err);
    }

    @Override
    public void authentificationOK(AuthentificationOK mess) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void authentificationFail(AuthentificationFail mess) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void signUpFail(SignUpFail mess) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inviteToConversation(ConversationInvite mess) {
        inviteToConversation(mess.getConversation());
    }

    @Override
    public void inviteToConversation(Conversation conv) {
System.out.println("starting conversation " + conv.getId());
        ViewController.instance().getConversations().add(conv);
        controller.showConversation(conv);
    }

    @Override
    public void contactOK(ContactOK mess) {
        ViewController.instance().getAccount().addContact(mess.getContactAccount().getNum());
        ViewController.instance().getContacts().add(mess.getContactAccount());
        controller.updateContacts();
    }
    @Override
    public void contactFail(ContactFail mess) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ChatViewController getController() {
        return controller;
    }

    @Override
    public void receiveMessage(MessagePosted mess) {
        Message message = mess.getMessage();
        //Finding the conversation to which the message should be added
        Conversation conv = null;
        boolean found = false;
        Iterator<Conversation> it = ViewController.instance().getConversations().iterator();
        while (!found && it.hasNext()) {
            conv = it.next();
            found = conv.getId() == message.getConversation().getId();
        }
        if (!found) {
            System.out.println("Message received from unknown conversation: " + message);
            return;
        }
        //Adding the message to the conversation.
        conv.addMessage(message);
        if (conv.equals(controller.getCurrentConversation()))
            controller.showConversation(conv);
            
    }
    
}
