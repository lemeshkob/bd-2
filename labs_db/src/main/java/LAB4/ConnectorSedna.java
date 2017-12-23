package LAB4;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import lombok.Setter;

import net.xqj.sedna.SednaXQDataSource;

import javax.xml.xquery.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class ConnectorSedna {

        @Getter @Setter
        private StringBuilder url = new StringBuilder("localhost");
        @Getter @Setter
        private String nameDB = "booksShop";
        @Getter @Setter
        private String user = "SYSTEM";
        @Getter @Setter
        private String password = "MANAGER";

        private XQDataSource xqs = new SednaXQDataSource();
        private XQConnection conn = null;

        public ConnectorSedna() throws Exception{
                xqs.setProperty("serverName", url.toString());
                xqs.setProperty("databaseName", nameDB);
                conn = xqs.getConnection(user, password);
                System.out.println("Connected to Sedna.");

        }




        public void generateXmlFile(List<Book> books) throws IOException {

                XmlMapper xmlMapper = new XmlMapper();
                File file = new File("books.xml");
                FileWriter writer = new FileWriter(file);
                writer.write("<?xml version=\"1.0\" ?> \n");
                writer.write("<Books> \n");
                for (Book book: books) {
                        writer.write(book.getXMLData());
                }
                writer.write("</Books> \n");
                writer.close();
        }


        public void executeQuery(String query) throws Exception{
                XQPreparedExpression xqpe = conn.prepareExpression(query);
                XQResultSequence rs = xqpe.executeQuery();
                while(rs.next()) {
                        System.out.println(rs.getItemAsString(null));
                }
        }


        public void close()throws Exception{
                conn.close();
                System.out.println("Disconnected from Sedna.");
        }





}
