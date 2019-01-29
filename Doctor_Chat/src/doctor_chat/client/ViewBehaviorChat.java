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
public class ViewBehaviorChat implements ViewBehavior {
    protected ChatViewController controller;
    
    public ViewBehaviorChat(ChatViewController controller) {
        this.controller = controller;
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
System.out.println("starting conversation " + conv);
        //TODO : code here
    }
    
}
