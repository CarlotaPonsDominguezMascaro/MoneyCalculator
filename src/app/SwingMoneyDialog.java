package app;


import model.Currency;
import model.Money;
import view.MoneyDialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class SwingMoneyDialog  extends JPanel implements MoneyDialog {
    private final Currency[] currencies;
    private String amount;
    private Currency currency;
    TextField textField = new TextField("0");


    public SwingMoneyDialog(List<Currency> currencies, boolean showAmount) {

        this.currencies = currencies.toArray(new Currency[0]);
        amount();
        if(showAmount){

            textField.setText(amount);
            this.add(textField);
        }
        this.add(currency());

    }



    @Override
    public Money get() {
        amount = textField.getText();
        return new Money( Double.parseDouble(amount), currency);
    }

    private void amount() {

        amount = textField.getText();

    }

    private Component currency() {
        JComboBox comboBox = new JComboBox(currencies);
        comboBox.addItemListener(currencyChanged());
        currency = (Currency) comboBox.getSelectedItem();
        return comboBox;
    }







    private ItemListener currencyChanged() {
        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent itemEvent){
                if(itemEvent.getStateChange() == ItemEvent.DESELECTED) return;
                currency = (Currency) itemEvent.getItem();
            }
        };
    }


}
