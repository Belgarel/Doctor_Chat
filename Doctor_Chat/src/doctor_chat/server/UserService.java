/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class UserService {
    private static UserService instance = null;
    
    public boolean findUser(String login, String password) throws NotFoundException, AuthentificationFailedException {
        Statement request = null;
        ResultSet results = null;
        String sql = "select password from drc_utilisateur where login = '" + login + "'";
        try {
            request = DBConnection.instance().getConnection().createStatement();
            results = request.executeQuery(sql);
            if (!results.next()) //if the login was not found
                throw new NotFoundException(results.toString());
            if (password == null || password != results.getString(1)) //if the pasword is wrong
                throw new AuthentificationFailedException();
            //return true after closing the statement and resultset (finally)
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (results != null)
                    results.close();
                if (request != null)
                    request.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    public static UserService instance() {
        if (instance == null)
        {
            synchronized(DBConnection.class) {
                if (instance == null)
                    instance = new UserService();
            }
        }
        return instance;
    }
}
