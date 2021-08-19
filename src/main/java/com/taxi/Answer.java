package com.taxi;

import org.springframework.stereotype.Service;


@Service
public class Answer {


    public String getAnswerResTaxi(TaxiVariantDTO taxiVariantDTO, boolean status) {
        String answer = "Ошибка! данный вариант такси не найден!";
        if (status) {
            answer = "Такси из города " + taxiVariantDTO.from + " в город " + taxiVariantDTO.to + " успешно заказано за " + taxiVariantDTO.price + " руб. Спасибо!";
        }
        return answer;
    }

    public String getAnswerUnResTaxi(boolean status) {
        String answer = "Ошибка! данный резерв такси не найден!";
        if (status) {
            answer = "Резерв успешно отменен!";
        }
        return answer;


    }
}