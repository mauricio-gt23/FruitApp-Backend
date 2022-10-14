package back.fruitback.services.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FruitResource {
    private Long id;
    private String name;
    private Float price;
    private String urlImage;
}
