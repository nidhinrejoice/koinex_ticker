package com.nidhin.koinexticker.homescreen.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coin implements Parcelable {

    @SerializedName("highest_bid")
    @Expose
    private String highestBid;
    @SerializedName("lowest_ask")
    @Expose
    private String lowestAsk;
    @SerializedName("last_traded_price")
    @Expose
    private String lastTradedPrice;
    @SerializedName("min_24hrs")
    @Expose
    private String min24hrs;
    @SerializedName("max_24hrs")
    @Expose
    private String max24hrs;
    @SerializedName("vol_24hrs")
    @Expose
    private String vol24hrs;
    @SerializedName("currency_full_form")
    @Expose
    private String currencyFullForm;
    @SerializedName("currency_short_form")
    @Expose
    private String currencyShortForm;
    @SerializedName("baseCurrency")
    @Expose
    private String baseCurrency;
    @SerializedName("per_change")
    @Expose
    private String perChange;
    @SerializedName("trade_volume")
    @Expose
    private String tradeVolume;
    public final static Parcelable.Creator<Coin> CREATOR = new Creator<Coin>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Coin createFromParcel(Parcel in) {
            return new Coin(in);
        }

        public Coin[] newArray(int size) {
            return (new Coin[size]);
        }

    };

    protected Coin(Parcel in) {
        this.highestBid = ((String) in.readValue((String.class.getClassLoader())));
        this.lowestAsk = ((String) in.readValue((String.class.getClassLoader())));
        this.lastTradedPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.min24hrs = ((String) in.readValue((String.class.getClassLoader())));
        this.max24hrs = ((String) in.readValue((String.class.getClassLoader())));
        this.vol24hrs = ((String) in.readValue((String.class.getClassLoader())));
        this.currencyFullForm = ((String) in.readValue((String.class.getClassLoader())));
        this.currencyShortForm = ((String) in.readValue((String.class.getClassLoader())));
        this.baseCurrency = ((String) in.readValue((String.class.getClassLoader())));
        this.perChange = ((String) in.readValue((String.class.getClassLoader())));
        this.tradeVolume = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Coin() {
    }

    public String getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    public String getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(String lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public String getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(String lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    public String getMin24hrs() {
        return min24hrs;
    }

    public void setMin24hrs(String min24hrs) {
        this.min24hrs = min24hrs;
    }

    public String getMax24hrs() {
        return max24hrs;
    }

    public void setMax24hrs(String max24hrs) {
        this.max24hrs = max24hrs;
    }

    public String getVol24hrs() {
        return vol24hrs;
    }

    public void setVol24hrs(String vol24hrs) {
        this.vol24hrs = vol24hrs;
    }

    public String getCurrencyFullForm() {
        return currencyFullForm;
    }

    public void setCurrencyFullForm(String currencyFullForm) {
        this.currencyFullForm = currencyFullForm;
    }

    public String getCurrencyShortForm() {
        return currencyShortForm;
    }

    public void setCurrencyShortForm(String currencyShortForm) {
        this.currencyShortForm = currencyShortForm;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getPerChange() {
        return perChange;
    }

    public void setPerChange(String perChange) {
        this.perChange = perChange;
    }

    public String getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(String tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(highestBid);
        dest.writeValue(lowestAsk);
        dest.writeValue(lastTradedPrice);
        dest.writeValue(min24hrs);
        dest.writeValue(max24hrs);
        dest.writeValue(vol24hrs);
        dest.writeValue(currencyFullForm);
        dest.writeValue(currencyShortForm);
        dest.writeValue(baseCurrency);
        dest.writeValue(perChange);
        dest.writeValue(tradeVolume);
    }

    public int describeContents() {
        return 0;
    }

}