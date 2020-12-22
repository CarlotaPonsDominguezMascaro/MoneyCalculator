import java.util.HashMap;
import java.util.Map;

public class CurrencyList {
    private Map<String,Currency> currencies = new HashMap();



    public void add(Currency currency){
        currencies.put(currency.getCode(),currency);
    }

    public Currency get(String isoCode){
        return currencies.get(isoCode.toUpperCase());
    }
}
