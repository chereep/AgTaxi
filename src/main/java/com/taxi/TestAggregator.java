package com.taxi;

import org.springframework.stereotype.Service;

@Service

public class TestAggregator implements TaxiAggregator {

    @Override
    public TaxiVariant findTaxiVariant(String from, String to) {
        TaxiVariant taxiVariant = new TaxiVariant();
        taxiVariant.from = "MSK";
        taxiVariant.to = "NSK";
        taxiVariant.price = 1000.00;
        taxiVariant.distance = 4020.5;
        return taxiVariant;
    }
}