package eu.prothos.currencyconverter.contracts;

import java.util.ArrayList;

import eu.prothos.currencyconverter.beans.CurrencyBean;

public interface IMainActivityPresenterContract {
    public void fromCurrencySelected(int i);
    public void toCurrencySelected(int i);
    public void amountChanged(String s);
    public void onCurrenciesAvailable(ArrayList<CurrencyBean> currencyBeans);
    public void convertAmount();
    public void onResume();
    public void onDestroy();
}
