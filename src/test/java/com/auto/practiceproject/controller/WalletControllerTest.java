package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.dto.request.MoneyTransferDTO;
import com.auto.practiceproject.util.ParseToJSONHelper;
import com.auto.practiceproject.util.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application_test.properties")
@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private ParseToJSONHelper helper;

    @Test
    public void putMoneyToWalletTest() throws Exception {
        String jsonRequest = helper.moneyTransferDTOToJSON(
                new MoneyTransferDTO("4903010000000009", LocalDate.now(), 10D));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest).with(testUtil.authentication("Dzmitry@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
