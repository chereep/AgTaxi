package com.taxi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class Controller {
    private final TaxiService taxiService;

    public Controller(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @PostMapping("/api/taxi/variants")
    public int find(@RequestBody FindTaxiRequestTDO findTaxiRequestTDO) {
        return taxiService.findTaxiVariants(findTaxiRequestTDO.from, findTaxiRequestTDO.to);
    }

    @PostMapping("/api/taxi/result")
    public TaxiVariantsDTO findTaxiResult(@RequestBody FindIdDTO findIdDTO) {
        TaxiVariantsDTO taxiVariantsDTO = new TaxiVariantsDTO();
        taxiVariantsDTO.variantList = taxiService.findTaxiResults(findIdDTO.findId);
        return taxiVariantsDTO;
    }
    @PostMapping("/api/taxi/reservation")
    public String reservationTaxi(@RequestBody UserIdUuidDTO userIdUuidDTO) {
        return taxiService.reservation(userIdUuidDTO.uuid,userIdUuidDTO.id);

    }
    @PostMapping("/api/taxi/unReservation")
    public String unReservationTaxi(@RequestBody UserIdUuidDTO userIdUuidDTO){
        return taxiService.unReservation(userIdUuidDTO.uuid);
   }
}