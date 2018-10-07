package com.zakir.euvatcalculation.utils;

import com.google.gson.Gson;
import com.zakir.euvatcalculation.data.remote.model.EUVatRateCollection;

public class EUVatRateCollectionUtils {

    private static String euVatRateJSON = "{\n" +
            "  'details': 'http://github.com/adamcooke/vat-rates',\n" +
            "  'version': null,\n" +
            "  'rates': [\n" +
            "    {\n" +
            "      'name': 'Spain',\n" +
            "      'code': 'ES',\n" +
            "      'country_code': 'ES',\n" +
            "      'periods': [\n" +
            "        {\n" +
            "          'effective_from': '0000-01-01',\n" +
            "          'rates': {\n" +
            "            'super_reduced': 4,\n" +
            "            'reduced': 10,\n" +
            "            'standard': 21\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      'name': 'Hungary',\n" +
            "      'code': 'HU',\n" +
            "      'country_code': 'HU',\n" +
            "      'periods': [\n" +
            "        {\n" +
            "          'effective_from': '0000-01-01',\n" +
            "          'rates': {\n" +
            "            'reduced1': 5,\n" +
            "            'reduced2': 18,\n" +
            "            'standard': 27\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            " ]\n" +
            "}";

    private static String euVatRateEmptyJSON = "{\n" +
            "  'details': 'http://github.com/adamcooke/vat-rates',\n" +
            "  'version': null,\n" +
            "  'rates': []\n" +
            "}";

    public static EUVatRateCollection getEmptyEUVatRateCollection() {
        return new Gson().fromJson(euVatRateEmptyJSON, EUVatRateCollection.class);
    }

    public static EUVatRateCollection getEUVatRateCollectionWithTwoRates() {
        return new Gson().fromJson(euVatRateJSON, EUVatRateCollection.class);
    }
}
