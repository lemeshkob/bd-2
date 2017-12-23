package LAB2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import org.apache.commons.csv.CSVFormat;

import org.apache.commons.csv.CSVPrinter;

/**
 * this is class who convert cvs file to json
 */
public class ConvertCVS {

        private final String NEW_LINE_SEPARATOR = "\n";
        private final String [] FILE_HEADER = {"id", "url", "ip", "timeStamp", "timeSpend"};

        private String pathToFile = null;
        private BufferedReader reader = null;
        private FileReader readerFile = null;
        private String readLine = null;
        private String splitBy = ",";
        private ArrayList<BrowserLog> containerElements = new ArrayList<>();

    /**
     * it is class constructor
     * @param pathToFile - its path to our file
     * @throws IOException
     */
    public ConvertCVS(String pathToFile) throws Exception {
            this.pathToFile  = pathToFile;
        }

    /**
     * this is function for read file
     * she is using after calling constructor
     */
    private void readFile() throws IOException{
        this.readerFile = new FileReader(pathToFile);
        this.reader = new BufferedReader(this.readerFile);
        String [] arrValues = null;
        BrowserLog browserLog = null;
        int iter = 0;
        DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy", Locale.ENGLISH);
        while((readLine = reader.readLine()) != null){
                arrValues = readLine.split(this.splitBy);
                if (arrValues.length >= 4 && iter != 0) {
                    try {
                        browserLog = new BrowserLog(
                                arrValues[0],
                                arrValues[1],
                                format.parse(arrValues[2]),
                                arrValues[3]
                        );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    containerElements.add(browserLog);
                }
            arrValues = null;
            browserLog = null;
            iter++;
        }
        this.readerFile.close();
        this.reader.close();

    }

    /**
     * it is function for converting list data to cvs file
     */
    public void toCVS(List<BrowserLog> logs) throws IOException{

       CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
       FileWriter fileWriter = new FileWriter(pathToFile);
       CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat);
       csvPrinter.printRecord(FILE_HEADER);
       for (BrowserLog log : logs){
           csvPrinter.printRecord(log);
       }
        System.out.println("CSV file was created successfully !!!");
        fileWriter.flush();
        fileWriter.close();
        csvPrinter.close();
    }


    /**
     * its class who get elements from our file
     * @return
     */
    public ArrayList<BrowserLog> getElements () throws IOException{
            readFile();
            return this.containerElements;
    }
}
