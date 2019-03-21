package com.nidhin.koinexticker.homescreen.domain;

import com.nidhin.koinexticker.UseCase;
import com.nidhin.koinexticker.homescreen.data.CoinDetails;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CheckLastCheckedAt extends UseCase<Long, Void> {
    private MainActivityRepository mHomeRepository;

    @Inject
    public CheckLastCheckedAt(MainActivityRepository homeRepository) {
        mHomeRepository = homeRepository;
    }

    @Override
    public Single<Long> buildUseCaseObservable(Void aVoid) {
        return Single.just(mHomeRepository.getLastCheckedAt()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
