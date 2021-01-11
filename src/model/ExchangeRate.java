package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public class ExchangeRate {

    private Currency from;
    private Currency to;
    private double rate;

    public ExchangeRate(Currency from, Currency to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }


}
