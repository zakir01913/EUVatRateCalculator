package com.zakir.euvatcalculation.domain.model;

import java.util.Date;
import java.util.List;

public class VatPeriod {
    Date effectFrom;
    List<VatTypeRate> vatTypeRates;

    public Date getEffectFrom() {
        return effectFrom;
    }

    public void setEffectFrom(Date effectFrom) {
        this.effectFrom = effectFrom;
    }

    public List<VatTypeRate> getVatTypeRates() {
        return vatTypeRates;
    }

    public void setVatTypeRates(List<VatTypeRate> vatTypeRates) {
        this.vatTypeRates = vatTypeRates;
    }
}
