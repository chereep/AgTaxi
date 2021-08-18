package com.taxi;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestAggregator2 implements TaxiAggregator {
    String name = ("Такси как в Лондоне");

    @Override
    public TaxiVariant findTaxiVariant(String from, String to) {
        UUID uuidTaxi = UUID.randomUUID();
        TaxiVariant taxiVariant = new TaxiVariant();
        taxiVariant.idVariant = uuidTaxi;
        taxiVariant.name = name;
        taxiVariant.from = "MSK";
        taxiVariant.to = "NSK";
        taxiVariant.price = 1030.00;
        taxiVariant.distance = 4050.0;
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
