package com.taxi;

import org.springframework.stereotype.Service;


@Service
public class Answer {


    public String getAnswer(TaxiVariant taxiVariant, boolean status) {
        String answer = ("Ошибка! данный вариант такси не найден! Попробуйте снова!");
        if (status) {
            answer = ("Такси из города " + taxiVariant.from + " в город " + taxiVariant.to + " успешно заказано за " + taxiVariant.price + " руб. Спасибо!");
        }
        return answer;
    }

}
