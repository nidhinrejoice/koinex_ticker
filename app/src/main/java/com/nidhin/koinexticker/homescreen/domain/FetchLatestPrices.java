package com.nidhin.koinexticker.homescreen.domain;

import com.nidhin.koinexticker.UseCase;
import com.nidhin.koinexticker.homescreen.data.CoinDetails;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class FetchLatestPrices extends UseCase<List<CoinDetails>, Void> {
    private MainActivityRepository mHomeRepository;

    @Inject
    public FetchLatestPrices(MainActivityRepository homeRepository) {
        mHomeRepository = homeRepository;
    }

    @Override
    public Single<List<CoinDetails>> buildUseCaseObservable(Void aVoid) {
        return mHomeRepository.ticker();
    }
}
