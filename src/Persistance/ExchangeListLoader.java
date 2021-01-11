package Persistance;

import model.Currency;
import model.ExchangeRate;

import java.io.IOException;

public interface ExchangeListLoader {
    ExchangeRate exchangeLoader(Currency from, Currency to) throws IOException;
}
