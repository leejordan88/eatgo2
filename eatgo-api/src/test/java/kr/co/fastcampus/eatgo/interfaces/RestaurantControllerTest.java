package kr.co.fastcampus.eatgo.interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("레스토랑 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class RestaurantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void 리스트_조회() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bob zip")))
                .andExpect(content().string(containsString("1004")))
        ;
    }

    @Test
    void 가게_상세() throws Exception {
        mockMvc.perform(get("/restaurants/{id}", 1004))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1004")))
                .andExpect(content().string(containsString("Bob zip")))
                .andExpect(content().string(containsString("Seoul")))
                .andExpect(content().string(containsString("Kimchi")));

        mockMvc.perform(get("/restaurants/{id}", 2080))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2080")))
                .andExpect(content().string(containsString("Cyber Food")))
                .andExpect(content().string(containsString("Busan")));
    }

}