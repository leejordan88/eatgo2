package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DisplayName("레스토랑 서비스")
@SpringBootTest
class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @MockBean
    RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void 레스토랑_상세정보() {
        Restaurant restaurant = Restaurant.builder().name("준성").address("안양").build();
        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);

        Restaurant selected = restaurantService.getRestaurant(1L);
        assertEquals(1L, selected.getId());
    }

    @Test
    void 레스토랑_등록() {
        Restaurant restaurant = Restaurant.builder().name("준성").address("안양").build();
        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);
        assertEquals(2L, createdRestaurant.getId());
    }

    @Test
    void 레스토랑_업데이트() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("jordan")
                .address("seoul")
                .build();
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        Restaurant updated = restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");

        assertEquals("Sool zip", updated.getName());
        assertEquals("Busan", updated.getAddress());
    }

}