package pl.aplazuk.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.aplazuk.shop.model.Basket;
import pl.aplazuk.shop.model.Product;
import pl.aplazuk.shop.repositories.BasketRepository;
import pl.aplazuk.shop.repositories.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class StarterDataCreator implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;

    @Autowired
    public StarterDataCreator(ProductRepository productRepository, BasketRepository basketRepository) {
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createBasket();
        createProducts();
    }

    private void createProducts() {
        Product product1 = new Product("Jeansy", createRandomPrice());
        Product product2 = new Product("Sweter", createRandomPrice());
        Product product3 = new Product("Koszula", createRandomPrice());
        Product product4 = new Product("Kurtka", createRandomPrice());
        Product product5 = new Product("Szalik", createRandomPrice());

        List<Product> productList = List.of(product1, product2, product3, product4, product5);

        productRepository.saveAll(productList);

    }

    private void createBasket() {
        Basket basket = new Basket();
        basketRepository.save(basket);
    }

    private double createRandomPrice(){
        double randomPrice = 50 + (Math.random() * ((350 - 50) + 1));
        BigDecimal bd = new BigDecimal(randomPrice);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
