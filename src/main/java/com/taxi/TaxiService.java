package com.taxi;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        List<TaxiVariantDTO> foundTaxiVariantsDTO;
        foundTaxiVariantsDTO = taxiAggregators
                .stream()
                .map(x -> x.findTaxiVariant(from, to)).filter(Objects::nonNull)
                .collect(Collectors.toList());
        requestsTaxi.add(foundTaxiVariantsDTO);
        return requestsTaxi.size() - 1;

    }

    List<TaxiVariantDTO> findTaxiResults(int findId) {
        List<TaxiVariantDTO> taxiVariants;
        taxiVariants = requestsTaxi.get(findId);

        if (!taxiVariants.isEmpty()) {
            return taxiVariants;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    String reservation(UUID reservationId, int findId) {
        List<TaxiVariantDTO> taxiVariants;
        String findName;
        Answer answerOj = new Answer();
        TaxiReservesDTO taxiReservesDTO = new TaxiReservesDTO();

        taxiVariants = requestsTaxi.get(findId);
        TaxiVariantDTO taxiVariantDTO = taxiVariants
                .stream()
                .filter((x) -> x.idVariant.equals(reservationId))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Резерв не найден!"));
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