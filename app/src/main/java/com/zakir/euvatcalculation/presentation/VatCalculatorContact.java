package com.zakir.euvatcalculation.presentation;

public interface VatCalculatorContact {

    interface View {

    }

    interface Presenter {
       void setView(View view);
       void loadVatData();
    }
}
