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
public interface ViewBehavior {
    
    public abstract void authentificationOK(AuthentificationOK mess);
    public abstract void authentificationFail(AuthentificationFail mess);
}
