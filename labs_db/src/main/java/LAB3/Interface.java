package LAB3;


public class Interface {
    public static void main (String[] args){


        ConnectorNeo4j con = new ConnectorNeo4j();
        con.runQuery(QueryGenerator.getPersons());
        con.showResult();
        con.runQuery(QueryGenerator.getPersonsFIOWithYears());
        while (con.getResultQuery().hasNext()){
            System.out.println(con.getResultQuery().next());
        }
        con.runQuery(QueryGenerator.getNumberFriendPerson());
        while (con.getResultQuery().hasNext()){
            System.out.println(con.getResultQuery().next());
        }
        con.runQuery(QueryGenerator.getAllGroups());
        con.showResult();
        con.runQuery(QueryGenerator.getAllArticles());
        con.showResult();
        con.runQuery(QueryGenerator.getGroups());
        while (con.getResultQuery().hasNext()){
            System.out.println(con.getResultQuery().next());
        }
        con.runQuery(QueryGenerator.getNumberGroupSubscribers());
        while (con.getResultQuery().hasNext()){
            System.out.println(con.getResultQuery().next());
        }
        con.runQuery(QueryGenerator.getNumberGroupPerson());
        while (con.getResultQuery().hasNext()){
            System.out.println(con.getResultQuery().next());
        }

        con.runQuery(QueryGenerator.getArticlesUser("Diana", "Simon", "Atwood"));
        while (con.getResultQuery().hasNext()){
            System.out.println(con.getResultQuery().next().get("me").get("name"));
            System.out.println(con.getResultQuery().next().get("a").asMap());
        }
    }
}
