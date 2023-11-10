package pl.aplazuk.shop.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aplazuk.shop.model.Basket;
import pl.aplazuk.shop.model.Product;
import pl.aplazuk.shop.repositories.BasketRepository;

import java.util.List;

@Repository
interface SqlBasketRepository extends BasketRepository, JpaRepository<Basket, Long> {
}
