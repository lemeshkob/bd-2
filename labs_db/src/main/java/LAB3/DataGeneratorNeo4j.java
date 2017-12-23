package LAB3;


import java.time.LocalDate;
import java.util.Random;

public class DataGeneratorNeo4j {
    private DataGeneratorNeo4j(){}
    private static String characters = "qwertyuiopasdfghjklzcvbnmQWWERTYUIOPPLKJHGFDSAAAZXCVBNM";
    private static Random random = new Random();

    private static final String[] surname = new String[]{
            "Achebe",
            "Ackroyd",
            "Adams",
            "Adichie",
            "Akinyemi",
            "Harris",
            "Ali",
            "Alighieri",
            "Cooke",
            "Allende",
            "Amis",
            "Amis",
            "Angelou",
            "Proulx",
            "Archer",
            "Ardagh",
            "Armitage",
            "Asimov",
            "Seierstad",
            "Athill",
            "Atkinson",
            "Atwood",
            "Auden",
            "Austen",
            "Auster"
    };

    private static final String[] name = new String[]{
            "Martin",
            "Maya",
            "Annie",
            "Jeffrey",
            "Philip",
            "Simon",
            "Isaac",
            "Åsne",
            "Diana",
            "Kate",
            "Margaret",
            "WH",
            "Jane",
            "Paul"
    };


    public static String getUsername(){
        return "user-"+getRandomString(4);
    }

    public static String getFirstName(){
        return name[random.nextInt(name.length-1)];
    }

    public static String getSecondName(){
        return name[random.nextInt(name.length-1)];
    }

    public static String getLastName(){
        return surname[random.nextInt(surname.length-1)];
    }

    public static String getArticleTitle(){
        return "title"+getRandomString(10);
    }

    public static String getArticleText(){
        return getRandomString(100);
    }

    public static String getNowDate(){
        return String.format("%s-%s-%s", LocalDate.now().getDayOfMonth(), LocalDate.now().getMonth(), LocalDate.now().getYear());
    }

    public static String getUrlAdress(){
        char [] urlEnd = new char[3];
        for (int i = 0; i < urlEnd.length; i++){
            urlEnd[i] = characters.charAt(random.nextInt(characters.length()));
        }

        return getRandomString(10) +"."+ String.copyValueOf(urlEnd);
    }

    public static String getArticleName(){
        return String.format("article%s", random.nextInt());
    }


    public static String getMessageName(){
        return  String.format("message%s", random.nextInt());
    }

    public static String getMessageText(){
        return getRandomString(100);
    }

    public static String getGroupName(){
        return String.format("group%s", random.nextInt());
    }

    public static String getGroupDescription(){
        return getRandomString(160);
    }

    private static String getRandomString(int size){
        char [] text = new char[size];
        for (int i = 0; i < size; i++){
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return String.copyValueOf(text);
    }

    private static int birthdayYear = 0;

    public static String getAge(){
        int year = LocalDate.now().getYear();
        int min = 1990;
        int generatedYear = random.nextInt((year - min) + 1) +  min;
        birthdayYear = generatedYear;
        return String.valueOf((year - generatedYear) > 0 ? year - generatedYear : year - random.nextInt(year));
    }

    public static String getBirthday(){
        if (birthdayYear > 0){
            return  String.format("%s-%s-%s",
                    String.valueOf(random.nextInt(31)),
                    String.valueOf(random.nextInt(12)),
                    String.valueOf(birthdayYear));
        }else {
            return  String.format("%s-%s-%s",
                    String.valueOf(random.nextInt(31)),
                    String.valueOf(random.nextInt(12)),
                    String.valueOf(random.nextInt(LocalDate.now().getYear())));
        }
    }



}
