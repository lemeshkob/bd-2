package LAB2;



import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *  class whose describe  our log data
 */
public class BrowserLog {


    private  int id;

    private String urlResource;

    private String ipAddress;

    private Date timeStamp;

    private String timeSpend;

    public BrowserLog(int id, String urlResource, String ipAddress, Date timeStamp, String timeSpend) {
        this.id = id;
        this.urlResource = urlResource;
        this.ipAddress = ipAddress;
        this.timeStamp = timeStamp;
        this.timeSpend = timeSpend;
    }

    public BrowserLog(String urlResource, String ipAddress, Date timeStamp, String timeSpend) {
        this.id = (new Random()).nextInt();
        this.urlResource = urlResource;
        this.ipAddress = ipAddress;
        this.timeStamp = timeStamp;
        this.timeSpend = timeSpend;
    }

    public int getId() {
        return id;
    }

    public String getUrlResource() {
        return urlResource;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getTimeSpend() {
        return timeSpend;
    }


    /**
     * method whose converting our BrowserLog object to DBObject
     * @return return the BasicDBObject
     */
    public BasicDBObject toDBObject(){
        BasicDBObject DBlog = new BasicDBObject();
        DBlog.put("url", this.urlResource);
        DBlog.put("ip", this.urlResource);
        DBlog.put("timeStamp", this.urlResource);
        DBlog.put("timeSpend", this.timeSpend);
        return DBlog;
    }

    /**
     * method whose converting to BaseLog our object
     * @param dbObject
     * @return
     */
    public static BrowserLog fromDBObject(BasicDBObject dbObject){
        BrowserLog browserLog = new BrowserLog(
                (String) dbObject.get("url"),
                (String) dbObject.get("ip"),
                (Date) dbObject.get("timeStamp"),
                (String) dbObject.get("timeSpend")
        );
        return  browserLog;
    }


    @Override
    public String toString(){
        return String.format(
                "%s,%s,%s,%s",
                this.urlResource,
                this.ipAddress,
                this.timeStamp,
                this.timeSpend
        );
    }
}
