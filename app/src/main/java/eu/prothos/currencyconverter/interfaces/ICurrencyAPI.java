package eu.prothos.currencyconverter.interfaces;

import java.util.ArrayList;
import java.util.List;

import eu.prothos.currencyconverter.beans.CurrencyBean;
import eu.prothos.currencyconverter.response.CurrencyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICurrencyAPI {

    @GET("daily/")
    Call<ArrayList<CurrencyBean>> getCurrencies();
}
