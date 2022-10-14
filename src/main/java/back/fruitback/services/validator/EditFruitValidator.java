package back.fruitback.services.validator;

import back.fruitback.services.domain.persistence.FruitRepository;
import back.fruitback.services.resource.EditFruitRequest;
import back.fruitback.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class EditFruitValidator {
    private final FruitRepository fruitRepository;

    public EditFruitValidator(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Notification validate(EditFruitRequest editFruitRequest) {
        Notification notification = new Notification();

        Float price = Float.valueOf(editFruitRequest.getPrice() != null ? editFruitRequest.getPrice().toString().trim() : "");
        if (price.toString().isEmpty()) {
            notification.addError("Fruit price is required");
        }

        return notification;
    }
}
