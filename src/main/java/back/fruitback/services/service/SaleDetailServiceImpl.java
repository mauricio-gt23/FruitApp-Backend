package back.fruitback.services.service;

import back.fruitback.services.domain.model.Product;
import back.fruitback.services.domain.model.Sale;
import back.fruitback.services.domain.model.SaleDetail;
import back.fruitback.services.domain.model.SaleDetailKey;
import back.fruitback.services.domain.persistence.ProductRepository;
import back.fruitback.services.domain.persistence.SaleDetailRepository;
import back.fruitback.services.domain.persistence.SaleRepository;
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

    private final ProductRepository productRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailMapper saleDetailMapper;

    public SaleDetailServiceImpl(ProductRepository productRepository, SaleDetailRepository saleDetailRepository, SaleRepository saleRepository, SaleDetailMapper saleDetailMapper) {
        this.productRepository = productRepository;
        this.saleDetailRepository = saleDetailRepository;
        this.saleRepository = saleRepository;
        this.saleDetailMapper = saleDetailMapper;
    }

    @Override
    public List<SaleDetail> getAll(Long saleId) {
        return saleDetailRepository.findBySaleId(saleId);
    }

    @Override
    public Result<SaleDetailResource, Notification> register(Long saleId, RegisterSaleDetailRequest request) {

        Product product = productRepository.findById(request.getProductId()).get();
        Sale sale = saleRepository.findById(saleId).get();
        SaleDetailKey saleDetailKey = new SaleDetailKey(saleId, request.getProductId());

        SaleDetail saleDetail = new SaleDetail(
                saleDetailKey,
                sale,
                product,
                request.getQuantityProduct()
        );

        saleDetailRepository.save(saleDetail);

        SaleDetailResource resource = saleDetailMapper.toResource(saleDetail);
        return Result.success(resource);
    }
}
