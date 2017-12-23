import LAB2.GenerateLogs;
import LAB2.LogsDB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class GenerateLogsTest {
    @Before
    public  void  init(){
        GenerateLogs.generateRandomData(11);
    }

    @Test
    public void test(){
        Assert.assertNotNull(GenerateLogs.generatedData);
    }
}
