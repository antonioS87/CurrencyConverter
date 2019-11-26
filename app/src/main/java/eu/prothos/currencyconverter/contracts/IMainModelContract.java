package eu.prothos.currencyconverter.contracts;

import java.util.ArrayList;

import eu.prothos.currencyconverter.beans.CurrencyBean;

public interface IMainModelContract {

    public void setPresenter(IMainActivityPresenterContract mainActivityPresenter);
    public ArrayList<CurrencyBean> getCurrencies();
    public Double getAmount();
    public void setAmount(Double amount);
    public Double getConvertedAmount();
    public void setConvertedAmount(Double convertedAmount);
    public CurrencyBean getFromCurrency();
    public void setFromCurrency(int fromCurrency);
    public CurrencyBean getToCurrency();
    public void setToCurrency(int toCurrency);

    public int getFromCurrencyIndex();
    public int getToCurrencyIndex();
}
