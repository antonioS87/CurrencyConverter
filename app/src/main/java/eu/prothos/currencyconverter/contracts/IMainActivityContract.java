package eu.prothos.currencyconverter.contracts;

import java.util.List;

public interface IMainActivityContract {
    public void setConvertedAmount( Double amount);
    public void initializeSpinners(List<String> currencies, int fromCurrencyIndex, int toCurrencyIndex);

    void setToCurrency(int toCurrencyIndex);

    void setFromCurrency(int fromCurrencyIndex);
}
