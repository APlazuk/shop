package pl.aplazuk.shop.service;

import pl.aplazuk.shop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProductsByBasketId(Long basketId);

    List<Product> findAll();

}
