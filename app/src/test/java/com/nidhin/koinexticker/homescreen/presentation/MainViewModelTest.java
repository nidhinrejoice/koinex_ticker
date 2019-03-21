package com.nidhin.koinexticker.homescreen.presentation;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.nidhin.koinexticker.api.ApiManager;
import com.nidhin.koinexticker.homescreen.data.Coin;
import com.nidhin.koinexticker.homescreen.data.CoinDetails;
import com.nidhin.koinexticker.homescreen.data.IMainActivityRepository;
import com.nidhin.koinexticker.homescreen.domain.CheckLastCheckedAt;
import com.nidhin.koinexticker.homescreen.domain.FetchLatestPrices;
import com.nidhin.koinexticker.homescreen.domain.MainActivityRepository;
import com.nidhin.koinexticker.persistance.SharedPrefsManager;
import com.nidhin.koinexticker.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    MainViewModel mViewModel;
    @Mock
    Observer<String> mStringObserver;
    @Mock
    Observer<List<String>> mListObserver;
    @Mock
    Observer<List<Coin>> mCoinListObserver;
    @Mock
    Observer<Boolean> mSuccessObserver;
    private Gson mGson;

    private FetchLatestPrices mFetchLatestPrices;
    private CheckLastCheckedAt mCheckLastCheckedAt;
    @Mock
    ApiManager mApiManager;
    @Mock
    SharedPrefsManager mSharedPrefsManager;
    MainActivityRepository mRepository;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    String mockResponse = "{\n" +
            "\t \n" +
            "\t\"stats\": {\n" +
            "\t\t\"inr\": {\n" +
            "\t\t\t\"ETH\": {\n" +
            "\t\t\t\t\"highest_bid\": \"9740\",\n" +
            "\t\t\t\t\"lowest_ask\": \"9799.98\",\n" +
            "\t\t\t\t\"last_traded_price\": \"9750\",\n" +
            "\t\t\t\t\"min_24hrs\": \"9612.05\",\n" +
            "\t\t\t\t\"max_24hrs\": \"9800.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"76.658\",\n" +
            "\t\t\t\t\"currency_full_form\": \"ether\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ETH\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-0.2047082906857728\",\n" +
            "\t\t\t\t\"trade_volume\": \"154759.44530000002\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BTC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"282000\",\n" +
            "\t\t\t\t\"lowest_ask\": \"282500\",\n" +
            "\t\t\t\t\"last_traded_price\": \"282500\",\n" +
            "\t\t\t\t\"min_24hrs\": \"281105.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"285000.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"8.2922\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bitcoin\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BTC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-0.3527336860670194\",\n" +
            "\t\t\t\t\"trade_volume\": \"1304391.15826\"\n" +
            "\t\t\t} \n" +
            "\t\t},\n" +
            "\t\t\"true_usd\": { \n" +
            "\t\t\t\"BTC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"3970.4361\",\n" +
            "\t\t\t\t\"lowest_ask\": \"4026.2653\",\n" +
            "\t\t\t\t\"last_traded_price\": \"3969.8847\",\n" +
            "\t\t\t\t\"min_24hrs\": \"3945.783\",\n" +
            "\t\t\t\t\"max_24hrs\": \"4028.527\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"1.5843\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bitcoin\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BTC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0.25510741371434154\",\n" +
            "\t\t\t\t\"trade_volume\": \"6238.445314390002\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ETH\": {\n" +
            "\t\t\t\t\"highest_bid\": \"137.69\",\n" +
            "\t\t\t\t\"lowest_ask\": \"140.69\",\n" +
            "\t\t\t\t\"last_traded_price\": \"138.8949\",\n" +
            "\t\t\t\t\"min_24hrs\": \"136.6251\",\n" +
            "\t\t\t\t\"max_24hrs\": \"139.0949\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"2.963\",\n" +
            "\t\t\t\t\"currency_full_form\": \"ether\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ETH\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0.608453593461734\",\n" +
            "\t\t\t\t\"trade_volume\": \"419.3427871\"\n" +
            "\t\t\t} \n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mGson = new Gson();
        mRepository = new IMainActivityRepository(mApiManager, mSharedPrefsManager, mGson);
        mFetchLatestPrices = new FetchLatestPrices(mRepository);
        mCheckLastCheckedAt = new CheckLastCheckedAt(mRepository);
        mViewModel = new MainViewModel(mFetchLatestPrices, mCheckLastCheckedAt);
    }

    @Test
    public void testFetchPricesFromApi() throws JSONException {
        when(mSharedPrefsManager.hasKey("prices")).thenReturn(false);
        when(mSharedPrefsManager.getLongValue("lastCheckedAt")).thenReturn(0L);
        when(mApiManager.ticker()).thenReturn(Single.just(new JSONObject(mockResponse)));
        mViewModel.getLatestPrices();
        mViewModel.getpDialog().observeForever(mStringObserver);
        mViewModel.getBaseCurrencies().observeForever(mListObserver);
        mViewModel.getCurrencyList().observeForever(mCoinListObserver);
        mStringObserver.onChanged("");
        List<String> list=new ArrayList<>();
        list.add("true_usd");
        list.add("inr");
        verify(mListObserver).onChanged(list);
        verify(mCoinListObserver).onChanged(anyList());
    }

    @Test
    public void testFetchPricesWhenInternetNotAvailable() throws JSONException {
        when(mSharedPrefsManager.hasKey("prices")).thenReturn(false);
        when(mSharedPrefsManager.getLongValue("lastCheckedAt")).thenReturn(0L);
        when(mApiManager.ticker()).thenReturn(Single.error(new ConnectException()));
        mViewModel.getLatestPrices();
        mViewModel.getToast().observeForever(mStringObserver);
        mViewModel.getCurrencyList().observeForever(mCoinListObserver);
        verify(mStringObserver).onChanged(Utils.getException(new ConnectException()));
    }
}