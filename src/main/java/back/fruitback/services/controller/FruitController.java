package back.fruitback.services.controller;

import back.fruitback.services.domain.service.FruitService;
import back.fruitback.services.mapping.FruitMapper;
import back.fruitback.services.resource.*;
import back.fruitback.shared.api.ApiController;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/fruits")
public class FruitController {

    private final FruitService fruitService;
    private final FruitMapper fruitMapper;

    public FruitController(FruitService fruitService, FruitMapper fruitMapper) {
        this.fruitService = fruitService;
        this.fruitMapper = fruitMapper;
    }

    @GetMapping()
    public ResponseEntity<Object> getAllFruit(Pageable pageable) {
        Page<FruitResource> resources = fruitMapper.modelListToPage(fruitService.getAll(), pageable);
        return ApiController.ok(resources);
    }

    @GetMapping("/{fruitId}")
    public ResponseEntity<Object> getByIdFruit(@PathVariable("fruitId") Long fruitId) {
        try {
            FruitResource fruit = fruitService.getById(fruitId);
            return ApiController.ok(fruit);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @GetMapping(params = "name")
    public ResponseEntity<Object> getByNameFruit(@RequestParam(value = "name", required = false) String name, Pageable pageable) {
        try {
            Page<FruitResource> resources = fruitMapper.modelListToPage(fruitService.getByName(name), pageable);
            return ApiController.ok(resources);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> registerFruit(@RequestBody RegisterFruitRequest request) {
        try {
            Result<FruitResource, Notification> result = fruitService.register(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/update/{fruitId}")
    public ResponseEntity<Object> editFruit(@PathVariable("fruitId") Long fruitId, @RequestBody EditFruitRequest request) {
        try {
            Result<FruitResource, Notification> result = fruitService.edit(fruitId, request);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

}
