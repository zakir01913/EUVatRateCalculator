package com.zakir.euvatcalculation.presentation;

import android.support.annotation.VisibleForTesting;

import com.zakir.euvatcalculation.data.exception.EmptyEUVatRateListException;
import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.domain.repository.EUCountryVatRateRepository;
import com.zakir.euvatcalculation.presentation.schedulers.BaseSchedulerProvider;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class VatCalculatorPresenter implements VatCalculatorContact.Presenter {

    private final EUCountryVatRateRepository EUCountryVatRateRepository;
    private final BaseSchedulerProvider baseSchedulerProvider;
    private VatCalculatorContact.View view;
    private CompositeDisposable compositeDisposable;
    private DecimalFormat decimalFormat = new DecimalFormat();
    private int currentCountryIndex = 0;
    private int currentVatTypeRateIndex = 0;
    private double currentAmount = 0.0f;
    private List<CountryVatRate> countryVatRates = new ArrayList<>();

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
            countryVatRates = listOfCountryVatRate;
            if (!countryVatRates.isEmpty()) {
                currentCountryIndex = 0;
                view.updateCountrySpinner(getCountryList(countryVatRates));
                view.updateRateSelection(countryVatRates.get(currentCountryIndex).getVatPeriods().get(0).getVatTypeRates());
            } else {
                view.showError(new EmptyEUVatRateListException());
            }
            view.hideProgressLoading();
        }, throwable -> {
            view.showError(throwable);
            view.hideProgressLoading();
        });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onAmountChange(double amount) {
        currentAmount = amount;
        updateTotalAmount();
    }

    @Override
    public void onRateTypeChange(int rateType) {
        currentVatTypeRateIndex = rateType;
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        double amountWithTax = currentAmount + (currentAmount * getCurrentVatRate() / 100);
        view.updateTotalAmount(Double.parseDouble(decimalFormat.format(amountWithTax)));
    }

    private double getCurrentVatRate() {
        return countryVatRates.get(currentCountryIndex).getVatPeriods().get(0).getVatTypeRates().get(currentVatTypeRateIndex).getRate();
    }

    private List<String> getCountryList(List<CountryVatRate> countryVatRates) {
        List<String> countryList = new ArrayList<>();
        for (CountryVatRate countryVatRate : countryVatRates) {
            countryList.add(countryVatRate.getCountryName());
        }
        return countryList;
    }

    @VisibleForTesting
    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @VisibleForTesting
    public void setCountryVatRates(List<CountryVatRate> countryVatRates) {
        this.countryVatRates = countryVatRates;
    }

    @VisibleForTesting

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }
}
