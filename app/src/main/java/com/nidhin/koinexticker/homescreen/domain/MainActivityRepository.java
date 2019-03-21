package com.nidhin.koinexticker.homescreen.domain;

import com.nidhin.koinexticker.homescreen.data.CoinDetails;

import java.util.List;

import io.reactivex.Single;

public interface MainActivityRepository {

    Single<List<CoinDetails>> ticker();
    Long getLastCheckedAt();

}
