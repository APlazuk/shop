package pl.aplazuk.shop.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Basket extends BaseEntity{

    private double totalCost;
    private double tax;
    private double discount;

    @OneToMany(mappedBy = "basket",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> productList;

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setTax(double tax){
        this.tax = tax;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        productList.forEach(product -> product.setBasket(this));
    }

}
