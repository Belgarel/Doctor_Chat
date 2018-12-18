/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import doctor_chat.common.NotFoundException;
import doctor_chat.common.AuthentificationFailedException;
import doctor_chat.common.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class UserService {
    private static UserService instance = null;
    
    public HashSet<User> findUsers(HashSet<Long> userIds) {
        HashSet<User> ret = new HashSet<User>();
        for (Long id : userIds) {
            try {
                ret.add(findUser(id));
            } catch (NotFoundException ex) {
                System.out.println("User not found: " + ex.getMessage());
            }
        }
        return ret;
    }
    
    public User findUser(Long id) throws NotFoundException {
        User ret = new User();
        Statement request = null;
        ResultSet results = null;
        String sql = "select password from drc_utilisateur where no_utilisateur = '" + id + "'";
        try {
            request = DBConnection.instance().getConnection().createStatement();
            results = request.executeQuery(sql);
            if (!results.next()) //if the login was not found
                throw new NotFoundException(results.toString());
            
            ret.setNum(Long.valueOf(results.getNString("NO_UTILISATEUR")));
            ret.setLogin(results.getNString("LOGIN"));
            ret.setMdp(results.getNString("PASSWORD"));
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
        return ret;
    }
    public User findUser(String login) throws NotFoundException {
        User ret = new User();
        Statement request = null;
        ResultSet results = null;
        String sql = "select password from drc_utilisateur where login = '" + login + "'";
        try {
            request = DBConnection.instance().getConnection().createStatement();
            results = request.executeQuery(sql);
            if (!results.next()) //if the login was not found
                throw new NotFoundException(results.toString());
            
            ret.setNum(Long.valueOf(results.getNString("NO_UTILISATEUR")));
            ret.setLogin(results.getNString("LOGIN"));
            ret.setMdp(results.getNString("PASSWORD"));
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
        return ret;
    }
    public User findUser(String login, String password) throws NotFoundException, AuthentificationFailedException {
        User ret = findUser(login);
        //check password
        if (password != null && !password.equals(ret.setPassword()))
            throw new AuthentificationFailedException();
        return ret;
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
