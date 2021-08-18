package com.taxi;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class TestAggregator implements TaxiAggregator {
    String name = ("Такси Быстро и дешево");

    @Override
    public TaxiVariantDTO findTaxiVariant(String from, String to) {
        UUID uuidTaxi = UUID.randomUUID();
        TaxiVariantDTO taxiVariantDTO = new TaxiVariantDTO();
        taxiVariantDTO.idVariant = uuidTaxi;
        taxiVariantDTO.name = name;
        taxiVariantDTO.from = "MSK";
        taxiVariantDTO.to = "NSK";
        taxiVariantDTO.price = 1000.00;
        taxiVariantDTO.distance = 4020.5;
        return taxiVariantDTO;
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
