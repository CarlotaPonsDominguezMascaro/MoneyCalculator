import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author carlotapons
 */
public class MoneyCalculator {

    public static void main(String[] arg) throws IOException {
        MoneyCalculator money = new MoneyCalculator();
        money.control();
        

    }


    private ExchangeRate exchageRate;
    private Money money;
    private Currency currencyTo;
    private CurrencyList currencies = new CurrencyList();

    public MoneyCalculator() {
        currencies.add(new Currency("USD","Dolar americano","$"));
        currencies.add(new Currency("EUR","Euro", "€"));
        currencies.add(new Currency("GBP","Libra esterlina", "£"));
    }

    private void control() throws IOException {
        input();
        process();
        output();
    }

    private void input() {
        System.out.print("introduzca una cantidad en dolares: ");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        System.out.print("introduzca divisa origen ");
        money = new Money(amount,currencies.get(scanner.next()));
        System.out.print("introduzca divisa destino ");
        currencyTo = currencies.get(scanner.next());

    }

    private void process() throws IOException {
        exchageRate = new ExchangeRate(money.getCurrency(),currencyTo);

    }

    private void output() {

        System.out.println(money.getAmount() + " " +money.getCurrency().getSymbol()+" equivale a " + exchageRate.getRate() * money.getAmount() + " "+ currencyTo.getSymbol() );
    }



}

