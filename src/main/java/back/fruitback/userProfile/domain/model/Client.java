package back.fruitback.userProfile.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("user_client")
public class Client extends User {

    private String name;

    private String lastName;

    private Float number;

    private String address;

    public Client(Long id, String email, String password,  String name, String lastName, Float number, String address) {
        super(id, email, password);
        this.name = name;
        this.lastName = lastName;
        this.number = number;
        this.address = address;
    }

    public Client(String email, String password,  String name, String lastName, Float number, String address) {
        super(email, password);
        this.name = name;
        this.lastName = lastName;
        this.number = number;
        this.address = address;
    }
}
