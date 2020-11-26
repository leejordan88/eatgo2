package kr.co.fastcampus.eatgo.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @BeforeEach
    void setUp() throws Exception {
        create_restaurant();
    }

    @Test
    void 리스트_조회() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bob zip")))
                .andExpect(content().string(containsString("1")))
        ;
    }

    @Test
    void 가게_상세() throws Exception {
        mockMvc.perform(get("/restaurants/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("Bob zip")))
                .andExpect(content().string(containsString("Seoul")));
//                .andExpect(content().string(containsString("Kimchi")));
    }

    @Test
    void 가게_상세_NOT_FOUND() throws Exception {
        long id = 404L;
        given(restaurantService.getRestaurant(id)).willThrow(new RestaurantNotFoundException(id));

        mockMvc.perform(get("/restaurants/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("가게_추가")
    void create_restaurant() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .name("Bob zip")
                .address("Seoul")
                .build();

        mockMvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants"))
                .andExpect(content().string("{}"))
                ;
    }

    @Test
    @DisplayName("가게_실패")
    void create_restaurant_400() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .build();

        mockMvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isBadRequest());
        ;
    }

    @Test
    void 가게_수정() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .name("Bob zip")
                .address("Seoul")
                .build();

        mockMvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants"))
                .andExpect(content().string("{}"))
        ;

        restaurant.setName("bar");

        mockMvc.perform(patch("/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurant)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}