package com.nidhin.koinexticker.homescreen.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.nidhin.koinexticker.homescreen.data.Coin;
import com.nidhin.koinexticker.homescreen.data.CoinDetails;
import com.nidhin.koinexticker.homescreen.domain.CheckLastCheckedAt;
import com.nidhin.koinexticker.homescreen.domain.FetchLatestPrices;
import com.nidhin.koinexticker.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private FetchLatestPrices mFetchLatestPrices;
    private CheckLastCheckedAt mCheckLastCheckedAt;
    private MutableLiveData<List<Coin>> currencyList;
    private MutableLiveData<String> pDialog;
    private MutableLiveData<String> toast;
    private MutableLiveData<String> header;
    private MutableLiveData<Boolean> sortBy;
    private boolean sortByPrice;
    private List<CoinDetails> priceList;
    private List<Coin> coinList;
    private MutableLiveData<List<String>> mBaseCurrencies;
    private String mBaseCurrency;
    private CompositeDisposable timerSubscription;

    public MutableLiveData<Boolean> getSortBy() {
        return sortBy;
    }

    public MutableLiveData<List<Coin>> getCurrencyList() {
        return currencyList;
    }

    public MutableLiveData<String> getpDialog() {
        return pDialog;
    }

    public MutableLiveData<String> getToast() {
        return toast;
    }

    public MainViewModel(FetchLatestPrices fetchLatestPrices, CheckLastCheckedAt checkLastCheckedAt) {

        mFetchLatestPrices = fetchLatestPrices;
        mCheckLastCheckedAt = checkLastCheckedAt;
        currencyList = new MutableLiveData<>();
        toast = new MutableLiveData<>();
        pDialog = new MutableLiveData<>();
        sortBy = new MutableLiveData<>();
        mBaseCurrencies = new MutableLiveData<>();
        header = new MutableLiveData<>();
    }

    public MutableLiveData<String> getHeader() {
        return header;
    }

    public MutableLiveData<List<String>> getBaseCurrencies() {
        return mBaseCurrencies;
    }

    public void getLatestPrices() {
        mFetchLatestPrices.execute(new DisposableSingleObserver<List<CoinDetails>>() {

            @Override
            public void onSuccess(List<CoinDetails> value) {
                priceList = value;
                coinList = priceList.get(0).getCoinList();
                pDialog.setValue("");
                List<String> baseCurrencyList = new ArrayList<>();
                for (CoinDetails coinDetails : priceList) {
                    baseCurrencyList.add((coinDetails.getBaseCurrency()));
                }
                if (Utils.isNotStringEmpty(mBaseCurrency)) {
                    setBaseCurrency(mBaseCurrency);
                } else
                    mBaseCurrencies.setValue(baseCurrencyList);
                onResume();
            }

            @Override
            public void onError(Throwable e) {
                priceList = new ArrayList<>();
                pDialog.setValue("");
                toast.setValue(Utils.getException(e));
                onResume();
            }
        }, null);
    }

    public void sortClicked() {

        sortByPrice = !sortByPrice;
        sort();
    }

    private void sort() {
        if (sortByPrice)
            Collections.sort(coinList);
        else
            Collections.sort(coinList, (coinDetails, t1) -> (coinDetails.getCurrencyShortForm().compareTo(t1.getCurrencyShortForm())));
        currencyList.setValue(coinList);
        sortBy.setValue(sortByPrice);
    }

    public void setBaseCurrency(String baseCurrency) {

        mBaseCurrency = baseCurrency;
        for (CoinDetails coinDetails : priceList) {
            if (coinDetails.getBaseCurrency().equalsIgnoreCase(mBaseCurrency)) {
                coinList = coinDetails.getCoinList();
                break;
            }
        }
        sort();

    }

    public void onResume() {
        mCheckLastCheckedAt.execute(new DisposableSingleObserver<Long>() {
            @Override
            public void onSuccess(Long value) {
                if (value > 60)
                    value = 0L;
                timerSubscription = new CompositeDisposable();
                Long finalValue = value;
                timerSubscription.add(Observable.interval(0, 1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            aLong = aLong + finalValue;
                            if (aLong % 60 == 0 || priceList == null) {
                                header.setValue("Fetching latest price...");
                                getLatestPrices();
                                onPause();
                            } else header.setValue("Refreshing in " + aLong % 60 + " seconds");
                        }));
            }

            @Override
            public void onError(Throwable e) {

            }
        }, null);

    }

    public void onPause() {
        if (timerSubscription != null)
            timerSubscription.dispose();
    }
}
