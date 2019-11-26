package eu.prothos.currencyconverter.models;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;


import eu.prothos.currencyconverter.beans.CurrencyBean;
import eu.prothos.currencyconverter.contracts.IMainActivityPresenterContract;
import eu.prothos.currencyconverter.contracts.IMainModelContract;
import eu.prothos.currencyconverter.interfaces.ICurrencyAPI;
import eu.prothos.currencyconverter.retrofit.CurrencyClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivityModel extends AndroidViewModel implements IMainModelContract {

    private ArrayList<CurrencyBean> currencyList;
    private IMainActivityPresenterContract mainActivityPresenter;
    private static Retrofit retrofit = null;
    private final static String API_KEY = "";
    private String DEBUG_TAG = getClass().getCanonicalName();
    private int fromCurrency;
    private int toCurrency;
    private Double amount;
    private Double convertedAmount;

    public MainActivityModel(@NonNull Application application) {

        super(application);
        getCurrenciesOnStart();
//        mainActivityPresenter.onCurrenciesAvailable( currencyList );
    }


    private void getCurrenciesOnStart() {
        Log.d( DEBUG_TAG, " getting currencies ");
        ICurrencyAPI service = CurrencyClientInstance.getRetrofitInstance().create(ICurrencyAPI.class);
        Call<ArrayList<CurrencyBean>> currencyCall = service.getCurrencies();
        currencyCall.enqueue(new Callback<ArrayList<CurrencyBean>>() {
            @Override
            public void onResponse(Call<ArrayList<CurrencyBean>> call, Response<ArrayList<CurrencyBean>> response) {
                currencyList = response.body();
                mainActivityPresenter.onCurrenciesAvailable( currencyList );
            }

            @Override
            public void onFailure(Call<ArrayList<CurrencyBean>> call, Throwable t) {
                Log.d( DEBUG_TAG, " retrofit error: " + t.toString());
            }
        });
    }


    public ArrayList<CurrencyBean> getCurrencies(){

        return this.currencyList;
    }


    public void setPresenter(IMainActivityPresenterContract mainActivityPresenter) {
        Log.d( DEBUG_TAG, " setting presenter and getting currencies");
        this.mainActivityPresenter = mainActivityPresenter;
        getCurrenciesOnStart();
    }

    public CurrencyBean getFromCurrency() {

        return currencyList.get( this.fromCurrency );
    }

    public void setFromCurrency(int fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public CurrencyBean getToCurrency() {

        if( currencyList != null ) {
            return currencyList.get(toCurrency);
        }else return null;

    }

    public void setToCurrency(int toCurrency) {

        this.toCurrency = toCurrency;
    }

    @Override
    public int getFromCurrencyIndex() {
        return this.fromCurrency;
    }

    @Override
    public int getToCurrencyIndex() {
        return this.toCurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(Double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
