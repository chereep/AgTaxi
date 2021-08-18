
package com.taxi;

import org.springframework.stereotype.Service;

@Service
public class TestAggregator2 implements TaxiAggregator {

    @Override
    public TaxiVariant findTaxiVariant(String from, String to) {
        TaxiVariant taxiVariant = new TaxiVariant();
        taxiVariant.from = "MSK";
        taxiVariant.to = "NSK";
        taxiVariant.price = 1030.00;
        taxiVariant.distance = 4050.0;
        return taxiVariant;
    }
}
