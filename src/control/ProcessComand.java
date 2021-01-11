package control;

import Persistance.file.FileExchangeLoader;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import view.MoneyDialog;
import view.MoneyDisplay;

import java.io.IOException;

public class ProcessComand implements Comand{

    private MoneyDialog moneyDialogFrom;
    private MoneyDialog moneyDialogTo;
    private MoneyDisplay moneyDisplay;

    public ProcessComand(MoneyDialog moneyDialogFrom, MoneyDialog moneyDialogTo, MoneyDisplay moneyDisplay) {
        this.moneyDialogFrom = moneyDialogFrom;
        this.moneyDialogTo = moneyDialogTo;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void executed() throws IOException {
        Money money = moneyDialogFrom.get();
        Currency currency = moneyDialogTo.get().getCurrency();
        ExchangeRate exchangeRate = new FileExchangeLoader("ExchangeRate").exchangeLoader(money.getCurrency(),currency);
        Money result = new Money(money.getAmount()* exchangeRate.getRate(),currency);
        moneyDisplay.display(result);
    }
}
