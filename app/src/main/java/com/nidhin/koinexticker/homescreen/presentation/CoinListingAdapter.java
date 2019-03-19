package com.nidhin.koinexticker.homescreen.presentation;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nidhin.koinexticker.R;
import com.nidhin.koinexticker.homescreen.data.CoinDetails;
import com.nidhin.koinexticker.utils.Utils;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CoinListingAdapter extends RecyclerView
        .Adapter<RecyclerView
        .ViewHolder> {
    private final Map<View, Map<Integer, View>> cache = new HashMap<View, Map<Integer, View>>();
    CoinInterface mAddressInterface;
    int selectedPos;
    private Context mContext;
    private List<CoinDetails> list;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public CoinListingAdapter(Context context, List<CoinDetails> list) {
        mContext = context;
        this.list = list;
        this.mAddressInterface = (CoinInterface) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_coins, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(final RecyclerView
            .ViewHolder holder, int position) {

        ViewHolder vh = (ViewHolder) holder;
        try {
            CoinDetails coinDetails = list.get(position);
            vh.tvCoinName.setText(coinDetails.getInr().getCurrencyShortForm());
            vh.tvCoinFullForm.setText(Utils.formatStringToDisplay(
                    coinDetails.getInr().getCurrencyFullForm()));
            Double percentChange = Double.valueOf(coinDetails.getInr().getPerChange());
            vh.tvPercentChange.setText(Utils.twoDecimalPlaces(percentChange) + "%");
            vh.tvPercentChange.setTextColor(percentChange > 0 ? Color.GREEN : percentChange == 0 ? Color.WHITE : Color.RED);
            vh.tvPercentChange.setCompoundDrawablesWithIntrinsicBounds(null, null, mContext.getResources().getDrawable(
                    percentChange > 0 ? R.drawable.ic_arrow_upward : percentChange == 0 ? R.drawable.ic_arrow_right : R.drawable.ic_arrow_downward), null);
            vh.tvLastTraded.setText("\u20B9" + (coinDetails.getInr().getLastTradedPrice()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateItems(List<CoinDetails> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }


    public interface CoinInterface {

        public void onCoinClicked(CoinDetails coinDetails);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCoinName, tvCoinFullForm, tvPercentChange, tvLastTraded;

        public ViewHolder(View v) {
            super(v);
            tvCoinName = v.findViewById(R.id.coin_name);
            tvCoinFullForm = v.findViewById(R.id.coin_full_form);
            tvPercentChange = v.findViewById(R.id.percent_change);
            tvLastTraded = v.findViewById(R.id.last_traded);


        }
    }

}