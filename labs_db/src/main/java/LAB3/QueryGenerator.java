package LAB3;

public class QueryGenerator {
    private QueryGenerator() {}


    public static String getGenerateUser(){
        String formatStr = "Create ( p:Person{firstName:\"%s\", secondName: \"%s\", lastName:\"%s\", name: \"%s\", age : %s, birthday: \"%s\"})";
        String query = String.format(
                formatStr,
                DataGeneratorNeo4j.getFirstName(),
                DataGeneratorNeo4j.getSecondName(),
                DataGeneratorNeo4j.getLastName(),
                DataGeneratorNeo4j.getUsername(),
                DataGeneratorNeo4j.getAge(),
                DataGeneratorNeo4j.getBirthday());
        return query;
    }

    public static String getGeneratedArticle(){
        String formatStr = "Create( a:Article{name : \"%s\", fullText : \"%s\", image : \"%s\", datePublish: \"%s\"})";
        String query = String.format(
                formatStr,
                DataGeneratorNeo4j.getArticleTitle(),
                DataGeneratorNeo4j.getArticleText(),
                DataGeneratorNeo4j.getUrlAdress(),
                DataGeneratorNeo4j.getNowDate()
                );
        return query;
    }

    public static String getCeneratedGroup(){
        String formatStr = "Create( g:Groups { name: \"%s\", description : \"%s\", datePublish: \"%s\"})";
        String query = String.format(
                formatStr,
                DataGeneratorNeo4j.getGroupName(),
                DataGeneratorNeo4j.getGroupDescription(),
                DataGeneratorNeo4j.getNowDate());
        return query;
    }

    public static String getAllPerson(){
        return "Match (n:Person) return n";
    }

    public static String getAllArticles(){
        return "Match (n:Article) return n";
    }

    public static String getAllGroups(){
        return "Match (n:Groups) return n";
    }



    public static String getRelationshipUsers(String username1, String username2){
        String formatStr = "MATCH (a:Person),(b:Person) WHERE a.name = \"%s\" AND b.name = \"%s\"  CREATE (a)-[f:Friends]->(b) , (b)-[fr:Friends]->(a)  RETURN f,fr";
        String query = String.format(
                formatStr,
                username1,
                username2
             );
        return query;
    }


    public static String addAuthorGroup(String username, String groupName){
        String formatStr = "MATCH (a:Person),(b:Groups) WHERE a.name = \"%s\" AND b.name = \"%s\"  CREATE (a)-[f:Author]->(b) , (b)-[fr:Author]->(a)  RETURN f,fr";
        String query = String.format(
                formatStr,
                username,
                groupName
        );
        return query;
    }


    public static String addUserInGroup(String username, String groupName){
        String formatStr = "MATCH (a:Person),(b:Groups) WHERE a.name = \"%s\" AND b.name = \"%s\"  CREATE (a)-[f:ConsistsIn]->(b) , (b)-[fr:ConsistsIn]->(a)  RETURN f,fr";
        String query = String.format(
                formatStr,
                username,
                groupName
        );
        return query;
    }

    public static String addArticleToGroup(String nameArticle, String groupName){
        String formatStr = "MATCH (a:Article),(b:Groups) WHERE a.name = \"%s\" AND b.name = \"%s\"  CREATE (a)-[f:Create]->(b) , (b)-[fr:Create]->(a)  RETURN f,fr";
        String query = String.format(
                formatStr,
                nameArticle,
                groupName
        );
        return query;
    }

    public static String addArticleForUser(String username, String nameArticle){
        String formatStr = "MATCH (a:Article),(b:Person) WHERE a.name = \"%s\" AND b.name = \"%s\"  CREATE  (b)-[f:Create]->(a)  RETURN f";
        String query = String.format(
                formatStr,
                nameArticle,
                username
        );
        return query;
    }


    public static String getPersons(){
        return "MATCH (n:Person) RETURN n ORDER BY n.firstName";
    }

    public static String getPersonsFIOWithYears(){
        return "MATCH (n:Person) RETURN n.firstName+\" \"+n.secondName+\" \"+n.lastName+\"(\"+n.age +\")\" ORDER BY n.age";
    }

