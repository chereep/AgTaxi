package com.taxi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
class Controller {
    private final TaxiService taxiService;

    public Controller(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @PostMapping("/api/taxi/variants")
    public int find(@RequestBody FindTaxiRequest findTaxiRequest) {
        return taxiService.findTaxiVariants(findTaxiRequest.from, findTaxiRequest.to);
    }

    @PostMapping("/api/taxi/result")
    public TaxiVariants findTaxiResult(@RequestBody FindId findId) {
        TaxiVariants taxiVariants = new TaxiVariants();
        taxiVariants.variantList = taxiService.findTaxiResults(findId.findId);
        return taxiVariants;
    }
    @PostMapping("/api/taxi/reservation")
    public String reservationTaxi(@RequestBody UserIdUuidDTO userIdUuidDTO) {
        UUID uuid = userIdUuidDTO.uuid;
        int idRequest = userIdUuidDTO.id;
        TaxiVariants taxiVariants = new TaxiVariants();
        return taxiService.reservation(uuid,idRequest);

    }
}


