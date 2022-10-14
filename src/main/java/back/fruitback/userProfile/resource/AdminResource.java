package back.fruitback.userProfile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResource {
    private Long id;
    private String email;
    private String password;
    private String adminName;
    private String adminLastName;
    private Float number;
}
