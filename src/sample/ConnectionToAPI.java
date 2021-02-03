package sample;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
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

    private JSONArray jsonArray = null;

    private URL url = null;

    private HttpURLConnection con = null;

    public ConnectionToAPI createResponse(TypeResponse typeResponse, ObjectResponse objectResponse, String response) throws UnsupportedEncodingException {
        return createResponse(typeResponse, objectResponse, response, null, null);
    }

    public ConnectionToAPI createResponse(TypeResponse typeResponse, ObjectResponse objectResponse, String response, LocalDate startDate, LocalDate endDate) throws UnsupportedEncodingException {
        if (response == null) {
            throw new IllegalArgumentException("response cannot be null");
        }
        switch (typeResponse) {
            case SEARCH:
                getRequest = "search?term=" + URLEncoder.encode(response, "UTF-8") + "&type=" + objectResponse;
                break;
            case SCHEDULE:
                if (startDate == null) {
                    throw new IllegalArgumentException("startDate is null");
                }
                if (endDate == null) {
                    throw new IllegalArgumentException("endDate is null");
                }
                if (endDate.compareTo(startDate) < 0) {
                    throw new IllegalArgumentException("period is wrong");
                }
                getRequest = ObjectResponse.SCHEDULE + "/" + objectResponse + "/" + response + "?start=" + startDate.format(DATE_FORMAT) + "&finish=" + endDate.format(DATE_FORMAT) + "&lng=1";
                break;
        }
        return this;
    }

    public ConnectionToAPI makeRequest() throws IOException {
        url = new URL(new StringBuilder().append(LINK_API).append(getRequest).toString());
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        var inputStream = con.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        try {
            this.jsonArray = (JSONArray) new JSONParser().parse(inputStreamReader);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONArray getJsonArray(){
        return jsonArray;
    }

    public static void main(String[] args) throws IOException, ParseException {
        ConnectionToAPI ctapi = new ConnectionToAPI();
        ctapi.createResponse(TypeResponse.SEARCH, ObjectResponse.PERSON, "Гурко А").makeRequest();
        var v = ctapi.getJsonArray();
    }
}
