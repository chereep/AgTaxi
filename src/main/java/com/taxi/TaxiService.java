package com.taxi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class TaxiService {
    private final List<TaxiAggregator> taxiAggregators;
    private final List<List<TaxiVariantDTO>> requestsTaxi = new ArrayList<>();
    private List<TaxiReservesDTO> taxiReserves = new ArrayList<>();


    public TaxiService(List<TaxiAggregator> taxiAggregators){
        this.taxiAggregators = taxiAggregators;
    }
    int findTaxiVariants(String from, String to) {
        List<TaxiVariantDTO> foundTaxiVariantsDTO = taxiAggregators
                .stream()
                .map(x->x.findTaxiVariant(from, to))
                .collect(Collectors.toList());
        requestsTaxi.add(foundTaxiVariantsDTO);
        return requestsTaxi.size()-1;

    }
    List<TaxiVariantDTO> findTaxiResults(int findId){

        return requestsTaxi.get(findId);
    }

    String reservation(UUID reservationId, int findId){
        List<TaxiVariantDTO> taxiVariants;
        String findName;
        Answer answerOj = new Answer();
        TaxiReservesDTO taxiReservesDTO = new TaxiReservesDTO();

        taxiVariants = requestsTaxi.get(findId);
        TaxiVariantDTO taxiVariantDTO = taxiVariants
                .stream()
                .filter((x) -> x.idVariant.equals(reservationId))
                .findFirst()
                .get(); // TODO если не нашли запрос, сделать отправку ответа об отсутствии запроса или сделать через цикл
        findName = taxiVariantDTO.name;
        boolean status = taxiAggregators
                .stream()
                .filter((x) -> x.getName().equals(findName))
                .map(TaxiAggregator::getTaxi).findFirst().orElse(false);
        taxiReservesDTO.reservesId = reservationId;
        this.taxiReserves.add(taxiReservesDTO);
        return answerOj.getAnswerResTaxi(taxiVariantDTO, status);
    }
    String unReservation(UUID reservationId){
        Answer answer = new Answer();
        boolean status = taxiReserves
                .removeIf((x)->x.reservesId.equals(reservationId));
        return answer.getAnswerUnResTaxi(status);

   }

}