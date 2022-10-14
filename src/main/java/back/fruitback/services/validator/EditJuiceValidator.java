package back.fruitback.services.validator;

import back.fruitback.services.domain.persistence.JuiceRepository;
import back.fruitback.services.resource.EditJuiceRequest;
import back.fruitback.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class EditJuiceValidator {

    private final JuiceRepository juiceRepository;

    public EditJuiceValidator(JuiceRepository juiceRepository) {
        this.juiceRepository = juiceRepository;
    }

    public Notification validate(EditJuiceRequest editJuiceRequest) {
        Notification notification = new Notification();

        Float price = Float.valueOf(editJuiceRequest.getPrice() != null ? editJuiceRequest.getPrice().toString().trim() : "");
        if (price.toString().isEmpty()) {
            notification.addError("Juice price is required");
        }

        return notification;
    }
}
