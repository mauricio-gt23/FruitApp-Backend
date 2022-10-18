package back.fruitback.services.controller;

import back.fruitback.services.domain.service.ProductService;
import back.fruitback.services.resource.ProductResource;
import back.fruitback.shared.api.ApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Object> getByIdProduct(@PathVariable("productId") Long productId) {
        try {
            ProductResource product = productService.getById(productId);
            return ApiController.ok(product);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }
}
