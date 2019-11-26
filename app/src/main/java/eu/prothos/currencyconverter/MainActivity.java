package eu.prothos.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import eu.prothos.currencyconverter.contracts.IMainActivityContract;
import eu.prothos.currencyconverter.contracts.IMainActivityPresenterContract;
import eu.prothos.currencyconverter.models.MainActivityModel;
import eu.prothos.currencyconverter.presenters.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements IMainActivityContract {

    private IMainActivityPresenterContract mainActivityPresenter;
    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;
    private EditText amount;
    private TextView convertedAmount;
    private Button convertButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fromCurrencySpinner = findViewById( R.id.from_currency_spinner );
        this.toCurrencySpinner = findViewById( R.id.to_currency_spinner );
        this.amount = findViewById( R.id.amount_input );
        this.convertedAmount = findViewById( R.id.result_amount );
        this.convertButton = findViewById( R.id.convert_button );

        this.mainActivityPresenter = new MainActivityPresenter( this, ViewModelProviders.of( this ).get( MainActivityModel.class ) );

    }

    @Override
    public void onResume() {
        this.mainActivityPresenter.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        this.mainActivityPresenter.onDestroy();
        super.onDestroy();
    }


    public void initializeSpinners(List<String> currencies, int fromCurrencyIndex, int toCurrencyIndex ){
        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<String>(this,R.layout.language_selection,R.id.language_text_view, currencies );
        this.fromCurrencySpinner.setAdapter( currencyAdapter );
        this.toCurrencySpinner.setAdapter( currencyAdapter );



        this.fromCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mainActivityPresenter.fromCurrencySelected( i );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.toCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mainActivityPresenter.toCurrencySelected( i );

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mainActivityPresenter.amountChanged( charSequence.toString() );
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        this.convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityPresenter.convertAmount();
            }
        });

        this.fromCurrencySpinner.setSelection( fromCurrencyIndex );
        this.toCurrencySpinner.setSelection( toCurrencyIndex );

    }

    @Override
    public void setToCurrency(int toCurrencyIndex) {

    }

    @Override
    public void setFromCurrency(int fromCurrencyIndex) {

    }

    public void setConvertedAmount( Double amount){
        this.convertedAmount.setText( amount.toString() );
    }


}
