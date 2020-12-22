
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.net.URLConnection;
/**
 *
 * @author carlotapons
 */
public class MoneyCalculator {

    public static void main(String[] args) throws Exception {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.execute();
    }

    private double amount;
    private double exchangeRate;
    private Currency currencyFrom;
    private Currency currencyTo;
    private Map<String, Currency> currencies = new HashMap<>();

    public MoneyCalculator() {
        currencies.put("USD", new Currency("USD", "$"));
        currencies.put("EUR", new Currency("EUR", "€"));
        currencies.put("GBP", new Currency("GBP", "£"));
    }

    private void execute() throws Exception {
        input();
        process();
        output();
    }

    private void input() {
        System.out.print("Introduzca una cantidad: ");
        Scanner scanner = new Scanner(System.in);
        amount  = Double.parseDouble(scanner.next());

        System.out.print("Introduzca el código de la divisa origen: ");
        currencyFrom = currencies.get(scanner.next());

        System.out.print("Introduzca el código de la divisa destino: ");
        currencyTo = currencies.get(scanner.next());
    }

    private void process() throws Exception {
        exchangeRate = getExchangeRate(currencyFrom.getCode(), currencyTo.getCode());
    }

    private void output() {
        System.out.println(amount + " " + currencyFrom.getSymbol() + " son " + amount * exchangeRate + " " + currencyTo.getSymbol());
    }

    private static double getExchangeRate(String from, String to) throws Exception {
        URL url =
                new URL("https://api.exchangeratesapi.io/latest?symbols="+from +"," +to);
        System.out.println(url);
        URLConnection connection = url.openConnection();
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(connection.getInputStream()))) {
            String line = reader.readLine();

            int lineFind = line.indexOf(to)+5;
            String line1 = line.substring(lineFind, lineFind+5);
            System.out.println(line1);
            return Double.parseDouble(line1);
        }
    }
}

