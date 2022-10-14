package back.fruitback.services.validator;

import back.fruitback.services.domain.persistence.JuiceRepository;
import back.fruitback.services.domain.persistence.SaleRepository;
import back.fruitback.services.resource.EditJuiceRequest;
import back.fruitback.services.resource.EditSaleRequest;
import back.fruitback.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class EditSaleValidator {

    private final SaleRepository saleRepository;

    public EditSaleValidator(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Notification validate(EditSaleRequest editSaleRequest) {
        Notification notification = new Notification();

        String productType = editSaleRequest.getProductType() != null ? editSaleRequest.getProductType().trim() : "";
        if (productType.isEmpty()) {
            notification.addError("Sale productType is required");
        }

        return notification;
    }


}
