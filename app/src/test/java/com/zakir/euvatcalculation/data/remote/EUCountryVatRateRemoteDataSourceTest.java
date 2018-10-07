package com.zakir.euvatcalculation.data.remote;

import com.zakir.euvatcalculation.data.remote.model.EUVatRateCollection;
import com.zakir.euvatcalculation.utils.EUVatRateCollectionUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EUCountryVatRateRemoteDataSourceTest {
    EUCountryVatRateRemoteDataSource euCountryVatRateRemoteDataSource;
    @Mock
    EUVatRateApiService euVatRateApiService;
    @Mock
    EUVatRateDataMapper euVatRateDataMapper;

    @Captor
    ArgumentCaptor<EUVatRateCollection> argumentCaptor = ArgumentCaptor.forClass(EUVatRateCollection.class);
    TestSubscriber testSubscriber;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(euVatRateApiService.getEUVatRates()).thenReturn(Flowable.just(EUVatRateCollectionUtils.getEmptyEUVatRateCollection()));
        euCountryVatRateRemoteDataSource = new EUCountryVatRateRemoteDataSource(euVatRateApiService, euVatRateDataMapper);
        testSubscriber = new TestSubscriber();
    }

    @Test
    public void getCountryVatRate_shouldCallEUVatRateApiServiceGetEUVatRates() {
        euCountryVatRateRemoteDataSource.getCountryVatRate();

        verify(euVatRateApiService).getEUVatRates();
    }

    @Test
    public void getCountryVatRate_callEUVatRateDataMapperConvertToDomain() {
        EUVatRateCollection euVatRateCollection = EUVatRateCollectionUtils.getEUVatRateCollectionWithTwoRates();
        when(euVatRateApiService.getEUVatRates()).thenReturn(Flowable.just(euVatRateCollection));

        euCountryVatRateRemoteDataSource.getCountryVatRate().subscribe(testSubscriber);

        verify(euVatRateDataMapper).convertToDomain(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(equalTo(euVatRateCollection)));
    }

    

}