package Persistance.sql;

import Persistance.CurrencyListLoader;
import Persistance.ExchangeListLoader;
import model.Currency;
import model.ExchangeRate;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLLoader implements CurrencyListLoader, ExchangeListLoader {



    private Connection conect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:currencies.db";
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return conn;
    }

    @Override
    public List<Currency> currencyLoad() {
        List<Currency> currencyList = new ArrayList<>();
        String sql = "SELECT * FROM Currencies";

        try (Connection conn = conect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                currencyList.add(new Currency(rs.getString("code"), rs.getString("name"), rs.getString("symbol")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return currencyList;
    }

    @Override
    public ExchangeRate exchangeLoader(Currency from, Currency to) throws IOException {
        String sql = "SELECT * FROM Currencies WHERE code = '"+from+"'; ";
        try (Connection conn = conect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){

                switch (to.getCode()){
                    case  "CAD":
                        System.out.println(rs.getDouble("exchangeCAD"));
                        return new ExchangeRate(from,to, rs.getDouble("exchangeCAD"));
                    case "USD":
                        return new ExchangeRate(from,to, rs.getDouble("exchangeUSD"));
                    case "GBP":
                        return new ExchangeRate(from,to, rs.getDouble("exchangeGBP"));
                    case "EUR":
                        return new ExchangeRate(from,to, rs.getDouble("exchangeEUR"));

                }

            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

            return new ExchangeRate(from,to, 1);
    }
}
/*
try{
            Scanner scanner = new Scanner (new File(fileName));
            String fromCode, toCode;
            double rate;
            while(scanner.hasNext()){
                fromCode = scanner.next();
                toCode = scanner.next();
                rate = scanner.nextDouble();
                if(from.getCode().equals(fromCode)){
                    if (to.getCode().equals(toCode)){
                        return new ExchangeRate(from,to,rate);
                    }
                }
            }
        } catch (FileNotFoundException exception){
            System.out.println("ERROR FileCurrencyListLoader (File not fount)" + exception.getMessage());
        }

        return new ExchangeRate(from,to,0);
 */