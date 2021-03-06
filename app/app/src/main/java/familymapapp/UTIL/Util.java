package familymapapp.Util;

import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by programmer on 11/29/17.
 */

public class Util {

    public static String convertInputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    public static Object convertJsonStringToObject(String jsonString, Class type){
        Gson gson = new Gson();
        return gson.fromJson(jsonString, type);
    }

    public static String getValueFromJson(String jsonString, String key){
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        String value = json.get(key).getAsString();
        return value;
    }

    public static String createJsonString(Pair<String, String> ... tuples){
        JsonObject json = new JsonObject();
        for(Pair<String, String> tuple : tuples){
            json.addProperty(tuple.first, tuple.second);
        }
        String jsonString = json.toString();
        return jsonString;
    }

    public static String covertObjectToJsonString(Object object){
        Gson gson = new Gson();
        String jsonString = gson.toJson(object);
        return jsonString;
    }

    public static <newType, oldType> ArrayList<newType> castArrayList(ArrayList<oldType> list){
        ArrayList<newType> newlyCastedArrayList = new ArrayList<newType>();
        for(oldType listObject : list){
            newlyCastedArrayList.add((newType)listObject);
        }
        return newlyCastedArrayList;
    }


}
