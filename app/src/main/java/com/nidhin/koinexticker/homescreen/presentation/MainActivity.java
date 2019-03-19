package com.nidhin.koinexticker.homescreen.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nidhin.koinexticker.R;
import com.nidhin.koinexticker.homescreen.data.CoinDetails;
import com.nidhin.koinexticker.homescreen.di.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends DaggerAppCompatActivity implements CoinListingAdapter.CoinInterface {

    @Inject
    MainViewModelFactory viewModelFactory;
    MainViewModel viewModel;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.header)
    TextView mHeader;
    private CoinListingAdapter mListingAdapter;
    private CompositeDisposable timerSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        viewModel.getCurrencyList().observe(this, this::loadCoins);
        mHeader.setVisibility(View.VISIBLE);
        timerSubscription=new CompositeDisposable();
    }

    @Override
    protected void onResume() {
        timerSubscription.add(Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if(aLong%180==0) {
                        mHeader.setText("Fetching latest price...");
                        viewModel.getLatestPrices();
                    }else mHeader.setText("Refreshing in "+aLong%180+" seconds");
                }));
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        timerSubscription.dispose();
        super.onDestroy();
    }

    void loadCoins(List<CoinDetails> list) {
        if (mListingAdapter == null) {
            mListingAdapter = new CoinListingAdapter(this, list);
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager mlayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mlayoutManager);
//            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
//                    mlayoutManager.getOrientation());
//            mRecyclerView.addItemDecoration(dividerItemDecoration);
            mRecyclerView.setAdapter(mListingAdapter);
        } else {
            mListingAdapter.updateItems(list);
        }
    }

    @Override
    public void onCoinClicked(CoinDetails coinDetails) {

    }
}
