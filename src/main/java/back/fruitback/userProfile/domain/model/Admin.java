package back.fruitback.userProfile.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("user_admin")
public class Admin extends User {

    private String adminName;

    private String adminLastName;

    private Float number;

    public Admin(Long id, String email, String password, String adminName, String adminLastName, Float number) {
        super(id, email, password);
        this.adminName = adminName;
        this.adminLastName = adminLastName;
        this.number = number;
    }
    public Admin(String email, String password,  String adminName, String adminLastName, Float number) {
        super(email, password);
        this.adminName = adminName;
        this.adminLastName = adminLastName;
        this.number = number;
    }
}
