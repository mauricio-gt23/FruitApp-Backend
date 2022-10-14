package back.fruitback.services.controller;

import back.fruitback.services.domain.service.SaleDetailService;
import back.fruitback.services.mapping.SaleDetailMapper;
import back.fruitback.services.resource.RegisterSaleDetailRequest;
import back.fruitback.services.resource.SaleDetailResource;
import back.fruitback.shared.api.ApiController;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/sales/{saleId}/details")
public class SaleDetailController {

    private final SaleDetailService saleDetailService;
    private final SaleDetailMapper saleDetailMapper;

    public SaleDetailController(SaleDetailService saleDetailService, SaleDetailMapper saleDetailMapper) {
        this.saleDetailService = saleDetailService;
        this.saleDetailMapper = saleDetailMapper;
    }

    @GetMapping()
    public ResponseEntity<Object> getAllBySaleId(@PathVariable("saleId") Long saleId, Pageable pageable) {
        Page<SaleDetailResource> resources = saleDetailMapper.modelListToPage(saleDetailService.getAll(saleId), pageable);
        return ApiController.ok(resources);
    }

    @PostMapping("")
    public ResponseEntity<Object> registerDetailBySaleId(@PathVariable("saleId") Long saleId, @RequestBody RegisterSaleDetailRequest request) {
        try {
            Result<SaleDetailResource, Notification> result = saleDetailService.register(saleId, request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

}
