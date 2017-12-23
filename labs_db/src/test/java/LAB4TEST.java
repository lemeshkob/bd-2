
import LAB3.DataGeneratorNeo4j;
import LAB3.GeneratorRandomDataInDB;
import LAB4.DataGenerator;
import LAB4.GeneratorDataInDB;
import org.junit.Assert;
import org.junit.Test;

public class LAB4TEST {

    @Test
    public void test(){
        Assert.assertNotNull(DataGenerator.getPrise());
        Assert.assertNotNull(DataGenerator.getDiscount());
        Assert.assertNotNull(DataGenerator.getYear());
        Assert.assertNotNull(DataGenerator.getPages());
        Assert.assertNotNull(DataGenerator.getGenre());
        Assert.assertNotNull(DataGenerator.getCover());
        Assert.assertNotNull(DataGenerator.getId());
        Assert.assertNotNull(DataGenerator.getAuthor());
        Assert.assertNotNull(DataGenerator.getTitle());
        Assert.assertNotNull(DataGenerator.getPages());
    }

    @Test
    public void test2(){
        GeneratorDataInDB.generateBooks();
        Assert.assertTrue(GeneratorDataInDB.getBooks() != null);

    }
}
