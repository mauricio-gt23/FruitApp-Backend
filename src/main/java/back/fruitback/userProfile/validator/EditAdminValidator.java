package back.fruitback.userProfile.validator;

import back.fruitback.shared.application.Notification;
import back.fruitback.userProfile.domain.persistence.AdminRepository;
import back.fruitback.userProfile.domain.persistence.UserRepository;
import back.fruitback.userProfile.resource.EditAdminRequest;
import org.springframework.stereotype.Component;

@Component
public class EditAdminValidator {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public EditAdminValidator(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public Notification validate(EditAdminRequest editAdminRequest) {

        Notification notification = new Notification();

        String adminName = editAdminRequest.getAdminName() != null ? editAdminRequest.getAdminName().trim() : "";
        if (adminName.isEmpty()) {
            notification.addError("Admin adminName is required");
        }
        //Optional<Admin> adminOptional = userRepository.findByEmail();

        return notification;

    }

}
