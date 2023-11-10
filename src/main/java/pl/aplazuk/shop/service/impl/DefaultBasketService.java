package pl.aplazuk.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.aplazuk.shop.model.Basket;
import pl.aplazuk.shop.model.Product;
import pl.aplazuk.shop.repositories.BasketRepository;
import pl.aplazuk.shop.service.BasketService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DefaultBasketService implements BasketService {

    @Value("${tax}")
    private double tax;

    @Value("${discount}")
    private double discount;

    private final BasketRepository basketRepository;

    @Autowired
    public DefaultBasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Optional<Basket> findBasketById(Long basketId) {
        Optional<Basket> basketById = basketRepository.findBasketById(basketId);

        if (basketById.isEmpty()) {
            log.warn("Exception in BasketService. Cannot find Basket with given id");

        }
        return basketById;
    }


    public Basket addProductToBasket(List<Product> products, Basket basket) {
        double totalProductsCost = getTotalProductsCost(products);

        basket.setTotalCost(totalProductsCost);
        basket.setProductList(products);
        basket.setTax(tax*totalProductsCost);
        basket.setDiscount(discount*totalProductsCost);
        return basketRepository.save(basket);
    }

    private double getTotalProductsCost(List<Product> productList){
        double totalPrice = productList.stream().mapToDouble(Product::getPrice).sum();
        BigDecimal bd = new BigDecimal(totalPrice);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
