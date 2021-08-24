package com.taxi;

import org.springframework.stereotype.Service;

@Service
public class TestAggregator2 implements TaxiAggregator {
    private final static String NAME = "Такси как в Лондоне";
    private final TestAggregatorRandomUuidGenerator2 testAggregatorRandomUuidGenerator2;

    TestAggregator2(TestAggregatorRandomUuidGenerator2 testAggregatorRandomUuidGenerator2) {
        this.testAggregatorRandomUuidGenerator2 = testAggregatorRandomUuidGenerator2;
    }

    @Override
    public TaxiVariantDTO findTaxiVariant(String from, String to) {
        TaxiVariantDTO taxiVariant = new TaxiVariantDTO();
        taxiVariant.idVariant = testAggregatorRandomUuidGenerator2.getRandomId();
        taxiVariant.name = NAME;
        taxiVariant.from = "MSK";
        taxiVariant.to = "NSK";
        taxiVariant.price = 1030.00;
        taxiVariant.distance = 4050.0;
        if (from.equals(taxiVariant.from) && to.equals(taxiVariant.to)) {
            return taxiVariant;
        } else {
            return null;
        }
    }

    @Override
    public boolean getTaxi(){
        return true;
    }

    @Override
    public String getName(){
        return NAME;
    }
}
