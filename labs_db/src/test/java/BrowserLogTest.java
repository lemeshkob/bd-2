import LAB2.BrowserLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class BrowserLogTest {

    BrowserLog browserLogTest = null;
    @Before
    public void init(){
        try {
            browserLogTest = new BrowserLog("jCCZfiAZlZ.NtX","1.1.1.1",new Date(), "3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        Assert.assertEquals(browserLogTest.getIpAddress(),"1.1.1.1");
        Assert.assertEquals(browserLogTest.getUrlResource(), "jCCZfiAZlZ.NtX");
        Assert.assertEquals(browserLogTest.getTimeSpend(), "3");
    }
}
