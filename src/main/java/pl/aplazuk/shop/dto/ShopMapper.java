package pl.aplazuk.shop.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.aplazuk.shop.model.Basket;
import pl.aplazuk.shop.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShopMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ShopMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO convertProductToDto(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> convertProductsToDto(List<Product> products){
        return products.stream().map(this::convertProductToDto).collect(Collectors.toList());
    }

    public Product convertProductDtoToEntity(ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }

    public BasketDTO convertBasketToDto(Basket basket){
        return modelMapper.map(basket, BasketDTO.class);
    }

    public List<Product> convertProductsDtoToEntity(List<ProductDTO> products) {
        return products.stream().map(this::convertProductDtoToEntity).collect(Collectors.toList());
    }
}
