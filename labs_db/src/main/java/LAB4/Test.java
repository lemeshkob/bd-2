package LAB4;

public class Test {
    public static void main(String[] args) throws Exception{
        //GeneratorDataInDB.generateBooks();
        ConnectorSedna con = new ConnectorSedna();
        //con.generateXmlFile(GeneratorDataInDB.getBooks());
        //File file = new File("books.xml");
        //con.executeQuery(QueryGenerator.loadFile(file.getName(), file.getCanonicalPath()));
        con.executeQuery(QueryGenerator.getByAuthorSum());
        con.executeQuery(QueryGenerator.getByAuthor());
        con.executeQuery(QueryGenerator.getBooksByCover());
        con.executeQuery(QueryGenerator.getByYear());
        con.executeQuery(QueryGenerator.getTitle());
        con.executeQuery(QueryGenerator.getAllAuthors());
        con.executeQuery(QueryGenerator.getAllYearsPublications());
        con.executeQuery(QueryGenerator.getAllBooks());
        con.close();
    }
}
