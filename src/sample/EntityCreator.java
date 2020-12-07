package sample;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class EntityCreator {
    public static void main(String[] args) throws Exception {
        getPerson("Дорофеев");
    }
        public static  Person getPerson(String response) throws ParseException, IOException {
            URL url = null;
            HttpURLConnection con = null;
            try {
                url = new URL("http://raspisanie.spmi.ru/api/search?term="+response+"&type=person");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONArray jsonArray =(JSONArray) new JSONParser().parse(new InputStreamReader(con.getInputStream()));
            for (Object person:jsonArray) {
                System.out.println(new Person((JSONObject) person).toString());
            }
            return null;
        }

}
