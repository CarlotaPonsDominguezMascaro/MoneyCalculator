package app;


import Persistance.CurrencyListLoader;
import Persistance.ExchangeListLoader;
import Persistance.file.FileCurrencyLoader;
import Persistance.file.FileExchangeLoader;
import control.Comand;
import control.ProcessComand;
import model.Currency;
import view.MoneyDialog;
import view.MoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;


/**
 * @author carlotapons
 */
public class MoneyCalculator extends JFrame {

    private final CurrencyListLoader currencyListLoader;
    private final List<Currency> currencies;
    private final ExchangeListLoader exchangeRateLoader;
    private MoneyDialog moneyDialogFrom;
    private MoneyDialog moneyDialogTo;
    private MoneyDisplay moneyDisplay;

    private Comand processCommand;

    public static void main(String[] arg){
        new MoneyCalculator().execute();


    }
    public MoneyCalculator()  {
        this.setTitle("Money Calc");
        this.setSize(600,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);


        this.currencyListLoader = new FileCurrencyLoader("Currencies");
        this.exchangeRateLoader = new FileExchangeLoader("ExchangeRate");
        this.currencies = currencyListLoader.currencyLoad();

        this.add(moneyDialogFrom(), BorderLayout.WEST);
        this.add(toolbar(),BorderLayout.SOUTH);
        this.add(moneyDialogTo(),BorderLayout.CENTER);

        this.add(moneyDisplay(),BorderLayout.EAST);

    }

    private void execute() {

        this.processCommand = new ProcessComand(moneyDialogFrom,moneyDialogTo,moneyDisplay);
        this.setVisible(true);
    }




    private Component moneyDialogFrom(){
        SwingMoneyDialog swingMoneyDialog = new SwingMoneyDialog(currencies,true);
        this.moneyDialogFrom = swingMoneyDialog;
        return swingMoneyDialog;
    }

    private Component moneyDialogTo() {
        SwingMoneyDialog swingMoneyDialog = new SwingMoneyDialog(currencies,false);
        this.moneyDialogTo = swingMoneyDialog;
        return swingMoneyDialog;
    }

    private Component moneyDisplay() {
        SwingMoneyDisplay swingMoneyDisplay = new SwingMoneyDisplay();
        this.moneyDisplay = swingMoneyDisplay;
        return swingMoneyDisplay;
    }

    private Component toolbar() {
        JPanel toolBarPanel = new JPanel();
        toolBarPanel.add(calculateButton());
        return toolBarPanel;
    }

    private JButton calculateButton() {
        JButton button = new JButton("calculate");
        button.addActionListener(calculate());
        return button;
    }

    private ActionListener calculate() {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    processCommand.executed();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
    }





}

