/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client;

import doctor_chat.common.connection.ContactFail;
import doctor_chat.common.connection.ContactOK;

/**
 *
 * @author Home
 */
public class ViewBehaviorAddContact extends ViewBehaviorChat {
    
    protected AddContactViewController contactController;
    
    public ViewBehaviorAddContact(ChatViewController controller, AddContactViewController contactController) {
        super(controller);
        this.contactController = contactController;
    }

    @Override
    public void contactOK(ContactOK mess) {
        //TODO
    }
    @Override
    public void contactFail(ContactFail mess) {
        contactController.showError(mess.getMessage());
    }
    @Override
    public void showError(String err) {
        contactController.showError(err);
    }
}
