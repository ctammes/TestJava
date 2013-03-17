import junit.framework.TestCase;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 17-3-13
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class TestDb extends TestCase {
    private String dbDir = "/home/chris/IdeaProjects/java/TestJava/tests/TestDb.java";
    private String dbDb = "test.db";
    private Sqlite sqlite = null;

    private String logDir = "/home/chris/IdeaProjects/java/TestJava/tests";
    private String logNaam = "test.log";
    static Logger log = null;

    @Override
    public void setUp() throws Exception {
        try {
            MijnLog mijnlog = new MijnLog(logDir, logNaam);
            log = mijnlog.getLog();
            log.setLevel(Level.INFO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sqlite = new Sqlite(dbDir, dbDb, log);
    }

    public void testLog() {
        log.info("dit is een test");
    }
}
