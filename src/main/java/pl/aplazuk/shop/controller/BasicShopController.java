package pl.aplazuk.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.aplazuk.shop.dto.ProductDTO;
import pl.aplazuk.shop.dto.ShopMapper;
import pl.aplazuk.shop.model.Basket;
import pl.aplazuk.shop.model.Product;
import pl.aplazuk.shop.service.BasketService;
import pl.aplazuk.shop.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class BasicShopController {

    private final BasketService basketService;
    private final ProductService productService;
    private final ShopMapper shopMapper;

    @Autowired
    public BasicShopController(BasketService basketService, ProductService productService, ShopMapper shopMapper) {
        this.basketService = basketService;
        this.productService = productService;
        this.shopMapper = shopMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<Product> productList = productService.findAll();
        if (productList.isEmpty() ){
            return new ResponseEntity<>("Shop has no products! Please come later", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(shopMapper.convertProductsToDto(productList), HttpStatus.OK);
    }

    @GetMapping("/products/{basketId}")
    public ResponseEntity<?> getAllProductsByBasketId(@PathVariable Long basketId){
        Optional<Basket> basket = basketService.findBasketById(basketId);

        if (basket.isEmpty()){
            return new ResponseEntity<>("Chosen basket doesn't exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(shopMapper.convertBasketToDto(basket.get()), HttpStatus.OK);
    }

    @PostMapping("/add/{basketId}")
    public ResponseEntity<?> addProductsToBasket(@PathVariable Long basketId, @RequestBody List<ProductDTO> productDTOList){
        Optional<Basket> basketById = basketService.findBasketById(basketId);

        if (basketById.isEmpty()){
           return ResponseEntity.notFound().build();
        }

        List<Product> products = shopMapper.convertProductsDtoToEntity(productDTOList);
        basketService.addProductToBasket(products, basketById.get());

        String message = String.format("Products: %s added to the basket", productDTOList);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
