package com.taxi;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestAggregatorRandomUuidGenerator {

    public UUID getRandomId() {
        return UUID.randomUUID();
    }
}
