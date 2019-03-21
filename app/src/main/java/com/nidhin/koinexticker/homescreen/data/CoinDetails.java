package com.nidhin.koinexticker.homescreen.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.List;

public class CoinDetails implements Parcelable {

    List<Coin> mCoinList;
    String mBaseCurrency;

    public CoinDetails(List<Coin> coinList,String baseCurrency) {
        mCoinList = coinList;
        mBaseCurrency = baseCurrency;
    }

    public CoinDetails() {


    }

    public String getBaseCurrency() {
        return mBaseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        mBaseCurrency = baseCurrency;
    }

    public List<Coin> getCoinList() {
        return mCoinList;
    }

    public void setCoinList(List<Coin> coinList) {
        mCoinList = coinList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mCoinList);
        dest.writeString(this.mBaseCurrency);
    }

    protected CoinDetails(Parcel in) {
        this.mCoinList = in.createTypedArrayList(Coin.CREATOR);
        this.mBaseCurrency = in.readString();
    }

    public static final Creator<CoinDetails> CREATOR = new Creator<CoinDetails>() {
        @Override
        public CoinDetails createFromParcel(Parcel source) {
            return new CoinDetails(source);
        }

        @Override
        public CoinDetails[] newArray(int size) {
            return new CoinDetails[size];
        }
    };
}
