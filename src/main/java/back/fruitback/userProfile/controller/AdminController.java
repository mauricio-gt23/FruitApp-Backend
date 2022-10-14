package back.fruitback.userProfile.controller;

import back.fruitback.shared.api.ApiController;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import back.fruitback.userProfile.domain.service.AdminService;
import back.fruitback.userProfile.resource.AdminResource;
import back.fruitback.userProfile.resource.EditAdminRequest;
import back.fruitback.userProfile.resource.RegisterAdminRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/{adminId}")
    public ResponseEntity<Object> getByIdAdmin(@PathVariable("adminId") Long adminId) {
        try {
            AdminResource admin = adminService.getById(adminId);
            return ApiController.ok(admin);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> registerAdmin(@RequestBody RegisterAdminRequest request) {
        try {
            Result<AdminResource, Notification> result = adminService.register(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/update/{adminId}")
    public ResponseEntity<Object> editAdmin(@PathVariable("adminId") Long adminId, @RequestBody EditAdminRequest request) {
        try {
            Result<AdminResource, Notification> result = adminService.edit(adminId, request);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

}
