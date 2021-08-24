package com.taxi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {
  @Autowired
  MockMvc mockMvc;
     @Test
     void test() throws Exception {
      mockMvc.perform(post("/api/taxi/variants").contentType(MediaType.APPLICATION_JSON).content("" +
              "{\n" +
              "     \"from\": \"from\"," +
              "     \"to\": \"to\"" +
              "}")).andExpect(status().isOk())
              .andExpect(content().string("0"));
//      mockMvc.perform(post("/api/taxi/result").contentType(MediaType.APPLICATION_JSON).content("" +
//              "{\n" +
//              "     \"findId\": 0\n" +
//              "}")).andExpect(content().json(""));
     }

}