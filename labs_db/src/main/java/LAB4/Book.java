package LAB4;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
public class Book {

    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String genre;
    @Getter @Setter
    private List<String> authors;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private int year;
    @Getter @Setter
    private String city;
    @Getter @Setter
    private String publisher;
    @Getter @Setter
    private String annotation;
    @Getter @Setter
    private int pages;
    @Getter @Setter
    private String cover;
    @Getter @Setter
    private double price;
    @Getter @Setter
    private double discount;



    public String getXMLData(){
        StringBuilder xmlData = new StringBuilder(String.format("<Book id = \"%s\"> \n", id));
        xmlData.append(String.format("<genre>%s</genre> \n", genre));
        for (String author: authors) {
            xmlData.append(String.format("<author>%s</author> \n", author));
        }
        xmlData.append(String.format("<title>%s</title> \n", title))
                .append(String.format("<year>%s</year> \n", year))
                .append(String.format("<city>%s</city> \n", city))
                .append(String.format("<publisher>%s</publisher> \n", publisher))
                .append(String.format("<annotation>%s</annotation> \n", annotation))
                .append(String.format("<pages>%s</pages> \n", pages))
                .append(String.format("<cover>%s</cover> \n", cover))
                .append(String.format("<price>%s</price> \n", price))
                .append(String.format("<discount>%s</discount> \n", discount))
                .append("</Book> \n");
        return xmlData.toString();
    }

}
