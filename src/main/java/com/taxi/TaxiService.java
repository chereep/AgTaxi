package com.taxi;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class TaxiService {
    private final List<TaxiAggregator> taxiAggregators;
    private final Answer answer;
    private final TaxiRequestRepository taxiRequestRepository;
    private final TaxiReservationRepository taxiReservationRepository;

    public TaxiService(List<TaxiAggregator> taxiAggregators, Answer answer, TaxiRequestRepository taxiRequestRepository, TaxiReservationRepository taxiReservationRepository) {
        this.taxiAggregators = taxiAggregators;
        this.answer = answer;
        this.taxiRequestRepository = taxiRequestRepository;
        this.taxiReservationRepository = taxiReservationRepository;
    }

    public int findTaxiVariants(String from, String to) {
        List<TaxiVariantDTO> foundTaxiVariants = taxiAggregators
                .stream()
                .map(x -> x.findTaxiVariant(from, to))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return taxiRequestRepository.save(foundTaxiVariants);
    }

    public List<TaxiVariantDTO> findTaxiResults(int findId) {
        List<TaxiVariantDTO> taxiVariants = taxiRequestRepository.findById(findId);

        if (!taxiVariants.isEmpty()) {
            return taxiVariants;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public String reservation(UUID reservationId, int findId) {

        TaxiVariantDTO taxiVariantDTO = taxiRequestRepository.findById(findId)
                .stream()
                .filter((x) -> x.idVariant.equals(reservationId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Резерв не найден!"));
        boolean status = taxiAggregators
                .stream()
                .filter((x) -> x.getName().equals(taxiVariantDTO.name))
                .map(TaxiAggregator::getTaxi).findFirst().orElse(false);
        TaxiReserveDTO taxiReserveDTO = new TaxiReserveDTO();
        taxiReserveDTO.reservesId = reservationId;
        taxiReservationRepository.save(taxiReserveDTO);
        return answer.getAnswerResTaxi(taxiVariantDTO, status);
    }

    public String unReservation(UUID reservationId) {
        return answer.getAnswerUnResTaxi(taxiReservationRepository.removeByReservationId(reservationId));
    }
}