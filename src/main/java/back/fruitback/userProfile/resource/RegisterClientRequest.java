package back.fruitback.userProfile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterClientRequest {
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Float number;
    private String address;
}
