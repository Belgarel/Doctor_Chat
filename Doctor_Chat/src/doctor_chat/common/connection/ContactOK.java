/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common.connection;

import doctor_chat.common.User;
import java.io.Serializable;

/**
 *
 * @author Home
 */
public class ContactOK extends ServerMessage implements Serializable {
    
    protected User contactAccount;

    public ContactOK(User contactAccount) {
        this.contactAccount = contactAccount;
    }
    
    public User getContactAccount() {
        return contactAccount;
    }
    public void setContactAccount(User contactAccount) {
        this.contactAccount = contactAccount;
    }
}
