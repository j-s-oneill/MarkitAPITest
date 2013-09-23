/**
 * Created with IntelliJ IDEA.
 * User: stallworthpaul
 * Date: 9/19/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */

import static spark.Spark.*;
import spark.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public class MarkitTest {

    public static final String REST_ENDPOINT = "http://dev.markitondemand.com/Api/Quote";
    public static final String URI_INFO_PATH = "/Quote";
    public static final String sSymbol = "CSCO";

    public static void main(String[] args) {
        get (new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/plain");
                return "Output:\n" + getQuote(sSymbol);
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
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }

            return response.toString();

        } catch (Exception e) {
            return "Exceptional!";
        }

    }
}
