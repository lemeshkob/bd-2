package LAB3;

import lombok.Getter;
import org.neo4j.configuration.Internal;
import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.values.virtual.MapValue;
import org.neo4j.values.virtual.NodeValue;

import java.util.*;


public class GeneratorRandomDataInDB {

    private static Random random = new Random();

    private GeneratorRandomDataInDB(){}

    @Getter
    private static List<Map<String, Object>> persons = new ArrayList<>();
    @Getter
    private static List<Map<String, Object>> articles = new ArrayList<>();
    @Getter
    private static List<Map<String, Object>> groups = new ArrayList<>();

    public static void generateUsers(int quantityUsers){
        ConnectorNeo4j con = new ConnectorNeo4j();
        for (int i = 0; i < quantityUsers; i++){
            con.runQuery(QueryGenerator.getGenerateUser());
        }
        con.closeConnection();
    }

    private static void gettingPersonsData(){
        ConnectorNeo4j con = new ConnectorNeo4j();
        con.runQuery(QueryGenerator.getAllPerson());
        StatementResult result = con.getResultQuery();
        while (result.hasNext()){
            Record record = result.next();
            persons.add(((InternalNode)record.asMap().get("n")).asMap());
        }
        con.closeConnection();
    }


    public static void  addUsersRelationship(){
        gettingPersonsData();
        int indexUserNm2 = 0;
        ConnectorNeo4j con = new ConnectorNeo4j();
        for (int i = 0; i < persons.size(); i++){
            String username1 = persons.get(i).get("name").toString();
            do {
                indexUserNm2 = random.nextInt(persons.size());
            }while (i == indexUserNm2);
            String username2 = persons.get(indexUserNm2).get("name").toString();
            con.runQuery(QueryGenerator.getRelationshipUsers(username1, username2));
        }
        con.closeConnection();
    }


    public static void generateArticles(int quantityArticles){
        ConnectorNeo4j con = new ConnectorNeo4j();
        for (int i = 0; i < quantityArticles; i++){
            con.runQuery(QueryGenerator.getGeneratedArticle());
        }
        con.closeConnection();
    }



    public static void generateGroups(int quantity){
        ConnectorNeo4j con = new ConnectorNeo4j();
        for (int i = 0; i < quantity; i++){
            con.runQuery(QueryGenerator.getCeneratedGroup());
        }
        con.closeConnection();
    }


    private static void gettingArticles(){
        ConnectorNeo4j con = new ConnectorNeo4j();
        con.runQuery(QueryGenerator.getAllArticles());
        StatementResult result = con.getResultQuery();
        while (result.hasNext()){
            Record record = result.next();
            articles.add(((InternalNode)record.asMap().get("n")).asMap());
        }
        con.closeConnection();
    }

    private static void gettingGroups(){
        ConnectorNeo4j con = new ConnectorNeo4j();
        con.runQuery(QueryGenerator.getAllGroups());
        StatementResult result = con.getResultQuery();
        while (result.hasNext()){
            Record record = result.next();
            groups.add(((InternalNode)record.asMap().get("n")).asMap());
        }
        con.closeConnection();
    }

    public static void addAuthorForGroup(){
        ConnectorNeo4j con = new ConnectorNeo4j();
        gettingPersonsData();
        gettingGroups();
        for (int i = 0; i < groups.size(); i++){
            con.runQuery(QueryGenerator.addAuthorGroup(
                    persons.get(random.nextInt(persons.size())).get("name").toString(),
                    groups.get(i).get("name").toString()
            ));
        }
        con.closeConnection();
    }

    public static void addUsersInGoup(){
        ConnectorNeo4j con = new ConnectorNeo4j();
        gettingPersonsData();
        gettingGroups();
        int quantityUsers = random.nextInt(persons.size());
        for (int i = 0; i < groups.size(); i++){
            while (quantityUsers > 0){
                con.runQuery(QueryGenerator.addUserInGroup(
                        persons.get(random.nextInt(persons.size())).get("name").toString(),
                        groups.get(i).get("name").toString()
                        ));
                quantityUsers--;
            }
            quantityUsers = random.nextInt(persons.size());
        }
        con.closeConnection();
    }


    public static void addArticleInGroup(){
        ConnectorNeo4j con = new ConnectorNeo4j();
        gettingGroups();
        gettingArticles();
        int quantityArticles = random.nextInt(articles.size());
        for (int i = 0; i < groups.size(); i++){
            while (quantityArticles > 0){
                con.runQuery(QueryGenerator.addArticleToGroup(
                        articles.get(random.nextInt(articles.size())).get("name").toString(),
                        groups.get(i).get("name").toString()
                ));
                quantityArticles--;
            }
            quantityArticles = random.nextInt(articles.size());
        }
        con.closeConnection();
    }


    public static void addArticleForUser(){
        ConnectorNeo4j con = new ConnectorNeo4j();
        gettingPersonsData();
        gettingArticles();
        int quantityArticles = random.nextInt(articles.size());
        for (int i = 0; i < persons.size(); i++){
            while (quantityArticles > 0){
                con.runQuery(QueryGenerator.addArticleForUser(
                        persons.get(i).get("name").toString(),
                        articles.get(random.nextInt(articles.size())).get("name").toString()
                ));
                quantityArticles--;
            }
            quantityArticles = random.nextInt(articles.size());
        }
        con.closeConnection();
    }
}
