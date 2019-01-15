/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.common.connection.AuthentificationFail;
import doctor_chat.common.connection.AuthentificationOK;

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
        ViewController.instance().login(); //TODO: chat()
    }

    @Override
    public void authentificationFail(AuthentificationFail mess) {
        controller.setError(mess.getMessage());
    }
    
}
