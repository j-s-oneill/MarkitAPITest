/**
 * Created with IntelliJ IDEA.
 * Playing around with Markit On Demand API
 * User: stallworthpaul
 * Date: 9/19/13
 * Time: 11:33 AM
 */

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static spark.Spark.*;
import spark.*;

public class MarkitTest {

    public static final String REST_ENDPOINT = "http://dev.markitondemand.com/Api/Quote";
    public static final String[] mySymbols = {"CSCO", "SWHC", "GOOG", "ALDW", "XLP"};
    public static ArrayList<StockQuote> stockList = new ArrayList<StockQuote>();

    public static void main(String[] args) {
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/plain");
                for (String item : mySymbols) {
                    StockQuote q = getQuote(item);
                    //myStocks.put(item, q);
                    stockList.add(q);
                }
                StringBuilder resp = new StringBuilder();

                for (StockQuote aStockList : stockList)
                    resp.append(aStockList.DATA.Symbol).append(" ").append(aStockList.DATA.LastPrice).append("\n");

                return resp.toString();

            }
        });
    }

    public static StockQuote getQuote(String symbol) {
        String endpoint = REST_ENDPOINT + "/json?symbol=" + symbol;

        try {
            java.net.URL endpointURL = new java.net.URL(endpoint);
            java.net.HttpURLConnection request = (java.net.HttpURLConnection)endpointURL.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            java.io.BufferedReader rd = new BufferedReader(new InputStreamReader((request.getInputStream())));
            StringBuilder response = new StringBuilder();
            String line;
            Gson gson = new Gson();

            while ((line = rd.readLine()) != null) {
                response.append(line);
            }

            line = response.toString();
            StockQuote quote = gson.fromJson(line, StockQuote.class);

            return (quote);

        } catch (Exception e) {
            System.out.println("Cause: " + e.getCause());
            System.out.println("Message: " + e.getMessage());
            return (new StockQuote());
        }

    }
}
