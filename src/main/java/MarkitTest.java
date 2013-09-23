/**
 * Created with IntelliJ IDEA.
 * User: stallworthpaul
 * Date: 9/19/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static spark.Spark.get;

public class MarkitTest {

    public static final String REST_ENDPOINT = "http://dev.markitondemand.com/Api/Quote";
    public static final String sSymbol = "CSCO";
    public static Gson gson = new Gson();

    public static void main(String[] args) {
        get (new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/plain");
                return getQuote(sSymbol);
            }
        });
    }

    public static String getQuote(String symbol) {
        String endpoint = REST_ENDPOINT + "/json?symbol=" + symbol;

        try {
            java.net.URL endpointURL = new java.net.URL(endpoint);
            java.net.HttpURLConnection request = (java.net.HttpURLConnection)endpointURL.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            java.io.BufferedReader rd = new BufferedReader(new InputStreamReader((request.getInputStream())));
            StringBuilder response = new StringBuilder();
            String line = null;

            StockQuote quote = new StockQuote();
            while ((line = rd.readLine()) != null) {
                gson.toJson(line);
                response.append(line);
            }

            quote = gson.fromJson(response.toString(), StockQuote.class);
            //now return the quote class and try to access its members
            return response.toString();

        } catch (Exception e) {
            return "Exceptional!";
        }

    }
}
