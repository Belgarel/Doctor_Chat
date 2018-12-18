/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common;

/**
 *
 * @author Home
 */
public class AuthentificationFailedException extends Exception {

    public AuthentificationFailedException() {
        super();
    }
    public AuthentificationFailedException(String mess) {
        super(mess);
    }
}
