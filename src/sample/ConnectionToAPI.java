package sample;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ConnectionToAPI {

    enum ObjectResponse {
        PERSON("person"),
        GROUP("group"),
        AUDITORIUM("auditorium"),
        SCHEDULE("schedule");
        private String query;

        ObjectResponse(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }

    enum TypeResponse {
        SCHEDULE("schedule"),
        SEARCH("search");
        private String query;

        TypeResponse(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }

    }

    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    final private String LINK_API = "http://raspisanie.spmi.ru/api/";

    private String getRequest;

    private InputStream inputStream;

    URL url = null;

    HttpURLConnection con = null;

    public void createResponse(TypeResponse typeResponse, ObjectResponse objectResponse, String response) {
        createResponse(typeResponse, objectResponse, response, null, null);
    }

    public void createResponse(TypeResponse typeResponse, ObjectResponse objectResponse, String response, LocalDate startDate, LocalDate endDate) {
        if (response==null){
            throw new IllegalArgumentException("response cannot be null");
        }
        switch (typeResponse) {
            case SEARCH:
                getRequest = "search?term=" + response + "&type=" + objectResponse;
                break;
            case SCHEDULE:
                if (startDate == null) {
                    throw new IllegalArgumentException("startDate is null");
                }
                if (endDate == null) {
                    throw new IllegalArgumentException("endDate is null");
                }
                if (endDate.compareTo(startDate)<0){
                    throw new IllegalArgumentException("period is wrong");
                }
                getRequest = ObjectResponse.SCHEDULE + "/" + objectResponse + "/" + response + "?start=" + startDate.format(DATE_FORMAT) + "&finish=" + endDate.format(DATE_FORMAT) + "&lng=1";
                break;
        }
    }

    public void openConnection() throws IOException {
        url = new URL(new StringBuilder().append(LINK_API).append(getRequest).toString());
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        inputStream = con.getInputStream();
    }

    public JSONArray getJsonArray() throws IOException, ParseException, IllegalStateException {
        if (con == null) {
            throw new IllegalStateException("Connection is not open");
        }
        if (inputStream == null) {
            throw new IllegalStateException("Input stream is null");
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(inputStreamReader);
        return jsonArray;
    }
}
