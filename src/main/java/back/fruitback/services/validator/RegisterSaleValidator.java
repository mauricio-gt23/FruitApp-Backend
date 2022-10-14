package back.fruitback.services.validator;

import back.fruitback.services.resource.RegisterSaleRequest;
import back.fruitback.shared.application.Notification;
import back.fruitback.userProfile.domain.model.Client;
import back.fruitback.userProfile.domain.persistence.ClientRepository;
import back.fruitback.userProfile.domain.persistence.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisterSaleValidator {

    private final UserRepository userRepository;

    public RegisterSaleValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Notification validate(RegisterSaleRequest request) {
        Notification notification = new Notification();

        Boolean user = userRepository.existsById(request.getUserId());

        if (!user) {
            notification.addError("User id doesn't exist.");
            return notification;
        }

        Float totalPrice = Float.valueOf(request.getTotalPrice() != null ? request.getTotalPrice().toString().trim() : "");
        if (totalPrice.toString().isEmpty()) {
            notification.addError("Sale totalPrice is required");
            return notification;
        }

        return notification;
    }
}
