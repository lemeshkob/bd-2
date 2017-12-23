package LAB2;


import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * its static class for working with our data base
 */
public class LogsDB {

    public static ArrayList<BrowserLog> getIP(DBCollection dbCollection,String url){
        ArrayList<BrowserLog> elements = new ArrayList<BrowserLog>();
        DBObject queryObject = new BasicDBObject();
        queryObject.put("url", url);
        DBCursor cursor = dbCollection.find(queryObject).sort(new BasicDBObject().append("ip", 1));
        while (cursor.hasNext()){
            DBObject dbObj = cursor.next();
            elements.add(BrowserLog.fromDBObject((BasicDBObject) dbObj));
        }
        return  elements;
    }

    public static ArrayList<BrowserLog> getURL(DBCollection dbCollection,Integer timeFrom, Integer timeTo){
        ArrayList<BrowserLog> elements = new ArrayList<BrowserLog>();
        DBObject queryObject = new BasicDBObject();
        DBObject fromToObject = new BasicDBObject();
        fromToObject.put("$gte", Integer.toString(timeFrom));
        fromToObject.put("$lte", Integer.toString(timeTo));
        queryObject.put("timeSpend", fromToObject);
        DBCursor cursor = dbCollection.find(queryObject).sort(new BasicDBObject().append("url", 1));
        while (cursor.hasNext()){
            DBObject dbObj = cursor.next();
            elements.add(BrowserLog.fromDBObject((BasicDBObject) dbObj));
        }
        return  elements;
    }

    public static ArrayList<BrowserLog> getURL(DBCollection dbCollection,String ip){
        ArrayList<BrowserLog> elements = new ArrayList<BrowserLog>();
        DBObject queryObject = new BasicDBObject();
        queryObject.put("ip", ip);
        DBCursor cursor = dbCollection.find(queryObject).sort(new BasicDBObject().append("url", 1));
        while (cursor.hasNext()){
            DBObject dbObj = cursor.next();
            elements.add(BrowserLog.fromDBObject((BasicDBObject) dbObj));
        }
        return  elements;
    }




}
