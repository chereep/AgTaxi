package com.taxi;

import org.springframework.stereotype.Service;


@Service
public class Answer {


    public String getAnswer(TaxiVariantDTO taxiVariantDTO, boolean status) {
        String answer = ("Ошибка! данный вариант такси не найден! Попробуйте снова!");
        if (status) {
            answer = ("Такси из города " + taxiVariantDTO.from + " в город " + taxiVariantDTO.to + " успешно заказано за " + taxiVariantDTO.price + " руб. Спасибо!");
        }
        return answer;
    }

}
