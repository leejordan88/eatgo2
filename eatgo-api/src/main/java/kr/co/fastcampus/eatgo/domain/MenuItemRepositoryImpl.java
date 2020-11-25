package kr.co.fastcampus.eatgo.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MenuItemRepositoryImpl implements MenuItemRepository {

    private List<MenuItem> menuItems;

    public MenuItemRepositoryImpl() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1L , 1004L,"Kimchi"));
    }

    @Override
    public List<MenuItem> findAllByRestaurantId(Long restaurantId) {
        return menuItems.stream()
                .filter(m -> m.getRestaurantId().equals(restaurantId))
                .collect(Collectors.toList());
    }
}
