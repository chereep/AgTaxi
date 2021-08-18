package com.taxi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class TaxiService {
    private final List<TaxiAggregator> taxiAggregators;
    private final List<List<TaxiVariant>> fullList = new ArrayList<>();


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

    String reservation(UUID reservationId, int findId){
        List<TaxiVariant> taxiVariants;
        String findName;
        String answerSt;
        Answer answerOj = new Answer();

        taxiVariants = fullList.get(findId);
        TaxiVariant taxiVariant = taxiVariants
                .stream()
                .filter((x) -> x.idVariant.equals(reservationId))
                .findFirst()
                .get();
        findName = taxiVariant.name;
        boolean status = taxiAggregators
                .stream()
                .filter((x) -> x.getName().equals(findName))
                .map(TaxiAggregator::getTaxi).findFirst().orElse(false);
        answerSt = answerOj.getAnswer(taxiVariant,status);
        return answerSt;
    }

}