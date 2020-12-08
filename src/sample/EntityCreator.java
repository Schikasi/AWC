package sample;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Название класса поменять
public class EntityCreator {
    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.d");
    public static void main(String[] args) throws Exception {
        System.out.println("http://raspisanie.spmi.ru/api/"+RequestObject.SCHEDULE+"/");
    }

    enum RequestObject {
        PERSON("person"),
        GROUP("group"),
        AUDITORIUM("auditorium"),
        SCHEDULE("schedule");
        private String query;

        RequestObject(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }

    public static JSONArray getJsonArray(String response, RequestObject requestObject) throws ParseException, IOException {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://raspisanie.spmi.ru/api/search?term=" + response + "&type=" + requestObject);
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
        con.disconnect();
        return jsonArray;
    }

    public static JSONArray getJsonArray(RequestObject requestObject, String idRequestObject, LocalDate startDate, LocalDate endDate) throws ParseException, IOException {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://raspisanie.spmi.ru/api/"+RequestObject.SCHEDULE+"/" + requestObject + "/" + idRequestObject + "?start=" + startDate.format(DATE_FORMAT) + "&finish=" + endDate.format(DATE_FORMAT) + "&lng=1" );
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
        con.disconnect();
        return jsonArray;
    }
}
