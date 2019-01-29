/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import doctor_chat.common.NotFoundException;
import doctor_chat.common.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Home
 */
public class ContactService {
    private static ContactService instance = null;
    
    public HashSet<Long> findContactIdsFromUser(User user) {
        return findContactIdsFromUserId(user.getNum());
    }
    public HashSet<Long> findContactIdsFromUserId(long userId) {
        HashSet<Long> ret =  new HashSet<Long>();
        Statement request = null;
        ResultSet results = null;
        String sql = "select * from drc_contact where no_utilisateur = " + userId;
        try {
            request = DBConnection.instance().getConnection().createStatement();
            results = request.executeQuery(sql);
            
            while (results.next()) {
                ret.add(Long.valueOf(results.getNString("NO_CONTACT")));
            }
            
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
    
    public void createContact(long user, long contact) {
        throw new NotImplementedException();
    }
    public void deleteContact(long user, long contact) {
        throw new NotImplementedException();
    }
    
    
    private ContactService() {}
    public static ContactService instance() {
        if (instance == null)
        {
            synchronized(ContactService.class) {
                if (instance == null)
                    instance = new ContactService();
            }
        }
        return instance;
    }
}
