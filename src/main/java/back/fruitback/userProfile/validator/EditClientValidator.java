package back.fruitback.userProfile.validator;

import back.fruitback.shared.application.Notification;
import back.fruitback.userProfile.domain.persistence.AdminRepository;
import back.fruitback.userProfile.domain.persistence.UserRepository;
import back.fruitback.userProfile.resource.EditClientRequest;
import org.springframework.stereotype.Component;

@Component
public class EditClientValidator {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public EditClientValidator(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public Notification validate(EditClientRequest editClientRequest) {

        Notification notification = new Notification();

        String adminName = editClientRequest.getName() != null ? editClientRequest.getName().trim() : "";
        if (adminName.isEmpty()) {
            notification.addError("Admin adminName is required");
        }

        return notification;

    }
}
