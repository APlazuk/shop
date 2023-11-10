package pl.aplazuk.shop.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.aplazuk.shop.model.Product;
import pl.aplazuk.shop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Long> {

    @Override
    @Query("SELECT p FROM Product p LEFT JOIN p.basket b where b.id = :basketId")
    Optional<List<Product>> findAllProductsByBasketId(@Param("basketId")Long basketId);

}
