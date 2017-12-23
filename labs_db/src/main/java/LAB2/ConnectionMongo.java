package LAB2;

import com.mongodb.*;
import com.mongodb.util.JSON;
import jdk.nashorn.internal.objects.annotations.Setter;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.Getter;

import java.util.List;

/**
 * class who connecting to MongoDB
 */
public class ConnectionMongo {

    @Getter
    private String databaseName = null;
    @Getter
    private String url = "localhost"; // i am use localhost as default means
    @Getter
    private int port  = 27017; // i am use port number 27017 as default means
    @Getter
    private MongoClient connection = null;
    @Getter
    private DB dataBase = null;

    private DBCollection collection = null;

    public DBCollection getCollection(){
        return collection;
    }

    /**
     * constructor
     * @param url - address
     * @param port - port for connecting
     * @param databaseName - name our base data
     * @throws Exception
     */

    public ConnectionMongo(String url, int port, String databaseName) throws Exception{
        this.connection = new MongoClient(url, port);
        this.dataBase = (DB)this.connection.getDatabase(databaseName);
        this.databaseName = databaseName;
        this.url = url;
        this.port = port;
    }

    /**
     * constructor for connecting to bd without typing in
     *  host and port, this constructor using default values for connect to mongo
     * @param databaseName
     * @throws Exception
     */
    public ConnectionMongo(String databaseName, String collection) throws  Exception{
        this.connection = new MongoClient();
        this.dataBase = this.connection.getDB(databaseName);
        this.databaseName = databaseName;
        setCollection(collection);
    }

    /**
     * method for getting collection
     * @param collectionName - name our collection
     */
    public void setCollection(String collectionName){
        this.collection = dataBase.getCollection(collectionName);
    }


    /**
     * method for adding our json data do db
     * @param jsonData - our json data
     * @throws Exception
     */
    public void addJsonToBD(List<BrowserLog> logs) throws Exception{
        String jsonData = null;
        DBObject dbObject = null;
        for (BrowserLog log: logs) {
            jsonData = ConvertJSON.convertToJson(log);
            dbObject = (DBObject) JSON.parse(jsonData);
            this.collection.insert(dbObject);
        }
        System.out.println("Data added to mongodb  successfully !!!");
    }


    /**
     * method for getting all data from data base
     * @return
     * @throws Exception
     */
    public StringBuilder getAllData() throws Exception{
        DBCursor cursor = collection.find();
        StringBuilder outputString = null;
        while (cursor.hasNext()){
            outputString.append(cursor.next());
        }
        return  outputString;
    }



}
