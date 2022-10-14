package back.fruitback.services.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleResource {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private String status;
    private String createdAt;
}
