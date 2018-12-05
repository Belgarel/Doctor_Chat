/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import java.sql.Connection;

/**
 *
 * @author Home
 */
public class DBConnection {
    private static DBConnection instance = null;
    private Connection co;
    
    private DBConnection() {
        this.co = null;
    }
    public static DBConnection instance() {
        if (instance == null)
        {
            synchronized(DBConnection.class) {
                if (instance == null)
                    instance = new DBConnection();
            }
        }
        return instance;
    }
}
