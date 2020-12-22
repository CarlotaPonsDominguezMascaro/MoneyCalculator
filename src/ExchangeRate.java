import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public class ExchangeRate {

    private LocalDate date;
    private double rate;

    public ExchangeRate(Currency from, Currency to) throws IOException {

        getExchangeRate(from.getCode(),to.getCode());
    }



    public LocalDate getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    private void getExchangeRate(String from, String to) throws IOException {
        URL url = new URL("https://api.exchangeratesapi.io/latest?symbols=" + from + "," + to);
        URLConnection conection = url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conection.getInputStream()))) {
            String line = reader.readLine();
            int lineFind = line.indexOf(to) + 5;
            String line1 = line.substring(lineFind, lineFind + 5);
            rate = Double.parseDouble(line1);
        }
    }
}
