package com.taxi;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class TestAggregator implements TaxiAggregator {
    String name = ("Такси Быстро и дешево");

    @Override
    public TaxiVariant findTaxiVariant(String from, String to) {
        UUID uuidTaxi = UUID.randomUUID();
        TaxiVariant taxiVariant = new TaxiVariant();
        taxiVariant.idVariant = uuidTaxi;
        taxiVariant.name = name;
        taxiVariant.from = "MSK";
        taxiVariant.to = "NSK";
        taxiVariant.price = 1000.00;
        taxiVariant.distance = 4020.5;
        return taxiVariant;
    }
//    @Override
//    public boolean findTaxiAggregator(String uuid){
//        if (uuidTaxi.toString().equals(uuid)) {
//            return false;
//        } else {
//            return true;
//        }
//    }
    @Override
    public boolean getTaxi(){
        return true;
    }
    @Override
    public String getName(){
        return name;
    }
}
