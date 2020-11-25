package kr.co.fastcampus.eatgo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    @Test
    void 레스토랑_생성자_등록_테스트() {
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");

        assertEquals(1004, restaurant.getId());
        assertEquals("Bob zip", restaurant.getName());
        assertEquals("Seoul", restaurant.getAddress());
    }

}