    public static String getFriendsUser(String firstName, String secondName, String lastName){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (a:Person)-[:Friends]->(b:Person)")
                .append("WHERE a.firstName = \"%s\" and a.secondName = \"%s\" and a.lastName = \"%s\" ")
                .append("RETURN b,a ORDER BY b.firstName");
        return String.format(query.toString(), firstName, secondName, lastName);
    }

    public static String getFriendFriends(String firstName, String secondName, String lastName){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (a:Person)-[:Friends]->(b:Person), (b:Person) -[:Friends]->(c:Person)")
            .append("WHERE a.firstName = \"%s\" and a.secondName = \"%s\" and a.lastName = \"%s\" ")
            .append("RETURN a,b,c  ORDER BY b.firstName");
        return String.format(query.toString(), firstName, secondName, lastName);
    }

    public static String getNumberFriendPerson(){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (a:Person)-[f:Friends]->(b:Person), (b:Person)-[:Friends]->(c:Person)")
            .append(" RETURN a.firstName+\" \"+a.secondName+\" \"+a.lastName + \"Friends number: \" + count(f)/2 ");
        return query.toString();
    }

    public static String getGroups(){
        return "Match (a:Groups) return a.name ORDER BY a.name";
    }

    public static String getGroupsPerson(String firstName, String secondName, String lastName){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (a:Person)-[:ConsistsIn]->(b:Groups)")
                .append("WHERE a.firstName = \"%s\" and a.secondName = \"%s\" and a.lastName = \"%s\" ")
                .append("RETURN a,b  ORDER BY b.name");
        return String.format(query.toString(), firstName, secondName, lastName);
    }

    public static String getNumberGroupSubscribers(){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (a:Person)-[c:ConsistsIn]->(b:Groups)")
                .append("RETURN b.name, count(c) as subsc")
                .append(" ORDER BY subsc");
        return query.toString();
    }

    public static String getNumberGroupPerson(){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (a:Person)-[c:ConsistsIn]->(b:Groups)")
            .append("RETURN a.firstName+\" \"+a.secondName+\" \"+a.lastName, count(c) as subsc ")
            .append("ORDER BY subsc");
        return query.toString();
    }

    public static String getNumberGroupFriendFriends(String firstName, String secondName, String lastName){
        StringBuilder query  = new StringBuilder();
        query.append("MATCH (me:Person)-->(friend:Person)-->(friend_of_friend:Person)-[c:ConsistsIn]->(:Groups)")
                .append("WHERE me.firstName = \"%s\" and me.secondName = \"%s\" and me.lastName = \"%s\" ")
                .append("RETURN count(c)/2");
        return String.format(query.toString(), firstName, secondName, lastName);
    }


    public static String getArticlesUser(String firstName, String secondName, String lastName){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (me:Person)-[:Create]->(a:Article)")
            .append("WHERE me.firstName = \"%s\" and me.secondName = \"%s\" and me.lastName = \"%s\" ")
            .append("RETURN me, a");
        return String.format(query.toString(), firstName, secondName, lastName);
    }

    public static String getMeanLengthArticle(){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (me:Person)-[:Create]->(a:Article)")
                .append("RETURN me.firstName+\" \"+me.secondName+\" \"+me.lastName, length(a.fullText)/2 as length_str")
                .append("ORDER BY length_str");
        return query.toString();
    }

    public static String getArticlesMoreThanNum(Integer num){
        StringBuilder query  = new StringBuilder();
        query.append("MATCH (a:Article)")
                .append("WHERE length(a.fullText) > %s ")
                .append("RETURN a ORDER BY a.name");
        return String.format(query.toString(), num);
    }

    public static String getNumberArticlesByPerson(){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (me:Person)-[cr:Create]->(a:Article)")
                .append("RETURN me.firstName+\" \"+me.secondName+\" \"+me.lastName,count(cr) as count_a")
                .append("ORDER BY count_a");
        return query.toString();
    }

    public static String getArticlesFriendsOfFriend(String firstName, String secondName, String lastName){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (me:Person)-[:Friends]->(friend:Person)-[:Friends]->(friend_of_friend:Person)-[c:Create]->(a:Article)")
            .append("WHERE me.firstName = \"%s\" and me.secondName = \"%s\" and me.lastName = \"%s\" ")
            .append("RETURN me,friend, friend_of_friend, a");
        return String.format(query.toString(), firstName, secondName, lastName);
    }

    public static String deleteAllData(){
        return "MATCH (n) DETACH DELETE n";
    }
}
