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
@DiscriminatorValue("juice")
public class Juice extends Product{
    public Juice(Long id, String name, Float price, String urlImage) {
        super(id, name, price, urlImage);
    }

    public Juice(String name, Float price, String urlImage) {
        super(name, price, urlImage);
    }
}
