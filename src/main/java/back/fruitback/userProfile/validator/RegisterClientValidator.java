package back.fruitback.userProfile.validator;

import back.fruitback.shared.application.Notification;
import back.fruitback.userProfile.domain.persistence.ClientRepository;
import back.fruitback.userProfile.domain.persistence.UserRepository;
import back.fruitback.userProfile.resource.RegisterClientRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegisterClientValidator {

    private final ClientRepository adminRepository;
    private final UserRepository userRepository;

    public RegisterClientValidator(ClientRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public Notification validate(RegisterClientRequest registerClientRequest) {
        Notification notification = new Notification();

        Boolean existEmail = userRepository.existsByEmail(registerClientRequest.getEmail());
        if (existEmail) {
            notification.addError("A User with the same email already exists");
            return notification;
        }

        String email = registerClientRequest.getEmail() != null ? registerClientRequest.getEmail().trim() : "";
        if (email.isEmpty()) {
            notification.addError("Admin email is required");
        }

        return notification;
    }

}
