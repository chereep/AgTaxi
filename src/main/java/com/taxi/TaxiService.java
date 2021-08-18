package com.taxi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class TaxiService {
    private final List<TaxiAggregator> taxiAggregators;
    private final List<List<TaxiVariantDTO>> fullList = new ArrayList<>();


    public TaxiService(List<TaxiAggregator> taxiAggregators){
        this.taxiAggregators = taxiAggregators;
    }
    int findTaxiVariants(String from, String to) {
        List<TaxiVariantDTO> foundTaxiVariantDTOS = taxiAggregators
                .stream()
                .map(x->x.findTaxiVariant(from, to))
                .collect(Collectors.toList());
        fullList.add(foundTaxiVariantDTOS);
        return fullList.size()-1;

    }
    List<TaxiVariantDTO> findTaxiResults(int findId){

        return fullList.get(findId);
    }

    String reservation(UUID reservationId, int findId){
        List<TaxiVariantDTO> taxiVariantDTOS;
        String findName;
        String answerSt;
        Answer answerOj = new Answer();

        taxiVariantDTOS = fullList.get(findId);
        TaxiVariantDTO taxiVariantDTO = taxiVariantDTOS
                .stream()
                .filter((x) -> x.idVariant.equals(reservationId))
                .findFirst()
                .get();
        findName = taxiVariantDTO.name;
        boolean status = taxiAggregators
                .stream()
                .filter((x) -> x.getName().equals(findName))
                .map(TaxiAggregator::getTaxi).findFirst().orElse(false);
        answerSt = answerOj.getAnswer(taxiVariantDTO,status);
        return answerSt;
    }

}