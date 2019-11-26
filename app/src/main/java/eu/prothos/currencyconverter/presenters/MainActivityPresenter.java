package eu.prothos.currencyconverter.presenters;

import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import eu.prothos.currencyconverter.MainActivity;
import eu.prothos.currencyconverter.beans.CurrencyBean;
import eu.prothos.currencyconverter.contracts.IMainActivityContract;
import eu.prothos.currencyconverter.contracts.IMainActivityPresenterContract;
import eu.prothos.currencyconverter.contracts.IMainModelContract;
import eu.prothos.currencyconverter.models.MainActivityModel;

public class MainActivityPresenter implements IMainActivityPresenterContract {

    private IMainActivityContract mainActivity;
    private IMainModelContract model;
    private String DEBUG_TAG = getClass().getCanonicalName();

    public MainActivityPresenter (IMainActivityContract activity, IMainModelContract mainModelContract){
        this.mainActivity = activity;
        this.model = mainModelContract;
        this.model.setPresenter( this );

        if( this.model.getToCurrency() != null ){

        }

    }



    public void fromCurrencySelected(int i) {
        this.model.setFromCurrency( i );
        Log.d( DEBUG_TAG, " from currency set: " + model.getFromCurrency().getCurrencyCode());
        convertAmount();

    }

    public void toCurrencySelected(int i) {
        this.model.setToCurrency( i );
        Log.d( DEBUG_TAG, " to currency set: " + model.getToCurrency().getCurrencyCode());
        convertAmount();
    }

    public void convertAmount(){
        if( this.model.getAmount() == null) {
            this.model.setAmount( 0.0 );
        }

        if( this.model.getFromCurrency() != null && this.model.getToCurrency() != null) {
            Log.d(DEBUG_TAG, " amount changed: " + this.model.getAmount());
            this.model.setConvertedAmount(this.model.getAmount() * this.model.getFromCurrency().getBuyingRate() / this.model.getToCurrency().getSellingRate());
            this.mainActivity.setConvertedAmount( this.model.getConvertedAmount() );
        }
    }

    @Override
    public void onResume() {

        this.mainActivity.setFromCurrency( this.model.getFromCurrencyIndex() );
        this.mainActivity.setToCurrency ( this.model.getToCurrencyIndex() );
    }

    @Override
    public void onDestroy() {
        this.mainActivity = null;
    }


    public void amountChanged(String s) {
        if(!s.isEmpty()) {
            this.model.setAmount( Double.parseDouble(s) );
        } else{
            this.model.setAmount( 0.0 );
        }
        Log.d( DEBUG_TAG, " amount changed: " + this.model.getAmount() );
        this.model.setConvertedAmount( this.model.getAmount()*this.model.getFromCurrency().getBuyingRate()/this.model.getToCurrency().getSellingRate() );
        this.mainActivity.setConvertedAmount( this.model.getConvertedAmount() );
    }

    public void onCurrenciesAvailable(ArrayList<CurrencyBean> currencyBeans) {
        List<String> currencyStringList = new ArrayList<>();

        for( CurrencyBean currencyBean : currencyBeans ){
            currencyStringList.add( currencyBean.getCurrencyCode() );
        }
        this.mainActivity.initializeSpinners( currencyStringList, this.model.getFromCurrencyIndex(), this.model.getToCurrencyIndex() );

    }
}
