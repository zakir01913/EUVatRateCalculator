package com.zakir.euvatcalculation.data.remote;

import com.zakir.euvatcalculation.data.remote.model.EUVatRateCollection;
import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.utils.EUVatRateCollectionUtils;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class EUVatRateDataMapperTest {
    EUVatRateDataMapper euVatRateDataMapper;

    @Before
    public void setup() {
        euVatRateDataMapper = new EUVatRateDataMapper();
    }

    @Test
    public void convertToDomain_withEmptyEUVatRateCollection_returnEmptyListOfCountryVatRate() throws Exception {
        EUVatRateCollection euVatRateCollection = EUVatRateCollectionUtils.getEmptyEUVatRateCollection();
        List<CountryVatRate> countryVatRates = euVatRateDataMapper.convertToDomain(euVatRateCollection);

        assertThat(countryVatRates.size(), is(comparesEqualTo(0)));
    }

    @Test
    public void convertToDomain_withNull_returnEmptyListOfCountryVatRate() throws Exception {

        List<CountryVatRate> countryVatRates = euVatRateDataMapper.convertToDomain(null);

        assertThat(countryVatRates.size(), is(comparesEqualTo(0)));
    }

    @Test
    public void convertToDomain_withValidEUVatRateCollection_returnListOfCountryVatRate() throws Exception {
        EUVatRateCollection euVatRateCollection = EUVatRateCollectionUtils.getEUVatRateCollectionWithTwoRates();

        List<CountryVatRate> countryVatRates = euVatRateDataMapper.convertToDomain(euVatRateCollection);

        assertThat(countryVatRates.size(), is(comparesEqualTo(2)));
        CountryVatRate countryVatRate = countryVatRates.get(0);
        assertThat(countryVatRate.getCountryName(), is(equalTo(euVatRateCollection.getCountryVatRateData().get(0).getCountryName())));
        assertThat(countryVatRate.getVatPeriods().size(), is(comparesEqualTo(1)));
        assertThat(countryVatRate.getVatPeriods().get(0).getVatTypeRates().size(), is(comparesEqualTo(3)));

    }
}