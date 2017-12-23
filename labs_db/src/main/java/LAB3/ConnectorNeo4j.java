package LAB3;

import org.neo4j.driver.internal.spi.Connection;
import org.neo4j.driver.v1.*;
import org.neo4j.jdbc.bolt.BoltDriver;




public class ConnectorNeo4j {
        private String username = "neo4j";
        private String password = "1234";
        private String urlAddress = "bolt://localhost:7687";

        private Driver driver = null;
        private Session session = null;
        private StatementResult resultQuery = null;

        public ConnectorNeo4j(String username, String password){
                this.username = username;
                this.password = password;
                this.driver = GraphDatabase.driver(urlAddress, AuthTokens.basic(username,password));
                driver.session();
        }

        public ConnectorNeo4j(){
              this.driver = GraphDatabase.driver(urlAddress, AuthTokens.basic(username, password));
              this.session = driver.session();

        }

        public StatementResult getResultQuery(){
                return this.resultQuery;
        }

        public void showResult(){
                while (resultQuery.hasNext()){
                        System.out.println(resultQuery.next().get("n").asMap());
                }
        }

        public void runQuery(String query){
           resultQuery = session.run(query);
        }

        public void closeConnection(){
                this.session.close();
                this.driver.close();
        }
}
