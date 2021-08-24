package com.taxi;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class Answer {

    public String getAnswerResTaxi(TaxiVariantDTO taxiVariantDTO, boolean status) {
        if (status) {
            return "Такси из города " + taxiVariantDTO.from +
                    " в город " + taxiVariantDTO.to +
                    " успешно заказано за " + taxiVariantDTO.price +
                    " руб. Спасибо!";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public String getAnswerUnResTaxi(boolean status) {
        String answer;
        if (status) {
            answer = "Резерв успешно отменен!";
            return answer;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}