package Persistance.file;

import Persistance.ExchangeListLoader;
import model.Currency;
import model.ExchangeRate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileExchangeLoader implements ExchangeListLoader {

    private String fileName;


    public FileExchangeLoader(String name) {
        this.fileName = name;
    }




    @Override
    public ExchangeRate exchangeLoader(Currency from, Currency to) throws IOException {
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
    }
}
