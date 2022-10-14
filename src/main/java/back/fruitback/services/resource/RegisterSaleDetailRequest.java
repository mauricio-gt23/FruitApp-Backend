package back.fruitback.services.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterSaleDetailRequest {
    private Long productId;
    private Integer quantityProduct;
}
