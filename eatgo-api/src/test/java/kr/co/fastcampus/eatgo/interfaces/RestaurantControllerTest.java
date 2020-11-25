package kr.co.fastcampus.eatgo.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("레스토랑 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class RestaurantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RestaurantService restaurantService;

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

    @Test
    void 가게_추가() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .name("준성")
                .address("안양")
                .build();

        mockMvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"))
                ;

        verify(restaurantService).addRestaurant(any());
    }

}