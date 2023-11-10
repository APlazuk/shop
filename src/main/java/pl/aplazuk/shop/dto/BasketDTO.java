package pl.aplazuk.shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pl.aplazuk.shop.model.Product;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BasketDTO {

    private double totalCost;
    private double tax;
    private double discount;
    private List<Product> productList;
}
