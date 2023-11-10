package pl.aplazuk.shop.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


@Getter @Setter
@Entity
@NoArgsConstructor
@ToString(exclude = "basket")
public class Product extends BaseEntity {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Basket basket;

}
