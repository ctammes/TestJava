/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class Sqlite {

    private String sqliteDir = null;
    private String sqliteDb = null;
    private Connection conn = null;
    private int queryTimeout = 30;
    private Logger log = null;

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    public Sqlite(String dir, String db) {
        this.sqliteDir = dir;
        this.sqliteDb = db;
    }

    public Sqlite(String dir, String db, Logger log) {
        this.sqliteDir = dir;
        this.sqliteDb = db;
        this.log = log;
    }

    /**
     * SQLite maakt niet bestaande database aan
     * @return
     */
    public boolean openDb() {
        try {
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            String cn = "jdbc:sqlite:" + this.sqliteDir + "/" + this.sqliteDb;
            this.conn = DriverManager.getConnection(cn);
            return true;
        } catch(SQLException e) {
            log.severe(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "+e.getMessage());
            return false;
        } catch(ClassNotFoundException e) {
            log.severe(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "+e.getMessage());
            return false;
        }
    }
    
    public void sluitDb() {
        try {
            if(this.conn != null) {
              this.conn.close();
            }
        } catch(SQLException e) {
            log.severe(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "+e.getMessage());
        }
    }

    /**
     * Execute statement
     * @param sql
     * @return resultset
     */
    public ResultSet execute(String sql) {
        try {
            Statement statement = this.conn.createStatement();
            statement.setQueryTimeout(queryTimeout);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch(OutOfMemoryError e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.out.println("Bestaat de database wel?");
            return null;
        } catch(SQLException e) {
            log.severe(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "+e.getMessage());
            return null;
        }
        
    }

    /**
     * Insert statement
     * @param sql
     * @return true/false
     */
    public boolean insert(String sql) {
        try {
            Statement statement = this.conn.createStatement();
            statement.setQueryTimeout(queryTimeout);  // set timeout to 30 sec.
            statement.executeUpdate(sql);
            return true;
        } catch(OutOfMemoryError e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.out.println("Bestaat de database wel?");
            return false;
        } catch(SQLException e) {
            log.severe(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "+e.getMessage());
            return false;
        }
        
    }


}
