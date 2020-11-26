package kr.co.fastcampus.eatgo.domain;

import java.util.function.Supplier;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id) {
        super("Could not find restaurant " + id);
    }
}
