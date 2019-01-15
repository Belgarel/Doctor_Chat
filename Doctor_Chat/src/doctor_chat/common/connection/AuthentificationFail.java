/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common.connection;

/**
 *
 * @author Home
 */
public class AuthentificationFail extends ServerMessage {
    
    private String message;

    public AuthentificationFail(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
