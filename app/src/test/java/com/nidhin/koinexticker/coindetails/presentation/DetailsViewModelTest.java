package com.nidhin.koinexticker.coindetails.presentation;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.google.gson.Gson;
import com.nidhin.koinexticker.api.ApiManager;
import com.nidhin.koinexticker.homescreen.data.Coin;
import com.nidhin.koinexticker.persistance.SharedPrefsManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

public class DetailsViewModelTest {


    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    DetailsViewModel mViewModel;
    @Mock
    Observer<String> mStringObserver;
    @Mock
    Observer<List<String>> mListObserver;
    @Mock
    Observer<List<Coin>> mCoinListObserver;
    @Mock
    Observer<Boolean> mBooleanObserver;
    private Gson mGson;
    Coin mCoin;
    @Mock
    ApiManager mApiManager;
    @Mock
    SharedPrefsManager mSharedPrefsManager;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mGson = new Gson();
        mViewModel = new DetailsViewModel();
    }

    Coin mockCoin() {
        mCoin = new Coin();
        mCoin.setLastTradedPrice("200");
        mCoin.setHighestBid("190");
        mCoin.setLowestAsk("200");
        mCoin.setMax24hrs("205");
        mCoin.setMin24hrs("180");
        mCoin.setBaseCurrency("INR");
        mCoin.setCurrencyFullForm("ETHER");
        mCoin.setCurrencyShortForm("ETH");
        mCoin.setPerChange("-0.8");
        mCoin.setTradeVolume("201200.3");
        mCoin.setVol24hrs("1000.2");
        return mCoin;
    }


    @Test
    public void testSpreadCalculation() {
        mViewModel.getSpread().observeForever(mStringObserver);
        mViewModel.getChange().observeForever(mBooleanObserver);
        mViewModel.setCoin(mockCoin());
        verify(mStringObserver).onChanged("--- SPREAD 5% ---"); //(200-190)/200*100
        verify(mBooleanObserver).onChanged(false);
    }
}