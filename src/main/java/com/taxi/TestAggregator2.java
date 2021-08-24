package com.taxi;

import org.springframework.stereotype.Service;

@Service
public class TestAggregator2 implements TaxiAggregator {
    String name = ("Такси как в Лондоне");
    TestAggregatorRandomUuidGenerator2 testAggregatorRandomUuidGenerator2;

    TestAggregator2(TestAggregatorRandomUuidGenerator2 testAggregatorRandomUuidGenerator2) {
        this.testAggregatorRandomUuidGenerator2 = testAggregatorRandomUuidGenerator2;
    }

    @Override
    public TaxiVariantDTO findTaxiVariant(String from, String to) {
        TestAggregatorRandomUuidGenerator testAggregatorRandomUuidGenerator = null;
        TaxiVariantDTO taxiVariantDTO = new TaxiVariantDTO();
        taxiVariantDTO.idVariant = testAggregatorRandomUuidGenerator2.getRandomId();
        taxiVariantDTO.name = name;
        taxiVariantDTO.from = "MSK";
        taxiVariantDTO.to = "NSK";
        taxiVariantDTO.price = 1030.00;
        taxiVariantDTO.distance = 4050.0;
        return taxiVariantDTO;
    }

    @Override
    public boolean getTaxi(){
        return true;
    }
    @Override
    public String getName(){
        return name;
    }
}
