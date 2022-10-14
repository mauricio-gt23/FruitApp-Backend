package back.fruitback.services.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("fruit")
public class Fruit extends Product {
    public Fruit(Long id, String name, Float price, String urlImage) {
        super(id, name, price, urlImage);
    }

    public Fruit(String name, Float price, String urlImage) {
        super(name, price, urlImage);
    }
}
