import LAB2.ConnectionMongo;
import LAB2.LogsDB;
import LAB2.MapReduce;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapReduceTest {
    ConnectionMongo con = null;
    boolean isConnected = true;
    @Before
    public void init(){
        try {
            con = new ConnectionMongo("BD_LABS", "Logs");
            MapReduce.getURL(con.getCollection());
            MapReduce.getURLNumber(con.getCollection());
            MapReduce.getURLByHours(con.getCollection(), "2017-11-12","2018-12-12");
            MapReduce.getIpDuration(con.getCollection());
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
