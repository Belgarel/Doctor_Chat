/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common.connection;

import java.io.Serializable;

/**
 *
 * @author Home
 */
public class SignUpFail extends ServerMessage implements Serializable {
    
    private String message;

    public SignUpFail(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
