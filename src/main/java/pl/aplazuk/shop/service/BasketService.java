package pl.aplazuk.shop.service;

import pl.aplazuk.shop.model.Basket;
import pl.aplazuk.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface BasketService {

     Optional<Basket> findBasketById(Long basketId);

     Basket addProductToBasket(List<Product> products, Basket basket);

}
