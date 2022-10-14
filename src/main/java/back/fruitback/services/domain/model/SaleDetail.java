package back.fruitback.services.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="sale_detail")
public class SaleDetail {

    @EmbeddedId
    private SaleDetailKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("saleId")
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantityProduct;

    public SaleDetail(Sale sale, Product product, Integer quantityProduct) {
        this.id = new SaleDetailKey(sale.getId(), product.getId());
        this.sale = sale;
        this.product = product;
        this.quantityProduct = quantityProduct;
    }
}
