package back.fruitback.services.domain.service;

import back.fruitback.services.domain.model.Sale;
import back.fruitback.services.resource.EditSaleRequest;
import back.fruitback.services.resource.RegisterSaleRequest;
import back.fruitback.services.resource.SaleResource;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;

import java.util.List;

public interface SaleService {
    List<Sale> getAll();
    SaleResource getById(Long saleId);
    Result<SaleResource, Notification> register(RegisterSaleRequest request);
    Result<SaleResource, Notification> edit(Long saleId, EditSaleRequest request);
}
