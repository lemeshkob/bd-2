import LAB2.BrowserLog;
import LAB2.ConvertJSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ConvertJSONTest {
    BrowserLog browserLogTest = null;
    String res = null;
    @Before
    public void init(){
        try {
            browserLogTest = new BrowserLog("jCCZfiAZlZ.NtX","1.1.1.1",new Date(), "3");
            res = ConvertJSON.convertToJson(browserLogTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        Assert.assertNotNull(res);
    }
}
