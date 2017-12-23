package LAB2;

import com.mongodb.*;

import java.util.ArrayList;

public class MapReduce {
    public static void getURL(DBCollection dbCollection){

        String map ="function () {"+
                "emit(String(this.urlResource), {timeSpend: parseInt(this.timeSpend)} );"+
                "}";

        String reduce = "function (key, values) { "+
                " total = 0; "+
                " for (var i in values) { "+
                 " total += parseInt(values[i].timeSpend); "+
                " } "+
                " return {timeSpend:total} }";


       // MapReduceCommand cmd =
        MapReduceOutput out = dbCollection.mapReduce(map, reduce,"res", null);
        DBCollection collection = out.getOutputCollection();
        DBCursor cursor = collection.find().sort(new BasicDBObject().append("value", 1));
        while (cursor.hasNext()){
            DBObject dbObj = cursor.next();
            System.out.println(dbObj.toString());
        }
        System.out.println("Done");
        collection.drop();


    }

    public static void getURLNumber(DBCollection dbCollection){

        String map ="function () {"+
                "emit(String(this.urlResource), {numVisit: 0 } );"+
                "}";

        String reduce = "function (key, values) { "+
                " total = 0; "+
                " for (var i in values) { "+
                " total += 1; "+
                " } "+
                " return {numVisit:total} }";


        // MapReduceCommand cmd =
        MapReduceOutput out = dbCollection.mapReduce(map, reduce,"res", null);
        DBCollection collection = out.getOutputCollection();
        DBCursor cursor = collection.find().sort(new BasicDBObject().append("value", 1));
        while (cursor.hasNext()){
            DBObject dbObj = cursor.next();
            System.out.println(dbObj.toString());
        }
        System.out.println("Done");
        collection.drop();

    }

    public static void getURLByHours(DBCollection dbCollection, String DateFrom, String DateTo){



        String map ="function () {"+
                "if (this.timeStamp >= ISODate(\"" + DateFrom + "\")&& this.timeStamp <= ISODate(\"" +DateTo +"\"))"+
                "{emit(this.urlResource, {count: 0 }); }"+
                "}";

        String reduce = "function(key, values) {"
                + "var count = 0; var duration = 0;"
                + "for (var i in values) {"
                + "count += 1;"
                + "}"
                + "return {count:count}; }";


        // MapReduceCommand cmd =
        MapReduceOutput out = dbCollection.mapReduce(map, reduce,"res", null);
        DBCollection collection = out.getOutputCollection();
        DBCursor cursor = collection.find().sort(new BasicDBObject().append("value", 1));
        while (cursor.hasNext()){
            DBObject dbObj = cursor.next();
            System.out.println(dbObj.toString());
        }
        System.out.println("Done");
        collection.drop();

    }

    public static void getIpDuration(DBCollection dbCollection){

        String map = "function (){ emit(this.ipAddress, {count:1, duration:parseInt(this.timeSpend)}); }";
        String reduce = "function(key, values) {"
                + "var count = 0;"
                + "for (var i in values) {"
                + "count += 1;"
                + "}"
                + "var total = 0;"
                + "for (var i in values) {"
                + "total += parseInt(values[i].timeSpend);"
                + "}"
                + "return {count:count, duration: total}; }";


        // MapReduceCommand cmd =
        MapReduceOutput out = dbCollection.mapReduce(map, reduce,"res", null);
        DBCollection collection = out.getOutputCollection();
        DBCursor cursor = collection.find().sort(new BasicDBObject().append("value", 1));
        while (cursor.hasNext()){
            DBObject dbObj = cursor.next();
            System.out.println(dbObj.toString());
        }
        System.out.println("Done");
        collection.drop();

    }


}
