package com.zakir.euvatcalculation.presentation;

import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.domain.repository.EUCountryVatRateRepository;
import com.zakir.euvatcalculation.presentation.schedulers.BaseSchedulerProvider;
import com.zakir.euvatcalculation.utils.CountryVatRateTestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class VatCalculatorPresenterTest {

    @Mock
    EUCountryVatRateRepository EUCountryVatRateRepository;
    @Mock
    BaseSchedulerProvider baseSchedulerProvider;
    @Mock
    VatCalculatorContact.View view;


    VatCalculatorPresenter vatCalculatorPresenter;

    TestScheduler testScheduler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        when(baseSchedulerProvider.computation()).thenReturn(testScheduler);
        when(baseSchedulerProvider.ui()).thenReturn(testScheduler);
        when(baseSchedulerProvider.io()).thenReturn(testScheduler);
        when(EUCountryVatRateRepository.getCountryVatRate()).then(answer -> Flowable.just(CountryVatRateTestUtils.getEmptyList()));

        vatCalculatorPresenter = new VatCalculatorPresenter(EUCountryVatRateRepository, baseSchedulerProvider);
        vatCalculatorPresenter.setView(view);
    }

    @Test
    public void loadVatData_initializeObservable() {
        vatCalculatorPresenter.loadVatData();

        verify(EUCountryVatRateRepository).getCountryVatRate();
        verify(baseSchedulerProvider).io();
        verify(baseSchedulerProvider).ui();
    }

    @Test
    public void loadVatData_callViewToShowProgressLoading() {
        vatCalculatorPresenter.loadVatData();

        verify(view).showProgressLoading();
    }

    @Test
    public void loadVatData_addDisposableToCompositeDisposable() {
        vatCalculatorPresenter.loadVatData();

        assertThat(vatCalculatorPresenter.getCompositeDisposable().size(), is(comparesEqualTo(1)));
    }

    @Test
    public void loadVatData_clearOldDisposableFromCompositeDisposable() {
        vatCalculatorPresenter.loadVatData();
        vatCalculatorPresenter.loadVatData();

        assertThat(vatCalculatorPresenter.getCompositeDisposable().size(), is(comparesEqualTo(1)));
    }

    @Test
    public void loadVatData_onReceiveData_callViewUpdateUI() {
        List<CountryVatRate> countryVatRates = CountryVatRateTestUtils.getDefaultVatData();
        when(EUCountryVatRateRepository.getCountryVatRate()).then(answer -> Flowable.just(countryVatRates));

        vatCalculatorPresenter.loadVatData();
        testScheduler.triggerActions();

        verify(view).updateUI(countryVatRates);
    }

    @Test
    public void loadVatData_onReceiveData_callViewHideProgressLoading() {
        vatCalculatorPresenter.loadVatData();
        testScheduler.triggerActions();

        verify(view).hideProgressLoading();
    }

    @Test
    public void calculateVat_withNoDecimalPoint_returnValidValue() {
        float vat = vatCalculatorPresenter.calculateVat(100, 10);

       assertThat((double)vat, is(closeTo(10.00, 0.009)));
    }

    @Test
    public void calculateVat_withDecimalPoint_returnValidValue() {


        float vat = vatCalculatorPresenter.calculateVat(100.50f, 10.5f);

        assertThat((double)vat, is(closeTo(10.55, 0.009)));
    }

}