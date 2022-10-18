package back.fruitback.services.service;

import back.fruitback.services.domain.persistence.ProductRepository;
import back.fruitback.services.domain.service.ProductService;
import back.fruitback.services.mapping.ProductMapper;
import back.fruitback.services.resource.ProductResource;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductResource getById(Long productId) {
        return mapper.toResource(productRepository.getReferenceById(productId));
    }

}
