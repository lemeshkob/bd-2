package LAB4;

public class QueryGenerator {
    private QueryGenerator() {}


    public static String loadFile(String fileName, String path){
        String res = "LOAD '%s' '%s'";
        return String.format(res, path, fileName);
    }

    public static String getTitle(){
        return "for $x in doc('books.xml')//Book return $x/title/text()";
    }

    public static String getAllBooksXML(){
        return "for $x in doc('books.xml')//Book return $x";
    }

    public static String getAllBooks(){
        return "for $x in doc('books.xml')//Book return <book>{$x/title/text()} |  {$x/genre/text()} | {$x/author/text()} | {$x/price/text()}</book>";
    }

    public static String getAllAuthors(){
        return "for $x in distinct-values(doc('books.xml')//Book/author) return $x";
    }

    public static String getAllYearsPublications(){
        return "let $books := (doc('books.xml')//Book) return <results>{ for $x in distinct-values($books/year) return $x} {for $y in distinct-values($books/publisher) return  $y } </results>";
    }

    public static String getByAuthor(){
        return "let $books := (doc('books.xml')//Book) return <results>{ for $x in $books where $x/author = 'Diana Athill' return <book>{$x/author[1]} {$x/title} {$x/city} {$x/publisher} {$x/year} {$x/cover} {$x/price}</book> } </results>";
    }

    public static String getByPublisher(){
        return "let $books := (doc('books.xml')//Book) return <results>{ for $x in $books where $x/publisher = 'The McGrawHill' return <book>{$x/author[1]} {$x/title} {$x/city} {$x/publisher} {$x/year} {$x/cover} {$x/price}</book> } </results>";
    }

    public static String getByGenre(){
        return "let $books := (doc('books.xml')//Book) return <results>{ for $x in $books where $x/genre = 'The McGrawHill' return <book>{$x/author[1]} {$x/title} {$x/city} {$x/publisher} {$x/year} {$x/cover} {$x/price}</book> } </results>";
    }

    public static String getByYear(){
        return "let $books := (doc('books.xml')//Book) return <results>{ for $x in $books where $x/year >1800 and $x/year < 1900 return <book>{$x/author[1]} {$x/title} {$x/city} {$x/publisher} {$x/year} {$x/cover} {$x/price}</book> } </results>";
    }

    public static String getByPrice(){
        return "let $books := (doc('books.xml')//Book) return <results>{ for $x in $books where $x/prise >80 and $x/prise < 100 return <book>{$x/author[1]} {$x/title} {$x/city} {$x/publisher} {$x/year} {$x/cover} {$x/price}</book> } </results>";
    }

    public static String getByAuthorSum(){
        return "let $books := (doc('books.xml')//Book) return <results><book><numBook>{count(for $x in $books where $x/author = 'Diana Athill' return $x)}</numBook><sum>{sum(for $x in $books where $x/author = 'Diana Athill' return $x/price)}</sum></book></results>";
    }

    public static String getBooksByCover(){
        return "let $books := (doc('books.xml')//Book) return <results> {sum(count( for $x in $books where $x/cover = 'ClothLinen' or  $x/cover = 'Buckra' return $x )) } </results>";
    }

}

