package kr.co.fastcampus.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MenuItem {

    private Long id;
    private Long restaurantId;
    private String name;

}
