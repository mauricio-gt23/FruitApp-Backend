package back.fruitback.services.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterSaleRequest {
    private Long userId;
    private Double totalPrice;
}
