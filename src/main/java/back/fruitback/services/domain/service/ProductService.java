package back.fruitback.services.domain.service;

import back.fruitback.services.resource.ProductResource;

public interface ProductService {
    ProductResource getById(Long productId);
}
