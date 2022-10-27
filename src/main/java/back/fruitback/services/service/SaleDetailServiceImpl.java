package back.fruitback.services.service;


import back.fruitback.services.domain.model.Product;
import back.fruitback.services.domain.model.Sale;
import back.fruitback.services.domain.model.SaleDetail;
import back.fruitback.services.domain.persistence.ProductRepository;
import back.fruitback.services.domain.persistence.SaleDetailRepository;
import back.fruitback.services.domain.persistence.SaleRepository;
import back.fruitback.services.domain.service.SaleDetailService;
import back.fruitback.services.mapping.SaleDetailMapper;
import back.fruitback.services.resource.RegisterSaleDetailRequest;
import back.fruitback.services.resource.SaleDetailResource;
import back.fruitback.services.resource.SaleResource;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final SaleDetailMapper mapper;

    public SaleDetailServiceImpl(ProductRepository productRepository, SaleRepository saleRepository, SaleDetailRepository saleDetailRepository, SaleDetailMapper mapper) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
        this.mapper = mapper;
    }

    @Override
    public List<SaleDetail> getAll(Long saleId) {
        return saleDetailRepository.findBySaleId(saleId);
    }

    @Override
    public Result<SaleDetailResource, Notification> register(Long saleId, RegisterSaleDetailRequest request) {

        Sale sale = saleRepository.getReferenceById(saleId);
        Product product = productRepository.getReferenceById(request.getProductId());

        SaleDetail saleDetail = new SaleDetail(
                sale,
                product,
                request.getQuantityProduct()
        );

        saleDetailRepository.save(saleDetail);

        SaleDetailResource resource = mapper.toResource(saleDetail);
        return Result.success(resource);
    }
}
