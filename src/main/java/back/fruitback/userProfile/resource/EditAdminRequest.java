package back.fruitback.userProfile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditAdminRequest {
    private String password;
    private String adminName;
    private String adminLastName;
    private Float number;
}
