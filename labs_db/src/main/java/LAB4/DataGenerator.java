package LAB4;


import java.time.LocalDate;
import java.util.Random;

public class DataGenerator {
    private DataGenerator(){}
    private static String characters = "qwertyuiopasdfghjklzcvbnmQWWERTYUIOPPLKJHGFDSAAAZXCVBNM";
    private static Random random = new Random();


    private static final String [] genres = new String[]{
            "Comedy",
            "Drama",
            "Horror fiction",
            "Literary realism",
            "Romance",
            "Satire",
            "Tragedy",
            "Tragicomedy",
            "Fantasy",
            "Mythology",
            "Adventure",
            "Classic",
            "Crime/detective",
            "Fable",
            "Fairy tale",
            "Legend"
    };

    private static final String [] cities = new String[]{
            "Alexander City",
            "Andalusia",
            "Anniston",
            "Athens",
            "Atmore",
            "Auburn",
            "Bessemer",
            "Birmingham",
            "Chickasaw",
            "Clanton",
            "Cullman",
            "Decatur",
            "Demopolis",
            "Dothan",
            "Enterprise",
            "Eufaula",
            "Florence",
            "Fort Payne",
            "Tuskegee"
    };


    private static final String[] publications = new String[]{
            "Pearson",
            "Reed Elsevier",
            "The Woodbridge Company",
            "Wolters Kluwer",
            "Bertelsmann AG",
            "Lagardère",
            "Grupo Planeta",
            "The McGrawHill",
            "Holtzbrinck",
            "Scholastic",
            "Wiley",
            "Gruppo De Agostini",
            "Shueisha",
            "Kodansha",
            "Houghton Mifflin Harcourt",
            "Shogakukan"
    };

    private static  final  String[] covers = new String[]{
            "Arrestox",
            "B-ClothVerona",
            "Pearl",
            "Linen",
            "Kennett",
            "C-1",
            "ClothLinen",
            "Buckra",
            "Cot-Linen",
            "Manhattan",
            "Luminaire"
    };


    private static final String[] authors = new String[]{
            "Chinua Achebe",
            "Peter Ackroyd",
            "Douglas Adams",
            "Chimamanda Ngozi Adichie",
            "Aaron Akinyemi",
            "Alexandra Harris",
            "Monica Ali",
            "Dante Alighieri",
            "Alistair Cooke",
            "Isabel Allende",
            "Kingsley Amis",
            "Martin Amis",
            "Maya Angelou",
            "Annie Proulx",
            "Jeffrey Archer",
            "Philip Ardagh",
            "Simon Armitage",
            "Isaac Asimov",
            "Åsne Seierstad",
            "Diana Athill",
            "Kate Atkinson",
            "Margaret Atwood",
            "WH Auden",
            "Jane Austen",
            "Paul Auster"
    };

    public static String getAuthor(){
        return authors[random.nextInt(authors.length-1)];
    }

    public static String getGenre(){
        return genres[random.nextInt(genres.length-1)];
    }

    public static Integer getId(){
        return random.nextInt();
    }

    public static String getTitle(){
        return "Book-" + getRandomString(3);
    }

    public static Integer getYear(){
        int year = LocalDate.now().getYear();
        int min = 1700;
        return random.nextInt((year - min) + 1) +  min;
    }

    public static String getCity(){
        return cities[random.nextInt(cities.length-1)];
    }

    public static String getPublisher(){
        return publications[random.nextInt(publications.length-1)];
    }

    public static String getAnnotation(){
        return getRandomString(100);
    }

    public static Integer getPages(){
        return random.nextInt(2000);
    }

    public static String getCover(){
        return covers[random.nextInt(covers.length-1)];
    }

    public static Integer getPrise(){
        return random.nextInt(200);
    }

    public static Integer getDiscount(){
        return random.nextInt(20);
    }



    private static String getRandomString(int size){
        char [] text = new char[size];
        for (int i = 0; i < size; i++){
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return String.copyValueOf(text);
    }

}
