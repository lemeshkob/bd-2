import LAB2.*;
import LAB3.GeneratorRandomDataInDB;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * collection commands
 */
enum Commands{
    exit,
    LAB2,
    LAB3
}

/**
 * class whose is interface for working with user
 */
public class ConsoleInterface {

    private static String command = new String();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        commandProcessing();
    }
    /**
     * method for getting command from keyboard
     * @param inputData
     */
   private static void getCommand(Scanner inputData){
       do{
           try{
               System.out.println("Type in command: ");
               command = inputData.next();
               break;
           }catch (Exception e){
               inputData = new Scanner(System.in);
               System.out.println("Error! The data typed in not true.");
               continue;
           }
       }while (true);
   }

    /**
     * method whose processing typed in from keyboard commands
     */
   private static void commandProcessing(){
    Commands defComands = Commands.valueOf("LAB3");
    try {
        defComands = Commands.valueOf(command);
    }catch (Exception ex){
        System.out.println("Error! The command is not valid.");
        getCommand(ConsoleInterface.scanner);
    }

    switch (defComands){
        case exit:
            return;
        case LAB2:
            ConvertCVS convert = null;
            List<BrowserLog> logs = null;

            System.out.println("Generate random data");
            GenerateLogs.generateRandomData(10);

            System.out.println("Add generated data to csv file");
            try {
                convert = new ConvertCVS("logs.csv");
                convert.toCVS(GenerateLogs.generatedData);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error data couldn't added  to csv file");
            }

            System.out.println("Read generated csv file");
            try {
                logs = convert.getElements();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error couldn't read csv file");
            }

            System.out.println("Convert to JSOn And add data to mongoDB");
            ConnectionMongo con = null;
            try {
                con = new ConnectionMongo("BD_LABS", "Logs");
                // con.addJsonToBD(logs);
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("Error couldn't add data to mongoDB");
            }

            LogsDB.getIP(con.getCollection(),"yandex.ru");
            LogsDB.getURL(con.getCollection(),60, 70);
            LogsDB.getURL(con.getCollection(), "11.22.32.22");
            MapReduce.getURL(con.getCollection());
            MapReduce.getURLNumber(con.getCollection());
            MapReduce.getURLByHours(con.getCollection(), "2017-11-12","2018-12-12");
            MapReduce.getIpDuration(con.getCollection());
            break;
        case LAB3:
            GeneratorRandomDataInDB.generateUsers(30);
            GeneratorRandomDataInDB.addUsersRelationship();
            GeneratorRandomDataInDB.generateArticles(20);
            GeneratorRandomDataInDB.generateGroups(20);
            GeneratorRandomDataInDB.addAuthorForGroup();
            GeneratorRandomDataInDB.addArticleInGroup();
            GeneratorRandomDataInDB.addArticleForUser();
            GeneratorRandomDataInDB.addUsersInGoup();
            break;
        default:
            break;
    }


   }

}
