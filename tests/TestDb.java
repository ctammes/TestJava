import nl.ctammes.common.MijnLog;
import nl.ctammes.common.Sqlite;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 17-3-13
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class TestDb {
    static String dbDir = "/home/chris/IdeaProjects/java/TestJava/tests";
    static String dbDb = "test.db";
    static Sqlite sqlite = null;

    static String logDir = "/home/chris/IdeaProjects/java/TestJava/tests";
    static String logNaam = "test.log";
    static Logger log = null;

    @BeforeClass
    public static void setUp() throws Exception {
        if (log == null) {
            try {
                MijnLog mijnlog = new MijnLog(logDir, logNaam, true);
                log = mijnlog.getLog();
                log.setLevel(Level.INFO);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (sqlite == null) {
            File db = new File(dbDir + "/" + dbDb);
            db.delete();
            sqlite = new Sqlite(dbDir, dbDb, log);
            try {
                sqlite.openDb();
            } catch(Exception e) {
                log.severe(e.getMessage());
            }
        }
    }

    @Test
    public void testLog() {
        log.info("dit is een test");
    }

    @Test
    public void testCreate() {
        String sql = "CREATE TABLE files" +
                " (id integer primary key autoincrement," +
                " naam text unique," +
                " omschrijving text)";
        Assert.assertTrue("testCreate", sqlite.executeNoResult(sql));
    }

    @Test
    public void testInsert() {
        String sql = "insert into files" +
                " (naam, omschrijving) " +
                " values ('naam1', 'omschrijving1')";
        try {
            Assert.assertTrue("testInsert", sqlite.executeNoResult(sql));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSelect() {
        String sql = "select * from files";
        try {
            ResultSet rs = sqlite.execute(sql);
            Assert.assertNotNull("testSelect", rs);
            while (rs.next()) {
                System.out.println(rs.getString("naam"));
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
