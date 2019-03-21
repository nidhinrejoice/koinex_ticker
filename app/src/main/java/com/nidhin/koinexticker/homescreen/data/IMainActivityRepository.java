package com.nidhin.koinexticker.homescreen.data;

import com.google.gson.Gson;
import com.nidhin.koinexticker.api.ApiManager;
import com.nidhin.koinexticker.homescreen.domain.MainActivityRepository;
import com.nidhin.koinexticker.persistance.SharedPrefsManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class IMainActivityRepository implements MainActivityRepository {

    private ApiManager mApiManager;
    private SharedPrefsManager mSharedPrefsManager;
    private Gson mGson;

    public IMainActivityRepository(ApiManager apiManager, SharedPrefsManager sharedPrefsManager, Gson gson) {
        mApiManager = apiManager;
        mSharedPrefsManager = sharedPrefsManager;
        mGson = gson;
    }

    @Override
    public Single<List<CoinDetails>> ticker() {
        if (getLastCheckedAt() > 180 && mSharedPrefsManager.hasKey("prices")) {
            try {
                return Single.just(populateList(new JSONObject(mSharedPrefsManager.getString("prices"))));
            } catch (JSONException e) {
                return Single.error(e);
            }
        } else {
            return mApiManager.ticker().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .flatMap(jsonObject -> {

                        mSharedPrefsManager.putStringValue("prices", jsonObject.toString());
                        return Single.just(populateList(jsonObject));
                    }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
        }
    }

    List<CoinDetails> populateList(JSONObject jsonObject) throws JSONException {

        List<Coin> coinsList = new ArrayList<>();
        List<CoinDetails> coinDetailsList = new ArrayList<>();
        JSONObject statsObject = jsonObject.getJSONObject("stats");
        Iterator<String> baseCurrencyKeys = statsObject.keys();
        mSharedPrefsManager.putLongValue("lastCheckedAt", Calendar.getInstance().getTimeInMillis());
        CoinDetails coinDetails;
        while (baseCurrencyKeys.hasNext()) {
            coinDetails = new CoinDetails();
            coinsList = new ArrayList<>();
            String baseCurrency = baseCurrencyKeys.next();
            JSONObject baseCurrencyJsonObject = statsObject.getJSONObject(baseCurrency);
            Iterator<String> keys = baseCurrencyJsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Coin coin = mGson.fromJson(baseCurrencyJsonObject.getJSONObject(key).toString(), Coin.class);
                coinsList.add(coin);
            }
            coinDetails.setBaseCurrency(baseCurrency);
            coinDetails.setCoinList(coinsList);
            coinDetailsList.add(coinDetails);
        }
        return coinDetailsList;
    }

    @Override
    public Long getLastCheckedAt() {
        return (Calendar.getInstance().getTimeInMillis() - mSharedPrefsManager.getLongValue("lastCheckedAt", 0)) / 1000;
    }
}
