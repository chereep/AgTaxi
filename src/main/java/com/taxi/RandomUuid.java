package com.taxi;

import java.util.UUID;

public class RandomUuid {
    UUID uuid = UUID.randomUUID();

    UUID getRandomId(){
        return uuid;
    }
}
