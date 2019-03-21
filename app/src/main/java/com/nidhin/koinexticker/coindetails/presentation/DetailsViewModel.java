package com.nidhin.koinexticker.coindetails.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Parcelable;

import com.nidhin.koinexticker.homescreen.data.Coin;
import com.nidhin.koinexticker.homescreen.data.CoinDetails;
import com.nidhin.koinexticker.homescreen.domain.FetchLatestPrices;
import com.nidhin.koinexticker.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class DetailsViewModel extends ViewModel {

    private MutableLiveData<List<String>> mBaseCurrencies;
    private MutableLiveData<String> mCoinName;
    private MutableLiveData<String> lastTraded;
    private MutableLiveData<String> bid;
    private MutableLiveData<String> ask;
    private MutableLiveData<String> spread;
    private MutableLiveData<String> percentage;
    private MutableLiveData<String> low;
    private MutableLiveData<String> high;
    private MutableLiveData<String> volume;
    private MutableLiveData<String> tradeVolume;
    private MutableLiveData<Boolean> change;
    private Coin coin;

    @Inject
    public DetailsViewModel() {
        mBaseCurrencies = new MutableLiveData<>();
        mCoinName = new MutableLiveData<>();
        lastTraded = new MutableLiveData<>();
        bid = new MutableLiveData<>();
        ask = new MutableLiveData<>();
        spread = new MutableLiveData<>();
        percentage = new MutableLiveData<>();
        low = new MutableLiveData<>();
        high = new MutableLiveData<>();
        volume = new MutableLiveData<>();
        tradeVolume = new MutableLiveData<>();
        change = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getChange() {
        return change;
    }

    public MutableLiveData<List<String>> getBaseCurrencies() {
        return mBaseCurrencies;
    }

    public MutableLiveData<String> getCoinName() {
        return mCoinName;
    }

    public MutableLiveData<String> getLastTraded() {
        return lastTraded;
    }

    public MutableLiveData<String> getBid() {
        return bid;
    }

    public MutableLiveData<String> getAsk() {
        return ask;
    }

    public MutableLiveData<String> getSpread() {
        return spread;
    }

    public MutableLiveData<String> getPercentage() {
        return percentage;
    }

    public MutableLiveData<String> getLow() {
        return low;
    }

    public MutableLiveData<String> getHigh() {
        return high;
    }

    public MutableLiveData<String> getVolume() {
        return volume;
    }

    public MutableLiveData<String> getTradeVolume() {
        return tradeVolume;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
        setCoinInfo();
    }

    void setCoinInfo() {
        String currency = (coin.getBaseCurrency().equalsIgnoreCase("inr") ? "\u20B9" : "$");
        mCoinName.setValue(Utils.formatStringToDisplay(coin.getCurrencyFullForm()));
        Double currentVal=Double.parseDouble(coin.getLastTradedPrice());
        lastTraded.setValue(currency + Utils.formatAmount(currentVal));
        Double bidVal=Double.valueOf(coin.getHighestBid());
        Double askVal=Double.valueOf(coin.getLowestAsk());
        bid.setValue(currency + Utils.formatAmount(bidVal));
        ask.setValue(currency + Utils.formatAmount(askVal));
        spread.setValue("--- SPREAD "+Utils.calculateSpread(coin)+"% ---");
        Double percentChange = Double.valueOf(coin.getPerChange());
        percentage.setValue(Utils.twoDecimalPlaces(percentChange) + "%");
        low.setValue(currency + Utils.formatAmount(Double.valueOf(coin.getMin24hrs())));
        high.setValue(currency + Utils.formatAmount(Double.valueOf(coin.getMax24hrs())));
        volume.setValue(coin.getVol24hrs() + " " + coin.getCurrencyShortForm());
        tradeVolume.setValue(currency + Utils.formatAmount(Double.valueOf(coin.getTradeVolume())));
        if (percentChange != 0)
            change.setValue(percentChange > 0);
    }
}
