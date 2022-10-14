package back.fruitback.services.validator;

import back.fruitback.services.domain.persistence.JuiceRepository;
import back.fruitback.services.resource.RegisterJuiceRequest;
import back.fruitback.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class RegisterJuiceValidator {

    private final JuiceRepository juiceRepository;

    public RegisterJuiceValidator(JuiceRepository juiceRepository) {
        this.juiceRepository = juiceRepository;
    }

    public Notification validate(RegisterJuiceRequest request) {
        Notification notification = new Notification();

        Boolean existName = juiceRepository.existsByName(request.getName());
        if (existName) {
            notification.addError("A Juice with the same name already exists");
            return notification;
        }

        String name = request.getName() != null ? request.getName().trim() : "";
        if (name.isEmpty()) {
            notification.addError("Juice name is required");
        }

        return notification;
    }
}
