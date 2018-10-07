package com.zakir.euvatcalculation.presentation;

import android.support.annotation.VisibleForTesting;

import com.zakir.euvatcalculation.domain.repository.EUCountryVatRateRepository;
import com.zakir.euvatcalculation.presentation.schedulers.BaseSchedulerProvider;

import java.text.DecimalFormat;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class VatCalculatorPresenter implements VatCalculatorContact.Presenter {

    private final EUCountryVatRateRepository EUCountryVatRateRepository;
    private final BaseSchedulerProvider baseSchedulerProvider;
    private VatCalculatorContact.View view;
    private CompositeDisposable compositeDisposable;
    private DecimalFormat decimalFormat = new DecimalFormat();

    @Inject
    public VatCalculatorPresenter(EUCountryVatRateRepository EUCountryVatRateRepository, BaseSchedulerProvider baseSchedulerProvider) {
        this.EUCountryVatRateRepository = EUCountryVatRateRepository;
        this.baseSchedulerProvider = baseSchedulerProvider;
        compositeDisposable = new CompositeDisposable();
        decimalFormat.setMaximumFractionDigits(2);
    }

    @Override
    public void setView(VatCalculatorContact.View view) {
        this.view = view;
    }

    @Override
    public void loadVatData() {
        view.showProgressLoading();
        compositeDisposable.clear();

        Disposable disposable = EUCountryVatRateRepository.getCountryVatRate()
        .subscribeOn(baseSchedulerProvider.io())
        .observeOn(baseSchedulerProvider.ui())
        .subscribe(listOfCountryVatRate -> {
            view.hideProgressLoading();
            view.updateUI(listOfCountryVatRate);
        });

        compositeDisposable.add(disposable);
    }

    @Override
    public float calculateVat(float amount, float vatPercentage) {
        String vat = decimalFormat.format(amount * vatPercentage / 100);
        return Float.parseFloat(vat);
    }

    @VisibleForTesting
    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
