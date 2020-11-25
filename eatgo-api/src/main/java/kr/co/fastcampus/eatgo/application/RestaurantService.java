package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> findAll();
    Restaurant getRestaurant(Long id);
    Restaurant addRestaurant(Restaurant restaurant);
}
