package com.nidhin.koinexticker.homescreen.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.nidhin.koinexticker.homescreen.data.CoinDetails;
import com.nidhin.koinexticker.homescreen.domain.FetchLatestPrices;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class MainViewModel extends ViewModel {
    private FetchLatestPrices mFetchLatestPrices;
    private MutableLiveData<List<CoinDetails>> currencyList;
    private MutableLiveData<String> pDialog;
    private MutableLiveData<String> toast;

    public MutableLiveData<List<CoinDetails>> getCurrencyList() {
        return currencyList;
    }

    public MutableLiveData<String> getpDialog() {
        return pDialog;
    }

    public MutableLiveData<String> getToast() {
        return toast;
    }

    public MainViewModel(FetchLatestPrices fetchLatestPrices) {

        mFetchLatestPrices = fetchLatestPrices;
        currencyList = new MutableLiveData<>();
        toast=new MutableLiveData<>();
        pDialog=new MutableLiveData<>();
    }

    public void getLatestPrices() {
        mFetchLatestPrices.execute(new DisposableSingleObserver<List<CoinDetails>>() {
            @Override
            public void onSuccess(List<CoinDetails> value) {
                currencyList.setValue(value);
                pDialog.setValue("");
            }

            @Override
            public void onError(Throwable e) {
                pDialog.setValue("");
            }
        }, null);
    }
}
