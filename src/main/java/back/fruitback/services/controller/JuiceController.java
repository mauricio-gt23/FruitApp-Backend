package back.fruitback.services.controller;

import back.fruitback.services.domain.service.JuiceService;
import back.fruitback.services.mapping.JuiceMapper;
import back.fruitback.services.resource.*;
import back.fruitback.shared.api.ApiController;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/juices")
public class JuiceController {

    private final JuiceService juiceService;
    private final JuiceMapper juiceMapper;

    public JuiceController(JuiceService juiceService, JuiceMapper juiceMapper) {
        this.juiceService = juiceService;
        this.juiceMapper = juiceMapper;
    }

    @GetMapping()
    public ResponseEntity<Object> getAllJuice(Pageable pageable) {
        Page<JuiceResource> resources = juiceMapper.modelListToPage(juiceService.getAll(), pageable);
        return ApiController.ok(resources);
    }

    @GetMapping("/{juiceId}")
    public ResponseEntity<Object> getByIdJuice(@PathVariable("juiceId") Long juiceId) {
        try {
            JuiceResource juice = juiceService.getById(juiceId);
            return ApiController.ok(juice);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @GetMapping(params = "name")
    public ResponseEntity<Object> getByNameJuice(@RequestParam(value = "name", required = false) String name, Pageable pageable) {
        try {
            Page<JuiceResource> resources = juiceMapper.modelListToPage(juiceService.getByName(name), pageable);
            return ApiController.ok(resources);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> registerJuice(@RequestBody RegisterJuiceRequest request) {
        try {
            Result<JuiceResource, Notification> result = juiceService.register(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/update/{juiceId}")
    public ResponseEntity<Object> editJuice(@PathVariable("juiceId") Long juiceId, @RequestBody EditJuiceRequest request) {
        try {
            Result<JuiceResource, Notification> result = juiceService.edit(juiceId, request);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
