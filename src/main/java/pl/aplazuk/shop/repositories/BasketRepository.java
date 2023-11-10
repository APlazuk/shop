package pl.aplazuk.shop.repositories;

import pl.aplazuk.shop.model.Basket;
import java.util.Optional;

public interface BasketRepository {

    Optional<Basket> findBasketById(Long basketId);

    Basket save(Basket basket);

}
