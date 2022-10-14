package back.fruitback.services.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterFruitRequest {
    private String name;
    private Float price;
    private String urlImage;
}
