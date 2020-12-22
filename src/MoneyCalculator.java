import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author carlotapons
 */
public class MoneyCalculator {

    public static void main(String[] arg) throws IOException {
        System.out.print("introduzca una cantidad en dolares: ");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        double exchageRate = getExchangeRate("GBP","USD");
        System.out.println(amount +" $ equivale a "+ exchageRate+" €");
    }

    private static double getExchangeRate(String from, String to) throws IOException {
        URL url = new URL("https://api.exchangeratesapi.io/latest?symbols="+from +"," +to);
        URLConnection conection = url.openConnection();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(conection.getInputStream()))) {
            String line = reader.readLine();
            int lineFind = line.indexOf(to)+5;
            String line1 = line.substring(lineFind, lineFind+5);
            return Double.parseDouble(line1);
        }
    }

}

