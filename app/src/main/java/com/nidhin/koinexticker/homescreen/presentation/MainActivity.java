package com.nidhin.koinexticker.homescreen.presentation;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nidhin.koinexticker.R;
import com.nidhin.koinexticker.coindetails.presentation.CoinDetailsActivity;
import com.nidhin.koinexticker.homescreen.data.Coin;
import com.nidhin.koinexticker.homescreen.di.MainViewModelFactory;
import com.nidhin.koinexticker.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends DaggerAppCompatActivity implements CoinListingAdapter.CoinInterface {

    @Inject
    MainViewModelFactory viewModelFactory;
    MainViewModel viewModel;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.header)
    TextView tvHeader;
    @BindView(R.id.sort)
    TextView tvSort;
    private CoinListingAdapter mListingAdapter;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private static int ENABLE_WIFI = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        viewModel.getCurrencyList().observe(this, this::loadCoins);
        viewModel.getSortBy().observe(this, this::setSortBy);
        viewModel.getBaseCurrencies().observe(this, this::setTabs);
        viewModel.getHeader().observe(this, this::setHeader);
        viewModel.getToast().observe(this, this::showToast);
        viewModel.getError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (Utils.checkInternetConnection(MainActivity.this)) {
                    viewModel.onResume();
                } else showNoInternetDialog(MainActivity.this);
            }
        });
        tvHeader.setVisibility(View.VISIBLE);

        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewModel.setBaseCurrency(String.valueOf(tab.getTag()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showToast(String toast) {
        Utils.makeToast(this, toast);
    }


    private void setHeader(String msg) {
        tvHeader.setText(msg);
    }

    private void setSortBy(Boolean sortBy) {
        tvSort.setCompoundDrawablesWithIntrinsicBounds(null, null,
                getDrawable(sortBy ? R.drawable.ic_sort_price : R.drawable.ic_sort_by_alpha), null);
    }

    void setTabs(List<String> baseCurrencies) {
        mTabLayout.setVisibility(View.VISIBLE);
        mTabLayout.removeAllTabs();
        for (String base : baseCurrencies) {

            TabLayout.Tab tab = mTabLayout.newTab();
            tab.setTag(base);
            tab.setText(Utils.formatStringToDisplay(base));
            mTabLayout.addTab(tab);
        }
    }

    @Override
    protected void onResume() {
        if (Utils.checkInternetConnection(this)) {
            viewModel.onResume();
        } else showNoInternetDialog(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        viewModel.onPause();
        super.onPause();
    }

    void loadCoins(List<Coin> list) {
        tvSort.setVisibility(View.VISIBLE);
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

    @OnClick(R.id.sort)
    void sortClicked() {

        viewModel.sortClicked();

    }

    @Override
    public void onCoinClicked(Coin coin, Pair pairOne, Pair pairTwo, Pair pairThree) {
        Intent intent = new Intent(this, CoinDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("coin", coin);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, pairOne, pairTwo, pairThree);
        startActivity(intent, options.toBundle());
        overridePendingTransition(0, 0);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public void showNoInternetDialog(final Context context) {

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage("Goto settings and enable internet")
                .setTitle("Internet not available")
                .setPositiveButton("Open Settings",
                        (paramDialogInterface, paramInt) -> {
                            paramDialogInterface.dismiss();
                            Intent myIntent = new Intent(
                                    Settings.ACTION_SETTINGS);
                            ((Activity) context).startActivityForResult(myIntent, ENABLE_WIFI);
                        })
                .setNeutralButton("Retry",
                        (paramDialogInterface, paramInt) -> {
                            paramDialogInterface.dismiss();
                            onResume();
                        })
                .setNegativeButton("Exit",
                        (paramDialogInterface, paramInt) -> ((Activity) context).finish()).setCancelable(false).create();
        dialog.show();
    }


}
