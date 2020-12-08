package sample;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class EntityCreator {
    public static void main(String[] args) throws Exception {
        getJsonArray("Дорофеев",RequestObject.PERSONE);
        getJsonArray("апмм",RequestObject.GROUP);
        getJsonArray("3402",RequestObject.AUDITORIUM);
    }

    enum RequestObject {
        PERSONE("&type=person"),
        GROUP("&type=group"),
        AUDITORIUM("&type=auditorium");
        private String query;

        RequestObject(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }

    public static Person getJsonArray(String response,RequestObject requestObject) throws ParseException, IOException {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://raspisanie.spmi.ru/api/search?term=" + response + requestObject);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(new InputStreamReader(con.getInputStream()));
        for (Object person : jsonArray) {
            System.out.println(new Person((JSONObject) person).toString());
        }
        con.disconnect();
        return null;
    }

}
