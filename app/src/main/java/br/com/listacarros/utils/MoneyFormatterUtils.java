package br.com.listacarros.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormatterUtils {

    public static String convertToMoney(double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));
        return formatter.format(value);
    }
}
