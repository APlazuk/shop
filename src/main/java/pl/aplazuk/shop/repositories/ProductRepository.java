package pl.aplazuk.shop.repositories;


import pl.aplazuk.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<List<Product>> findAllProductsByBasketId(Long basketId);

    <S extends Product> List<S> saveAll(Iterable<S> entities);

    List<Product> findAll();
}
