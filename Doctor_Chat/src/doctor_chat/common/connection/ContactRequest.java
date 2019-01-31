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
public class ContactRequest extends ClientMessage implements Serializable {
    private User account;
    private String contactLogin;

    public ContactRequest(User account, String contactLogin) {
        this.account = account;
        this.contactLogin = contactLogin;
    }

    public User getAccount() {
        return account;
    }
    public void setAccount(User account) {
        this.account = account;
    }
    public String getContactLogin() {
        return contactLogin;
    }
    public void setContactLogin(String contactLogin) {
        this.contactLogin = contactLogin;
    }
}
