package com.nidhin.koinexticker.homescreen.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CoinDetails implements Parcelable {

    private final Coin mInr;
    private final Coin mTrueUsd;

    public CoinDetails(Coin inr, Coin trueUsd) {

        mInr = inr;
        mTrueUsd = trueUsd;
    }

    public Coin getInr() {
        return mInr;
    }

    public Coin getTrueUsd() {
        return mTrueUsd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mInr, flags);
        dest.writeParcelable(this.mTrueUsd, flags);
    }

    protected CoinDetails(Parcel in) {
        this.mInr = in.readParcelable(Coin.class.getClassLoader());
        this.mTrueUsd = in.readParcelable(Coin.class.getClassLoader());
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
