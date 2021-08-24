package com.taxi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaxiRequestRepository {
    private final List<List<TaxiVariantDTO>> requestsTaxi = new ArrayList<>();

    public int save(List<TaxiVariantDTO> taxiVariants) {
        requestsTaxi.add(taxiVariants);
        return requestsTaxi.size() - 1;
    }

    public List<TaxiVariantDTO> findById(int id) {
        return requestsTaxi.get(id);
    }
}
