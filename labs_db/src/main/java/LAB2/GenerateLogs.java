package LAB2;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateLogs {


    public  static List<BrowserLog> generatedData = new ArrayList<>();

    private static Random random = new Random();
    private static String characters = "qwertyuiopasdfghjklzcvbnmQWWERTYUIOPPLKJHGFDSAAAZXCVBNM";

    private GenerateLogs(){}

    public static void generateRandomData(int number){
        BrowserLog log = null;
        for (int i = 0; i < number; i++){
        log = new BrowserLog(random.nextInt(), getUrlAdress(), getIpAdress(), new Date(), Integer.toString(random.nextInt(100)));
        generatedData.add(log);
        }
    }


    private static String getRandomString(int size){
        char [] text = new char[size];
        for (int i = 0; i < size; i++){
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return String.copyValueOf(text);
    }


    private static String getUrlAdress(){
        char [] urlEnd = new char[3];
        for (int i = 0; i < urlEnd.length; i++){
            urlEnd[i] = characters.charAt(random.nextInt(characters.length()));
        }

        return getRandomString(10) +"."+ String.copyValueOf(urlEnd);
    }

    private static String getIpAdress(){
        StringBuilder ipAdress = new StringBuilder();
        ipAdress.append(random.nextInt(255));
        ipAdress.append(".");
        ipAdress.append(random.nextInt(255));
        ipAdress.append(".");
        ipAdress.append(random.nextInt(255));
        ipAdress.append(".");
        ipAdress.append(random.nextInt(255));
        return  ipAdress.toString();
    }

}
