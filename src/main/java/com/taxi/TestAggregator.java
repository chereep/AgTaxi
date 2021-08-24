package com.taxi;

import org.springframework.stereotype.Service;

@Service
public class TestAggregator implements TaxiAggregator {
    String name = ("Такси Быстро и дешево");
    private final TestAggregatorRandomUuidGenerator testAggregatorRandomUuidGenerator;

    TestAggregator(TestAggregatorRandomUuidGenerator testAggregatorRandomUuidGenerator) {
        this.testAggregatorRandomUuidGenerator = testAggregatorRandomUuidGenerator;
    }

    @Override
    public TaxiVariantDTO findTaxiVariant(String from, String to) {

        TaxiVariantDTO taxiVariant = new TaxiVariantDTO();
        taxiVariant.idVariant = testAggregatorRandomUuidGenerator.getRandomId();
        taxiVariant.name = name;
        taxiVariant.from = "MSK";
        taxiVariant.to = "NSK";
        taxiVariant.price = 1000.00;
        taxiVariant.distance = 4020.5;
        if (from.equals(taxiVariant.from) && to.equals(taxiVariant.to)){
            return taxiVariant;
        }else {
            return null;
        }
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
