package com.taxi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TaxiReservationRepository {
    private final List<TaxiReserveDTO> taxiReserves = new ArrayList<>();

    public int save(TaxiReserveDTO taxiReserve){
        taxiReserves.add(taxiReserve);
        return taxiReserves.size() - 1;
    }
    public boolean removeByReservationId(UUID reservationId){
        return taxiReserves.removeIf((x)->x.reservesId.equals(reservationId));
    }
}
