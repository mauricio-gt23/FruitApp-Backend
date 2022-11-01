package back.fruitback.services.controller;

import back.fruitback.services.domain.service.SaleService;
import back.fruitback.services.mapping.SaleMapper;
import back.fruitback.services.resource.EditSaleRequest;
import back.fruitback.services.resource.RegisterSaleRequest;
import back.fruitback.services.resource.SaleResource;
import back.fruitback.shared.api.ApiController;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/sales")
public class SaleController {

    private final SaleService saleService;
    private final SaleMapper saleMapper;

    public SaleController(SaleService saleService, SaleMapper saleMapper) {
        this.saleService = saleService;
        this.saleMapper = saleMapper;
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(Pageable pageable) {
        Page<SaleResource> resources = saleMapper.modelListToPage(saleService.getAll(), pageable);
        return ApiController.ok(resources);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<Object> getById(@PathVariable("saleId") Long saleId) {
        try {
            SaleResource sale = saleService.getById(saleId);
            return ApiController.ok(sale);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @GetMapping(params = "userId")
    public ResponseEntity<Object> getByUserId(@RequestParam(value = "userId", required = false) Long userId, Pageable pageable) {
        try {
            Page<SaleResource> resources = saleMapper.modelListToPage(saleService.getByUserId(userId), pageable);
            return ApiController.ok(resources);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> register(@RequestBody RegisterSaleRequest request) {
        try {
            Result<SaleResource, Notification> result = saleService.register(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<Object> editSale(@PathVariable("saleId") Long saleId, @RequestBody EditSaleRequest request) {
        try {
            Result<SaleResource, Notification> result = saleService.edit(saleId, request);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
