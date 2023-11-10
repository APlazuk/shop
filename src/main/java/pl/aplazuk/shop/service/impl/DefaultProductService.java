package pl.aplazuk.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aplazuk.shop.model.Product;
import pl.aplazuk.shop.repositories.ProductRepository;
import pl.aplazuk.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProductsByBasketId(Long basketId) {
        Optional<List<Product>> productList = productRepository.findAllProductsByBasketId(basketId);

        if (productList.isEmpty() || productList.get().isEmpty()){
            log.warn("Basket with given id: {} doesn't contain any products", basketId);
            return new ArrayList<>();
        }

        return productList.get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
