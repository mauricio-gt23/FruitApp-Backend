package back.fruitback.services.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String name;

    protected Float price;

    protected String urlImage;

    @Column(name = "product_type", insertable = false, updatable = false)
    protected String productType;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    protected List<SaleDetail> sales = new ArrayList<>();

    public Product(Long id, String name, Float price, String urlImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
    }

    public Product(String name, Float price, String urlImage) {
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
    }
}
