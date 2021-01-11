package Persistance.file;

import Persistance.CurrencyListLoader;
import model.Currency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileCurrencyLoader implements CurrencyListLoader {

    private String name;


    public FileCurrencyLoader(String name) {
        this.name = name;
    }



    @Override
    public List<Currency> currencyLoad() {
        List<Currency> currencyList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(name)))){

            while (reader != null){
                String line = reader.readLine();
                String[] split = line.split(" ");
                currencyList.add(new Currency(split[0],split[1],split[2]));
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return currencyList;
    }
}
