package com.nidhin.koinexticker.homescreen.data;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.nidhin.koinexticker.api.ApiManager;
import com.nidhin.koinexticker.homescreen.domain.MainActivityRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class IMainActivityRepository implements MainActivityRepository {

    private ApiManager mApiManager;
    private Gson mGson;

    public IMainActivityRepository(ApiManager apiManager, Gson gson) {
        mApiManager = apiManager;
        mGson = gson;
    }

    @Override
    public Single<List<CoinDetails>> ticker() {
        return mApiManager.ticker().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<JSONObject, SingleSource<? extends List<CoinDetails>>>() {
                    @Override
                    public SingleSource<? extends List<CoinDetails>> apply(JSONObject jsonObject) throws Exception {
                        List<CoinDetails> currencyDetailsList = new ArrayList<>();
                        JSONObject inrJsonObject = jsonObject.getJSONObject("stats").getJSONObject("inr");
                        JSONObject trueUsdJsonObject = jsonObject.getJSONObject("stats").getJSONObject("true_usd");
                        Iterator<String> keys = inrJsonObject.keys();
                        while (keys.hasNext()) {
                            String key = keys.next();


                            Coin inr = mGson.fromJson(inrJsonObject.getJSONObject(key).toString(), Coin.class);
                            Coin trueUsd=null;
                            try {
                                trueUsd = mGson.fromJson(trueUsdJsonObject.getJSONObject(key).toString(), Coin.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            CoinDetails currencyDetails = new CoinDetails(inr, trueUsd);
                            currencyDetailsList.add(currencyDetails);

                        }
                        return Single.just(currencyDetailsList);
                    }
                }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }
}
