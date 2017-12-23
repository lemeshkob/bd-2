package LAB4;

import LAB3.ConnectorNeo4j;
import LAB3.QueryGenerator;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class GeneratorDataInDB {

    private static Random random = new Random();

    private GeneratorDataInDB(){}

    @Getter
    private static List<Book> books = new ArrayList<>();


    public static void generateBooks(){
        Book book = null;
        List<String> authors = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            authors = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3); j++){
                authors.add(DataGenerator.getAuthor());
            }
            book = new Book(
                    DataGenerator.getId(),
                    DataGenerator.getGenre(),
                    authors,
                    DataGenerator.getTitle(),
                    DataGenerator.getYear(),
                    DataGenerator.getCity(),
                    DataGenerator.getPublisher(),
                    DataGenerator.getAnnotation(),
                    DataGenerator.getPages(),
                    DataGenerator.getCover(),
                    Double.valueOf(DataGenerator.getPrise()),
                    Double.valueOf(DataGenerator.getDiscount())
            );
            books.add(book);
        }
    }

}
