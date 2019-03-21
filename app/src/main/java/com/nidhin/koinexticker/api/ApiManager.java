package com.nidhin.koinexticker.api;

import com.nidhin.koinexticker.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {

    protected ApiService service;

    public ApiManager(ApiService apiService) {
        this.service = apiService;
    }


    public Single<JSONObject> ticker() {
//        if (BuildConfig.DEBUG)
//            try {
//                return Single.just(new JSONObject(mockResponse));
//            } catch (Exception e) {
//                return Single.error(e);
//            }
//        else
            return Single.create(e -> {
                final SingleEmitter emitter = e;
                service.ticker().enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            try {
                                emitter.onSuccess(new JSONObject(response.body()));
                            } catch (JSONException e1) {
                                emitter.onError(e1);
                            }
                        } else emitter.onError(new Exception());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            });

    }


    String mockResponse = "{ \n" +
            "\t\"stats\": {\n" +
            "\t\t\"inr\": {\n" +
            "\t\t\t\"ETH\": {\n" +
            "\t\t\t\t\"highest_bid\": \"9650.05\",\n" +
            "\t\t\t\t\"lowest_ask\": \"9843.97\",\n" +
            "\t\t\t\t\"last_traded_price\": \"9650.05\",\n" +
            "\t\t\t\t\"min_24hrs\": \"9650.05\",\n" +
            "\t\t\t\t\"max_24hrs\": \"9844.98\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"32.604\",\n" +
            "\t\t\t\t\"currency_full_form\": \"ether\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ETH\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-1.5097004787726445\",\n" +
            "\t\t\t\t\"trade_volume\": \"177704.29611000002\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BTC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"283000\",\n" +
            "\t\t\t\t\"lowest_ask\": \"285200\",\n" +
            "\t\t\t\t\"last_traded_price\": \"283000\",\n" +
            "\t\t\t\t\"min_24hrs\": \"281500.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"285200.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"5.6168\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bitcoin\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BTC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-0.7017543859649122\",\n" +
            "\t\t\t\t\"trade_volume\": \"989482.5539519999\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"LTC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"4118.01\",\n" +
            "\t\t\t\t\"lowest_ask\": \"4240\",\n" +
            "\t\t\t\t\"last_traded_price\": \"4245\",\n" +
            "\t\t\t\t\"min_24hrs\": \"4103.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"4248.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"35.016\",\n" +
            "\t\t\t\t\"currency_full_form\": \"litecoin\",\n" +
            "\t\t\t\t\"currency_short_form\": \"LTC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"2.907594071358952\",\n" +
            "\t\t\t\t\"trade_volume\": \"148417.6682999999\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"XRP\": {\n" +
            "\t\t\t\t\"highest_bid\": \"22.21\",\n" +
            "\t\t\t\t\"lowest_ask\": \"22.34\",\n" +
            "\t\t\t\t\"last_traded_price\": \"22.35\",\n" +
            "\t\t\t\t\"min_24hrs\": \"22.1\",\n" +
            "\t\t\t\t\"max_24hrs\": \"22.44\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"78567.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"ripple\",\n" +
            "\t\t\t\t\"currency_short_form\": \"XRP\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0.721045515998198\",\n" +
            "\t\t\t\t\"trade_volume\": \"1467319.39\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"OMG\": {\n" +
            "\t\t\t\t\"highest_bid\": \"111.99\",\n" +
            "\t\t\t\t\"lowest_ask\": \"113.99\",\n" +
            "\t\t\t\t\"last_traded_price\": \"112\",\n" +
            "\t\t\t\t\"min_24hrs\": \"104.06\",\n" +
            "\t\t\t\t\"max_24hrs\": \"114.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"41.62\",\n" +
            "\t\t\t\t\"currency_full_form\": \"omisego\",\n" +
            "\t\t\t\t\"currency_short_form\": \"OMG\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"5.660377358490566\",\n" +
            "\t\t\t\t\"trade_volume\": \"4602.58085\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"REQ\": {\n" +
            "\t\t\t\t\"highest_bid\": \"1.86\",\n" +
            "\t\t\t\t\"lowest_ask\": \"1.87\",\n" +
            "\t\t\t\t\"last_traded_price\": \"1.86\",\n" +
            "\t\t\t\t\"min_24hrs\": \"1.82\",\n" +
            "\t\t\t\t\"max_24hrs\": \"1.86\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"4636.031\",\n" +
            "\t\t\t\t\"currency_full_form\": \"request\",\n" +
            "\t\t\t\t\"currency_short_form\": \"REQ\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"2.1978021978021998\",\n" +
            "\t\t\t\t\"trade_volume\": \"8599.41766\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ZRX\": {\n" +
            "\t\t\t\t\"highest_bid\": \"18.7\",\n" +
            "\t\t\t\t\"lowest_ask\": \"19\",\n" +
            "\t\t\t\t\"last_traded_price\": \"18.7\",\n" +
            "\t\t\t\t\"min_24hrs\": \"18.7\",\n" +
            "\t\t\t\t\"max_24hrs\": \"18.73\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"68.337\",\n" +
            "\t\t\t\t\"currency_full_form\": \"zerox\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ZRX\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-0.16017084890550526\",\n" +
            "\t\t\t\t\"trade_volume\": \"1279.16408\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"GNT\": {\n" +
            "\t\t\t\t\"highest_bid\": \"5.51\",\n" +
            "\t\t\t\t\"lowest_ask\": \"5.99\",\n" +
            "\t\t\t\t\"last_traded_price\": \"5.8\",\n" +
            "\t\t\t\t\"min_24hrs\": \"5.47\",\n" +
            "\t\t\t\t\"max_24hrs\": \"6.4\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"8922.753\",\n" +
            "\t\t\t\t\"currency_full_form\": \"golem\",\n" +
            "\t\t\t\t\"currency_short_form\": \"GNT\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"6.0329067641681915\",\n" +
            "\t\t\t\t\"trade_volume\": \"52083.3622\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BAT\": {\n" +
            "\t\t\t\t\"highest_bid\": \"13.1\",\n" +
            "\t\t\t\t\"lowest_ask\": \"13.85\",\n" +
            "\t\t\t\t\"last_traded_price\": \"13.81\",\n" +
            "\t\t\t\t\"min_24hrs\": \"13.25\",\n" +
            "\t\t\t\t\"max_24hrs\": \"13.81\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"2362.844\",\n" +
            "\t\t\t\t\"currency_full_form\": \"basic_attention_token\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BAT\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"3.834586466165412\",\n" +
            "\t\t\t\t\"trade_volume\": \"31497.789050000003\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"AE\": {\n" +
            "\t\t\t\t\"highest_bid\": \"32\",\n" +
            "\t\t\t\t\"lowest_ask\": \"34.4\",\n" +
            "\t\t\t\t\"last_traded_price\": \"34.4\",\n" +
            "\t\t\t\t\"min_24hrs\": \"31.58\",\n" +
            "\t\t\t\t\"max_24hrs\": \"34.4\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"3469.043\",\n" +
            "\t\t\t\t\"currency_full_form\": \"aeternity\",\n" +
            "\t\t\t\t\"currency_short_form\": \"AE\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"8.929702343255226\",\n" +
            "\t\t\t\t\"trade_volume\": \"118332.15349999999\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"TRX\": {\n" +
            "\t\t\t\t\"highest_bid\": \"1.59\",\n" +
            "\t\t\t\t\"lowest_ask\": \"1.6\",\n" +
            "\t\t\t\t\"last_traded_price\": \"1.61\",\n" +
            "\t\t\t\t\"min_24hrs\": \"1.58\",\n" +
            "\t\t\t\t\"max_24hrs\": \"1.61\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"336147.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"tron\",\n" +
            "\t\t\t\t\"currency_short_form\": \"TRX\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0.6250000000000006\",\n" +
            "\t\t\t\t\"trade_volume\": \"535509.2600000001\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"XLM\": {\n" +
            "\t\t\t\t\"highest_bid\": \"7.8\",\n" +
            "\t\t\t\t\"lowest_ask\": \"7.96\",\n" +
            "\t\t\t\t\"last_traded_price\": \"7.96\",\n" +
            "\t\t\t\t\"min_24hrs\": \"7.65\",\n" +
            "\t\t\t\t\"max_24hrs\": \"8.22\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"13345.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"stellar\",\n" +
            "\t\t\t\t\"currency_short_form\": \"XLM\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"1.4012738853503226\",\n" +
            "\t\t\t\t\"trade_volume\": \"103262.0899999999\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"NEO\": {\n" +
            "\t\t\t\t\"highest_bid\": \"642.02\",\n" +
            "\t\t\t\t\"lowest_ask\": \"675\",\n" +
            "\t\t\t\t\"last_traded_price\": \"675\",\n" +
            "\t\t\t\t\"min_24hrs\": \"674.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"675.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"20.346\",\n" +
            "\t\t\t\t\"currency_full_form\": \"neo\",\n" +
            "\t\t\t\t\"currency_short_form\": \"NEO\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"13733.250000000002\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"GAS\": {\n" +
            "\t\t\t\t\"highest_bid\": \"190.1\",\n" +
            "\t\t\t\t\"lowest_ask\": \"198.99\",\n" +
            "\t\t\t\t\"last_traded_price\": \"190.5\",\n" +
            "\t\t\t\t\"min_24hrs\": \"188.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"201.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"576.31\",\n" +
            "\t\t\t\t\"currency_full_form\": \"gas\",\n" +
            "\t\t\t\t\"currency_short_form\": \"GAS\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"1.3297872340425532\",\n" +
            "\t\t\t\t\"trade_volume\": \"111233.70434\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"XRB\": {\n" +
            "\t\t\t\t\"highest_bid\": \"68.03\",\n" +
            "\t\t\t\t\"lowest_ask\": \"72.8\",\n" +
            "\t\t\t\t\"last_traded_price\": \"68.03\",\n" +
            "\t\t\t\t\"min_24hrs\": \"68.03\",\n" +
            "\t\t\t\t\"max_24hrs\": \"72.89\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"27.17\",\n" +
            "\t\t\t\t\"currency_full_form\": \"nano\",\n" +
            "\t\t\t\t\"currency_short_form\": \"XRB\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-6.654774972557622\",\n" +
            "\t\t\t\t\"trade_volume\": \"1975.4918799999998\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"NCASH\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.12\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.13\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.13\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.12\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.13\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"414485.2\",\n" +
            "\t\t\t\t\"currency_full_form\": \"nucleus_vision\",\n" +
            "\t\t\t\t\"currency_short_form\": \"NCASH\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"53792.96799999999\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"EOS\": {\n" +
            "\t\t\t\t\"highest_bid\": \"257.01\",\n" +
            "\t\t\t\t\"lowest_ask\": \"262\",\n" +
            "\t\t\t\t\"last_traded_price\": \"256.07\",\n" +
            "\t\t\t\t\"min_24hrs\": \"255.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"264.94\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"92.06\",\n" +
            "\t\t\t\t\"currency_full_form\": \"eos\",\n" +
            "\t\t\t\t\"currency_short_form\": \"EOS\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"23910.1717\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"CMT\": {\n" +
            "\t\t\t\t\"highest_bid\": \"2.3\",\n" +
            "\t\t\t\t\"lowest_ask\": \"2.55\",\n" +
            "\t\t\t\t\"last_traded_price\": \"2.55\",\n" +
            "\t\t\t\t\"min_24hrs\": \"2.42\",\n" +
            "\t\t\t\t\"max_24hrs\": \"2.55\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"5505.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"cyber_miles\",\n" +
            "\t\t\t\t\"currency_short_form\": \"CMT\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"5.371900826446277\",\n" +
            "\t\t\t\t\"trade_volume\": \"13546.030000000002\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ONT\": {\n" +
            "\t\t\t\t\"highest_bid\": \"85\",\n" +
            "\t\t\t\t\"lowest_ask\": \"97.99\",\n" +
            "\t\t\t\t\"last_traded_price\": \"99.99\",\n" +
            "\t\t\t\t\"min_24hrs\": \"79.62\",\n" +
            "\t\t\t\t\"max_24hrs\": \"99.99\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"1180.878\",\n" +
            "\t\t\t\t\"currency_full_form\": \"ontology\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ONT\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"25.58402411454407\",\n" +
            "\t\t\t\t\"trade_volume\": \"100469.46619\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ZIL\": {\n" +
            "\t\t\t\t\"highest_bid\": \"1.31\",\n" +
            "\t\t\t\t\"lowest_ask\": \"1.33\",\n" +
            "\t\t\t\t\"last_traded_price\": \"1.33\",\n" +
            "\t\t\t\t\"min_24hrs\": \"1.27\",\n" +
            "\t\t\t\t\"max_24hrs\": \"1.33\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"7855.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"zilliqa\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ZIL\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"4.724409448818902\",\n" +
            "\t\t\t\t\"trade_volume\": \"10267.260000000002\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"IOST\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.56\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.61\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.6\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.53\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.64\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"1090970.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"ios_token\",\n" +
            "\t\t\t\t\"currency_short_form\": \"IOST\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"13.20754716981131\",\n" +
            "\t\t\t\t\"trade_volume\": \"648788.32\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ACT\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.79\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.81\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.79\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.77\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.84\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"26212.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"achain\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ACT\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-3.6585365853658436\",\n" +
            "\t\t\t\t\"trade_volume\": \"21214.829999999998\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ZCO\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.42\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.43\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.42\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.41\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.42\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"34922.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"zebi\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ZCO\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"14449.939999999999\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"SNT\": {\n" +
            "\t\t\t\t\"highest_bid\": \"1.55\",\n" +
            "\t\t\t\t\"lowest_ask\": \"1.8\",\n" +
            "\t\t\t\t\"last_traded_price\": \"1.8\",\n" +
            "\t\t\t\t\"min_24hrs\": \"1.8\",\n" +
            "\t\t\t\t\"max_24hrs\": \"1.8\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"500.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"status\",\n" +
            "\t\t\t\t\"currency_short_form\": \"SNT\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"900.0\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"POLY\": {\n" +
            "\t\t\t\t\"highest_bid\": \"7.02\",\n" +
            "\t\t\t\t\"lowest_ask\": \"7.2\",\n" +
            "\t\t\t\t\"last_traded_price\": \"7.06\",\n" +
            "\t\t\t\t\"min_24hrs\": \"7.06\",\n" +
            "\t\t\t\t\"max_24hrs\": \"7.21\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"1258.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"polymath\",\n" +
            "\t\t\t\t\"currency_short_form\": \"POLY\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-1.9444444444444522\",\n" +
            "\t\t\t\t\"trade_volume\": \"9043.85\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ELF\": {\n" +
            "\t\t\t\t\"highest_bid\": \"11.78\",\n" +
            "\t\t\t\t\"lowest_ask\": \"12.57\",\n" +
            "\t\t\t\t\"last_traded_price\": \"12.57\",\n" +
            "\t\t\t\t\"min_24hrs\": \"11.26\",\n" +
            "\t\t\t\t\"max_24hrs\": \"12.59\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"385.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"aelf\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ELF\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-0.15885623510722457\",\n" +
            "\t\t\t\t\"trade_volume\": \"4660.33\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"REP\": {\n" +
            "\t\t\t\t\"highest_bid\": \"1005\",\n" +
            "\t\t\t\t\"lowest_ask\": \"1046.97\",\n" +
            "\t\t\t\t\"last_traded_price\": \"1010\",\n" +
            "\t\t\t\t\"min_24hrs\": \"1010.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"1010.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"0.5\",\n" +
            "\t\t\t\t\"currency_full_form\": \"augur\",\n" +
            "\t\t\t\t\"currency_short_form\": \"REP\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"505.0\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"QKC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"2.5\",\n" +
            "\t\t\t\t\"lowest_ask\": \"2.59\",\n" +
            "\t\t\t\t\"last_traded_price\": \"2.59\",\n" +
            "\t\t\t\t\"min_24hrs\": \"2.59\",\n" +
            "\t\t\t\t\"max_24hrs\": \"2.61\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"3182.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"quarkchain\",\n" +
            "\t\t\t\t\"currency_short_form\": \"QKC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-0.7662835249042153\",\n" +
            "\t\t\t\t\"trade_volume\": \"8266.48\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"XZC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"458\",\n" +
            "\t\t\t\t\"lowest_ask\": \"495\",\n" +
            "\t\t\t\t\"last_traded_price\": \"495\",\n" +
            "\t\t\t\t\"min_24hrs\": \"455.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"495.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"43.98\",\n" +
            "\t\t\t\t\"currency_full_form\": \"zcoin\",\n" +
            "\t\t\t\t\"currency_short_form\": \"XZC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"6.223175965665236\",\n" +
            "\t\t\t\t\"trade_volume\": \"20295.3\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BCHABC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"10816\",\n" +
            "\t\t\t\t\"lowest_ask\": \"11200\",\n" +
            "\t\t\t\t\"last_traded_price\": \"11200\",\n" +
            "\t\t\t\t\"min_24hrs\": \"10802.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"11200.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"2.4164\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bitcoin_cash_abc\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BCHABC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"3.6823912121767624\",\n" +
            "\t\t\t\t\"trade_volume\": \"26778.274\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"TUSD\": {\n" +
            "\t\t\t\t\"highest_bid\": \"70.5\",\n" +
            "\t\t\t\t\"lowest_ask\": \"71.36\",\n" +
            "\t\t\t\t\"last_traded_price\": \"70.5\",\n" +
            "\t\t\t\t\"min_24hrs\": \"70.5\",\n" +
            "\t\t\t\t\"max_24hrs\": \"71.39\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"201.61\",\n" +
            "\t\t\t\t\"currency_full_form\": \"true_usd\",\n" +
            "\t\t\t\t\"currency_short_form\": \"TUSD\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"-0.704225352112676\",\n" +
            "\t\t\t\t\"trade_volume\": \"14339.5377\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BCHSV\": {\n" +
            "\t\t\t\t\"highest_bid\": \"4651\",\n" +
            "\t\t\t\t\"lowest_ask\": \"4800\",\n" +
            "\t\t\t\t\"last_traded_price\": \"4650\",\n" +
            "\t\t\t\t\"min_24hrs\": \"4650.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"4650.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"0.862\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bitcoin_cash_sv\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BCHSV\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"4008.2999999999997\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BTT\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.05\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.06\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.06\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.05\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.06\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"541233.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bit_torrent\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BTT\",\n" +
            "\t\t\t\t\"baseCurrency\": \"inr\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"31778.729999999996\"\n" +
            "\t\t\t}\n" +
            "\t\t},\n" +
            "\t\t\"true_usd\": {\n" +
            "\t\t\t\"XRP\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.3116\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.3164\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.3164\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.3129\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.32\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"2610.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"ripple\",\n" +
            "\t\t\t\t\"currency_short_form\": \"XRP\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0.8606949314631929\",\n" +
            "\t\t\t\t\"trade_volume\": \"821.4052000000001\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"TRX\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.0225\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.0227\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.0225\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.0224\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.0228\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"20119.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"tron\",\n" +
            "\t\t\t\t\"currency_short_form\": \"TRX\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"454.42789999999997\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BTC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"4016.355\",\n" +
            "\t\t\t\t\"lowest_ask\": \"4043.3404\",\n" +
            "\t\t\t\t\"last_traded_price\": \"4016.6975\",\n" +
            "\t\t\t\t\"min_24hrs\": \"3965.1345\",\n" +
            "\t\t\t\t\"max_24hrs\": \"4028.207\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"0.2144\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bitcoin\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BTC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"-0.13697703773076975\",\n" +
            "\t\t\t\t\"trade_volume\": \"855.3842745900001\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ETH\": {\n" +
            "\t\t\t\t\"highest_bid\": \"137.5047\",\n" +
            "\t\t\t\t\"lowest_ask\": \"139.3052\",\n" +
            "\t\t\t\t\"last_traded_price\": \"137.5047\",\n" +
            "\t\t\t\t\"min_24hrs\": \"136.2451\",\n" +
            "\t\t\t\t\"max_24hrs\": \"140.69\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"4.022\",\n" +
            "\t\t\t\t\"currency_full_form\": \"ether\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ETH\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"-1.0009006810185204\",\n" +
            "\t\t\t\t\"trade_volume\": \"552.9027689\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"NCASH\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.0014\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.0019\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.0019\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.0019\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.0019\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"910.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"nucleus_vision\",\n" +
            "\t\t\t\t\"currency_short_form\": \"NCASH\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"1.729\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BCHABC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"65\",\n" +
            "\t\t\t\t\"lowest_ask\": \"177.88\",\n" +
            "\t\t\t\t\"last_traded_price\": \"127.88\",\n" +
            "\t\t\t\t\"min_24hrs\": \"127.88\",\n" +
            "\t\t\t\t\"max_24hrs\": \"127.88\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"0.009\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bitcoin_cash_abc\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BCHABC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"1.15092\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"LTC\": {\n" +
            "\t\t\t\t\"highest_bid\": \"40.1\",\n" +
            "\t\t\t\t\"lowest_ask\": \"65\",\n" +
            "\t\t\t\t\"last_traded_price\": \"61\",\n" +
            "\t\t\t\t\"min_24hrs\": \"61.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"61.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"1.1\",\n" +
            "\t\t\t\t\"currency_full_form\": \"litecoin\",\n" +
            "\t\t\t\t\"currency_short_form\": \"LTC\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"67.1\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ZIL\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.0102\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.0218\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.0178\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.0178\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.0178\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"336.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"zilliqa\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ZIL\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"5.9808\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BCHSV\": {\n" +
            "\t\t\t\t\"highest_bid\": \"39\",\n" +
            "\t\t\t\t\"lowest_ask\": \"104\",\n" +
            "\t\t\t\t\"last_traded_price\": \"74\",\n" +
            "\t\t\t\t\"min_24hrs\": \"74.0\",\n" +
            "\t\t\t\t\"max_24hrs\": \"74.0\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"0.04\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bitcoin_cash_sv\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BCHSV\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"2.96\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"XLM\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.055\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.1473\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.106\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.106\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.106\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"48.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"stellar\",\n" +
            "\t\t\t\t\"currency_short_form\": \"XLM\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"5.088\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ZRX\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.15\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.42\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.29\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.29\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.29\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"120.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"zerox\",\n" +
            "\t\t\t\t\"currency_short_form\": \"ZRX\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"34.8\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"BTT\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.0009\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0.001\",\n" +
            "\t\t\t\t\"last_traded_price\": \"0.001\",\n" +
            "\t\t\t\t\"min_24hrs\": \"0.001\",\n" +
            "\t\t\t\t\"max_24hrs\": \"0.001\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"304.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"bit_torrent\",\n" +
            "\t\t\t\t\"currency_short_form\": \"BTT\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"0.304\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"USDT\": {\n" +
            "\t\t\t\t\"highest_bid\": \"0.901\",\n" +
            "\t\t\t\t\"lowest_ask\": \"0\",\n" +
            "\t\t\t\t\"last_traded_price\": \"1.0042\",\n" +
            "\t\t\t\t\"min_24hrs\": \"1.0042\",\n" +
            "\t\t\t\t\"max_24hrs\": \"1.0042\",\n" +
            "\t\t\t\t\"vol_24hrs\": \"10.0\",\n" +
            "\t\t\t\t\"currency_full_form\": \"tether\",\n" +
            "\t\t\t\t\"currency_short_form\": \"USDT\",\n" +
            "\t\t\t\t\"baseCurrency\": \"true_usd\",\n" +
            "\t\t\t\t\"per_change\": \"0\",\n" +
            "\t\t\t\t\"trade_volume\": \"10.042\"\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

}
