package back.fruitback.services.validator;

import back.fruitback.services.domain.persistence.FruitRepository;
import back.fruitback.services.resource.RegisterFruitRequest;
import back.fruitback.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class RegisterFruitValidator {

    private final FruitRepository fruitRepository;

    public RegisterFruitValidator(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Notification validate(RegisterFruitRequest request) {
        Notification notification = new Notification();

        Boolean existName = fruitRepository.existsByName(request.getName());
        if (existName) {
            notification.addError("A Fruit with the same name already exists");
            return notification;
        }

        String name = request.getName() != null ? request.getName().trim() : "";
        if (name.isEmpty()) {
            notification.addError("Fruit name is required");
        }

        return notification;
    }
}
