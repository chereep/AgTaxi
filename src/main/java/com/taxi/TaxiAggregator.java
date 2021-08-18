package com.taxi;

public interface TaxiAggregator {
    TaxiVariantDTO findTaxiVariant(String from, String to);
    boolean getTaxi();
    String getName();
}