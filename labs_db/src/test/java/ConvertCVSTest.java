import LAB2.BrowserLog;
import LAB2.ConvertCVS;
import LAB2.GenerateLogs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ConvertCVSTest {
    ConvertCVS convert = null;
    List<BrowserLog> logs = null;
    boolean was_converted = false;
    @Before
    public void init(){
        try {
            convert = new LAB2.ConvertCVS("logs.csv");
            convert.toCVS(GenerateLogs.generatedData);
            was_converted = true;
            logs = convert.getElements();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error data couldn't added  to csv file");
        }
    }

    @Test
    public void test(){
        Assert.assertTrue(was_converted);
        Assert.assertNotNull(logs);
    }
}
