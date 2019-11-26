package eu.prothos.currencyconverter.response;

import java.util.ArrayList;

import eu.prothos.currencyconverter.beans.CurrencyBean;

public class CurrencyResponse {

    private ArrayList<CurrencyBean> currencyBeans;

    public ArrayList<CurrencyBean> getCurrencyBeans() {
        return currencyBeans;
    }
    public void setCurrencyBeans(ArrayList<CurrencyBean> currencyBeans) {
        this.currencyBeans = currencyBeans;
    }

}
