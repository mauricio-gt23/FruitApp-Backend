package back.fruitback.userProfile.validator;

import back.fruitback.shared.application.Notification;
import back.fruitback.userProfile.domain.persistence.AdminRepository;
import back.fruitback.userProfile.domain.persistence.UserRepository;
import back.fruitback.userProfile.resource.RegisterAdminRequest;
import org.springframework.stereotype.Component;

@Component
public class RegisterAdminValidator {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public RegisterAdminValidator(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public Notification validate(RegisterAdminRequest registerAdminRequest) {
        Notification notification = new Notification();

        Boolean existEmail = userRepository.existsByEmail(registerAdminRequest.getEmail());
        if (existEmail) {
            notification.addError("A User with the same email already exists");
            return notification;
        }

        String email = registerAdminRequest.getEmail() != null ? registerAdminRequest.getEmail().trim() : "";
        if (email.isEmpty()) {
            notification.addError("Admin email is required");
        }

        return notification;
    }
}
