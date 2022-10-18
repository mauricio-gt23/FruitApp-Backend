package back.fruitback.services.service;


import back.fruitback.services.domain.model.SaleDetail;
import back.fruitback.services.domain.persistence.SaleDetailRepository;
import back.fruitback.services.domain.service.SaleDetailService;
import back.fruitback.services.mapping.SaleDetailMapper;
import back.fruitback.services.resource.RegisterSaleDetailRequest;
import back.fruitback.services.resource.SaleDetailResource;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;
    private final SaleDetailMapper mapper;

    public SaleDetailServiceImpl(SaleDetailRepository saleDetailRepository, SaleDetailMapper mapper) {
        this.saleDetailRepository = saleDetailRepository;
        this.mapper = mapper;
    }

    @Override
    public List<SaleDetail> getAll(Long saleId) {
        return null;
    }

    @Override
    public Result<SaleDetailResource, Notification> register(Long saleId, RegisterSaleDetailRequest request) {
        return null;
    }
}
