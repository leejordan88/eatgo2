package kr.co.fastcampus.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class MenuItem {

    @Id @GeneratedValue
    private Long id;
    private Long restaurantId;
    private String name;

}
