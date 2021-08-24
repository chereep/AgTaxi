package com.taxi;


import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TestAggregatorRandomUuidGenerator testAggregatorRandomUuidGenerator;
    @MockBean
    TestAggregatorRandomUuidGenerator2 testAggregatorRandomUuidGenerator2;


    @Test
    void testCod200() throws Exception {
        when(testAggregatorRandomUuidGenerator.getRandomId()).thenReturn(UUID.fromString("841deced-d500-46de-86ec-bcc44eebfd4e"));
        when(testAggregatorRandomUuidGenerator2.getRandomId()).thenReturn(UUID.fromString("bb48520e-1d02-4cb7-a37b-ba322993631c"));
        mockMvc.perform(post("/api/taxi/variants").contentType(MediaType.APPLICATION_JSON).content("" +
                        "{\n" +
                        "     \"from\": \"MSK\"," +
                        "     \"to\": \"NSK\"" +
                        "}")).andExpect(status().isOk())
                .andExpect(content().string("0"));
        mockMvc.perform(post("/api/taxi/result").contentType(MediaType.APPLICATION_JSON).content("" +
                        "{\n" +
                        "     \"findId\": 0\n" +
                        "}")).andExpect(status().isOk())
                .andExpect(content().json(IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream("/test_data/expected.json")), StandardCharsets.UTF_8)));
        mockMvc.perform(post("/api/taxi/reservation").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "     \"uuid\": \"841deced-d500-46de-86ec-bcc44eebfd4e\"\n" +
                        "}")).andExpect(status().isOk())
                .andExpect(content().string("Такси из города MSK в город NSK успешно заказано за 1000.0 руб. Спасибо!"));
        mockMvc.perform(post("/api/taxi/unReservation").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "     \"uuid\": \"841deced-d500-46de-86ec-bcc44eebfd4e\"\n" +
                        "}")).andExpect(status().isOk())
                .andExpect(content().string("Резерв успешно отменен!"));


    }

    @Test
    void testCod404() throws Exception {
        mockMvc.perform(post("/api/taxi/variants").contentType(MediaType.APPLICATION_JSON).content("" +
                        "{\n" +
                        "     \"from\": \"fromError\"," +
                        "     \"to\": \"toError\"" +
                        "}")).andExpect(status().isOk())
                .andExpect(content().string("0"));
        mockMvc.perform(post("/api/taxi/result").contentType(MediaType.APPLICATION_JSON).content("" +
                        "{\n" +
                        "     \"findId\": 0\n" +
                        "}")).andExpect(status().isNotFound());
        mockMvc.perform(post("/api/taxi/reservation").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "     \"uuid\": \"841deced-d500-46de-86ec-bcc44eebfd5e\"\n" +
                        "}")).andExpect(status().isNotFound());
        mockMvc.perform(post("/api/taxi/unReservation").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "     \"uuid\": \"841deced-d500-46de-86ec-bcc44eebfd5e\"\n" +
                        "}")).andExpect(status().isNotFound());
    }

}