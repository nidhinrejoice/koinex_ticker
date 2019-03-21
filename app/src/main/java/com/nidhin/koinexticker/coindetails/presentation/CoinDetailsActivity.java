package com.nidhin.koinexticker.coindetails.presentation;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.nidhin.koinexticker.R;
import com.nidhin.koinexticker.homescreen.data.Coin;
import com.nidhin.koinexticker.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CoinDetailsActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    DetailsViewModel viewModel;
    @BindView(R.id.last_traded)
    TextView tvLastTraded;
    @BindView(R.id.percent_change)
    TextView tvPercentChange;
    @BindView(R.id.volume)
    TextView tvVolume;
    @BindView(R.id.tradingVolume)
    TextView tvTradingVolume;
    @BindView(R.id.low)
    TextView tvLow;
    @BindView(R.id.high)
    TextView tvHigh;
    @BindView(R.id.bid)
    TextView tvBid;
    @BindView(R.id.ask)
    TextView tvAsk;
    @BindView(R.id.spread)
    TextView tvSpread;
    @BindView(R.id.toolbar)
    TextView mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_details);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel.class);
        viewModel.getCoinName().observe(this, this::setCoinName);
        viewModel.getLastTraded().observe(this, this::setLastPrice);
        viewModel.getAsk().observe(this, this::setAsk);
        viewModel.getBid().observe(this, this::setBid);
        viewModel.getHigh().observe(this, this::setHigh);
        viewModel.getLow().observe(this, this::setLow);
        viewModel.getPercentage().observe(this, this::setPercentage);
        viewModel.getVolume().observe(this, this::setVolume);
        viewModel.getTradeVolume().observe(this, this::setTradeVolume);
        viewModel.getChange().observe(this, this::setChange);
        viewModel.getSpread().observe(this, this::setSpread);

        viewModel.setCoin(getIntent().getParcelableExtra("coin"));
    }

    private void setSpread(String val) {
        tvSpread.setText(val);
    }

    private void setChange(Boolean val) {
        tvPercentChange.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(
                val ? R.drawable.ic_arrow_upward : R.drawable.ic_arrow_downward), null);
        tvPercentChange.setTextColor(getResources().getColor(val ? R.color.green : R.color.red));
    }

    private void setTradeVolume(String val) {
        tvTradingVolume.setText(val);
    }

    private void setVolume(String val) {
        tvVolume.setText(val);
    }

    private void setPercentage(String val) {
        tvPercentChange.setText(val);
    }

    private void setLow(String val) {
        tvLow.setText(val);
    }

    private void setHigh(String val) {
        tvHigh.setText(val);
    }

    private void setBid(String val) {
        tvBid.setText(val);
    }

    private void setAsk(String val) {
        tvAsk.setText(val);
    }

    private void setLastPrice(String val) {
        tvLastTraded.setText(val);
    }

    private void setCoinName(String coinName) {
        mToolbar.setText(coinName);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.go_back)
    void goBack() {
        onBackPressed();
    }
}
