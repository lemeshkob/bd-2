package LAB2;


import com.google.gson.Gson;

/**
 * class for converting  cvs to json
 */
public class ConvertJSON {

    /**
     * its class is static because constructor is private
     */
    private ConvertJSON(){}

    /**
     * method who serialized your data in string used google gson
     * and then return this string
     * @param log - object whose must be serilized
     * @return
     */
    public static String convertToJson(BrowserLog log){
        Gson serilize = new Gson();
        String serilizeDate = serilize.toJson(log);
        return  serilizeDate;
    }



}
