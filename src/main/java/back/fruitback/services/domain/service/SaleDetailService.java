package back.fruitback.services.domain.service;

import back.fruitback.services.domain.model.SaleDetail;
import back.fruitback.services.resource.RegisterSaleDetailRequest;
import back.fruitback.services.resource.SaleDetailResource;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;

import java.util.List;

public interface SaleDetailService {
    List<SaleDetail> getAll(Long saleId);
    Result<SaleDetailResource, Notification> register(Long saleId, RegisterSaleDetailRequest request);
}
