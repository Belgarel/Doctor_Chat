/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Home
 */
public class DBConnection {
    private static DBConnection instance = null;
    private Connection co;
    
    private DBConnection() throws SQLException {
        this.co = null;
        //MysqlDataSource dataSource = new MysqlDataSource();
        
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", ConnectionSettings.getLogin());
        connectionProperties.put("password", ConnectionSettings.getPassword());
        String jbcdURL = "jdbc:oracle:thin:@" +
                    ConnectionSettings.getHost() +
                    ":" + ConnectionSettings.getPort() +
                    ":" + ConnectionSettings.getSID();
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(jbcdURL);
        this.co = ds.getConnection(ConnectionSettings.getLogin(), ConnectionSettings.getPassword());
    }
    public static DBConnection instance() {
        if (instance == null)
        {
            synchronized(DBConnection.class) {
                if (instance == null)
                    try {
                        instance = new DBConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return instance;
    }
    public Connection getConnection()   {return this.co;}
}
