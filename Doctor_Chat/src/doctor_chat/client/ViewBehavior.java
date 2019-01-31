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
public interface ViewBehavior {
    
    public abstract void showError(String err);
    public abstract void authentificationOK(AuthentificationOK mess);
    public abstract void authentificationFail(AuthentificationFail mess);
    public abstract void signUpFail(SignUpFail mess);
    public abstract void inviteToConversation(ConversationInvite mess);
    public abstract void inviteToConversation(Conversation conv);
}
