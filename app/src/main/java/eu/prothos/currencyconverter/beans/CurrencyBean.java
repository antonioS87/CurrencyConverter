package eu.prothos.currencyconverter.beans;

import com.google.gson.annotations.SerializedName;

public class CurrencyBean {

    @SerializedName("currency_code")
    private String currencyCode;
    @SerializedName("buying_rate")
    private Double buyingRate;
    @SerializedName("selling_rate")
    private Double sellingRate;
    @SerializedName("unit_value")
    private Double unitValue;
    @SerializedName("madian_rate")
    private Double medianRate;

    public CurrencyBean( String currencyCode, Double buyingRate, Double sellingRate, Double unitValue, Double medianRate){
        this.currencyCode = currencyCode;
        this.buyingRate = buyingRate;
        this.sellingRate = sellingRate;
        this.unitValue = unitValue;
        this.medianRate = medianRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(Double buyingRate) {
        this.buyingRate = buyingRate;
    }

    public Double getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(Double sellingRate) {
        this.sellingRate = sellingRate;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }

    public Double getMedianRate() {
        return medianRate;
    }

    public void setMedianRate(Double medianRate) {
        this.medianRate = medianRate;
    }
}
