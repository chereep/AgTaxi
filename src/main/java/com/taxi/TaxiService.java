package com.taxi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
class TaxiService {
    private final List<TaxiAggregator> taxiAggregators;
    private List<List<TaxiVariant>> fullList = new ArrayList<>();


    public TaxiService(List<TaxiAggregator> taxiAggregators){
        this.taxiAggregators = taxiAggregators;
    }
    int findTaxiVariants(String from, String to) {
        List<TaxiVariant> foundTaxiVariants = taxiAggregators
                .stream()
                .map(x->x.findTaxiVariant(from, to))
                .collect(Collectors.toList());
        fullList.add(foundTaxiVariants);
        return fullList.size()-1;

    }
    List<TaxiVariant> findTaxiResults(int findId){

        return fullList.get(findId);
    }

}