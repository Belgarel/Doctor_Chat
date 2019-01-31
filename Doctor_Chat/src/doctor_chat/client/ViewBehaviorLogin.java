/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.common.Conversation;
import doctor_chat.common.connection.AuthentificationFail;
import doctor_chat.common.connection.AuthentificationOK;
import doctor_chat.common.connection.ConversationInvite;
import doctor_chat.common.connection.SignUpFail;

/**
 *
 * @author Home
 */
public class ViewBehaviorLogin implements ViewBehavior {
    protected ConnectionViewController controller;

    public ViewBehaviorLogin(ConnectionViewController controller) {
        this.controller = controller;
    }

    @Override
    public void authentificationOK(AuthentificationOK mess) {
        ViewController.instance().setAccount(mess.getAccount());
        ViewController.instance().setContacts(mess.getContacts());
        ViewController.instance().setConversations(mess.getConversation());
        ViewController.instance().chat();
    }

    @Override
    public void showError(String err) {
        controller.setError(err);
    }

    @Override
    public void authentificationFail(AuthentificationFail mess) {
        controller.setError(mess.getMessage());
    }

    @Override
    public void signUpFail(SignUpFail mess) {
         controller.setError(mess.getMessage());
    }

    @Override
    public void inviteToConversation(ConversationInvite mess) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void inviteToConversation(Conversation conv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
