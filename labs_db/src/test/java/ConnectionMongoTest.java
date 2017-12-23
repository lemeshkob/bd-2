import LAB2.BrowserLog;
import LAB2.ConnectionMongo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ConnectionMongoTest {
    ConnectionMongo conn = null;
    boolean isConnected = false;
    @Before
    public void init(){
        try {
            conn = new ConnectionMongo("BD_LABS", "Logs");
            isConnected = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        Assert.assertTrue(isConnected);
    }
}
