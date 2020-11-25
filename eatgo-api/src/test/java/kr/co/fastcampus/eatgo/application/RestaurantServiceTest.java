package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("레스토랑 서비스")
class RestaurantServiceTest {

    private RestaurantService restaurantService;
    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    void setUp() {
        restaurantRepository = new RestaurantRepositoryImpl();
        menuItemRepository = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantServiceImpl(restaurantRepository, menuItemRepository);
    }

    @Test
    void 레스토랑_상세정보() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assertEquals(1004L, restaurant.getId());
    }

    @Test
    void 레스토랑_등록() {
        Restaurant restaurant = Restaurant.builder().name("준성").address("안양").build();
        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);

        assertEquals(1234L, createdRestaurant.getId());
    }

}