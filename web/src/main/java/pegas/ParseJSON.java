package pegas;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ParseJSON {
    public Root parse(){
        JSONParser parser = new JSONParser();
        Root root = new Root();
        try(FileReader reader = new FileReader("web/src/main/resources/test1.json")){
            JSONArray rootJsonArray = (JSONArray)parser.parse(reader);
            for(Object item: rootJsonArray){
                JSONObject peopleJsonObject = (JSONObject)item;
                String value = (String)peopleJsonObject.get("value");
                String valueunrestricted_value = (String)peopleJsonObject.get("unrestricted_value");
                root.getPeoples().add(new People(value, valueunrestricted_value));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return root;
    }
}
