package Persistance.api;

import Persistance.ExchangeListLoader;
import model.Currency;
import model.ExchangeRate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetExchangeRateApi implements ExchangeListLoader {

    @Override
    public ExchangeRate exchangeLoader(Currency from, Currency to) throws IOException {

        URL url = new URL("https://api.exchangeratesapi.io/latest?symbols=" + from + "," + to);
        URLConnection conection = url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conection.getInputStream()))) {
            String line = reader.readLine();
            int lineFind = line.indexOf(to.getCode()) + 5;
            String line1 = line.substring(lineFind, lineFind + 5);
            return new ExchangeRate( from,to,Double.parseDouble(line1));
        }
    }
}
