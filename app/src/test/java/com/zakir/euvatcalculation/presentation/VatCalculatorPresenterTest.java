package com.zakir.euvatcalculation.presentation;

import com.zakir.euvatcalculation.data.exception.EmptyEUVatRateListException;
import com.zakir.euvatcalculation.data.exception.NetworkConnectionException;
import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.domain.model.VatTypeRate;
import com.zakir.euvatcalculation.domain.repository.EUCountryVatRateRepository;
import com.zakir.euvatcalculation.presentation.schedulers.BaseSchedulerProvider;
import com.zakir.euvatcalculation.utils.CountryVatRateTestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VatCalculatorPresenterTest {

    @Mock
    EUCountryVatRateRepository EUCountryVatRateRepository;
    @Mock
    BaseSchedulerProvider baseSchedulerProvider;
    @Mock
    VatCalculatorContact.View view;

    @Captor
    ArgumentCaptor<List<VatTypeRate>> vatTypeRateCaptor = ArgumentCaptor.forClass(List.class);


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
    public void loadVatData_onReceiveData_callViewUpdateUIMethods() {
        List<CountryVatRate> countryVatRates = CountryVatRateTestUtils.getDefaultVatData();
        when(EUCountryVatRateRepository.getCountryVatRate()).then(answer -> Flowable.just(countryVatRates));

        vatCalculatorPresenter.loadVatData();
        testScheduler.triggerActions();

        verify(view).updateCountrySpinner(ArgumentMatchers.anyList());
        verify(view).updateRateSelection(ArgumentMatchers.anyList());
        verify(view).hideProgressLoading();
    }

    @Test
    public void loadVatData_onReceiveEmptyData_callShowErrorMessage() {
        List<CountryVatRate> countryVatRates = CountryVatRateTestUtils.getEmptyList();
        when(EUCountryVatRateRepository.getCountryVatRate()).then(answer -> Flowable.just(countryVatRates));

        vatCalculatorPresenter.loadVatData();
        testScheduler.triggerActions();

        verify(view).showError(any(EmptyEUVatRateListException.class));
    }

    @Test
    public void loadVatData_onReceiveError_callShowErrorMessage() {
        when(EUCountryVatRateRepository.getCountryVatRate()).then(answer -> Flowable.error(new NetworkConnectionException()));

        vatCalculatorPresenter.loadVatData();
        testScheduler.triggerActions();

        verify(view).showError(any(NetworkConnectionException.class));
        verify(view).hideProgressLoading();
    }

    @Test
    public void loadVatData_onReceiveData_callViewHideProgressLoading() {
        vatCalculatorPresenter.loadVatData();
        testScheduler.triggerActions();

        verify(view).hideProgressLoading();
    }

    @Test
    public void onAmountChange_recalculateTax() {
        vatCalculatorPresenter.setCountryVatRates(CountryVatRateTestUtils.getDefaultVatData());

        vatCalculatorPresenter.onAmountChange(100.0);
        verify(view).updateTotalAmount(110.0);

        vatCalculatorPresenter.onAmountChange(200.75);
        verify(view).updateTotalAmount(220.82);

    }

    @Test
    public void onVatRateTypeChange_recalculateTax() {
        vatCalculatorPresenter.setCountryVatRates(CountryVatRateTestUtils.getDefaultVatData());
        vatCalculatorPresenter.setCurrentAmount(100);

        vatCalculatorPresenter.onRateTypeChange(1);
        verify(view).updateTotalAmount(120.0);

        vatCalculatorPresenter.onRateTypeChange(0);
        verify(view).updateTotalAmount(110);

    }

    @Test
    public void onCountryChange_callViewUpdateRateSelection() {
        List<CountryVatRate> countryVatRates = CountryVatRateTestUtils.getTwoEUVatRateData();
        vatCalculatorPresenter.setCountryVatRates(countryVatRates);

        vatCalculatorPresenter.onCountryChange(0);
        vatCalculatorPresenter.onCountryChange(1);

        verify(view, times(2)).updateRateSelection(vatTypeRateCaptor.capture());
        assertThat(vatTypeRateCaptor.getAllValues().get(0), is(equalTo(
                countryVatRates.get(0)
                        .getVatPeriods().get(0)
                        .getVatTypeRates()
        )));
        assertThat(vatTypeRateCaptor.getAllValues().get(1), is(equalTo(
                countryVatRates.get(1)
                        .getVatPeriods().get(0)
                        .getVatTypeRates()
        )));
    }

    @Test
    public void onCountryChange_recalculateTax() {
        List<CountryVatRate> countryVatRates = CountryVatRateTestUtils.getTwoEUVatRateData();
        vatCalculatorPresenter.setCountryVatRates(countryVatRates);
        vatCalculatorPresenter.setCurrentAmount(100);

        vatCalculatorPresenter.onCountryChange(0);
        verify(view).updateTotalAmount(104);

        vatCalculatorPresenter.onCountryChange(1);
        verify(view).updateTotalAmount(109);
    }

}