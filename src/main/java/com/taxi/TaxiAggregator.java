package com.taxi;

public interface TaxiAggregator {
    TaxiVariant findTaxiVariant(String from, String to);
}
