package back.fruitback.userProfile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource {
    private Long id;
    private String email;
    private String password;
    private String userType;
}
