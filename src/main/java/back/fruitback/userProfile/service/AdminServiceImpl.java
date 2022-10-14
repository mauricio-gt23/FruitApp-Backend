package back.fruitback.userProfile.service;

import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import back.fruitback.userProfile.domain.model.Admin;
import back.fruitback.userProfile.domain.persistence.AdminRepository;
import back.fruitback.userProfile.domain.service.AdminService;
import back.fruitback.userProfile.mapping.AdminMapper;
import back.fruitback.userProfile.resource.AdminResource;
import back.fruitback.userProfile.resource.EditAdminRequest;
import back.fruitback.userProfile.resource.RegisterAdminRequest;
import back.fruitback.userProfile.validator.EditAdminValidator;
import back.fruitback.userProfile.validator.RegisterAdminValidator;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final RegisterAdminValidator registerAdminValidator;
    private final EditAdminValidator editAdminValidator;
    private final AdminMapper mapper;

    public AdminServiceImpl(AdminRepository adminRepository, RegisterAdminValidator registerAdminValidator, EditAdminValidator editAdminValidator, AdminMapper mapper) {
        this.adminRepository = adminRepository;
        this.registerAdminValidator = registerAdminValidator;
        this.editAdminValidator = editAdminValidator;
        this.mapper = mapper;
    }

    @Override
    public AdminResource getById(Long adminId) {
        return mapper.toResource(adminRepository.getReferenceById(adminId));
    }

    @Override
    public Result<AdminResource, Notification> register(RegisterAdminRequest request) {

        Notification notification = this.registerAdminValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        Admin admin = new Admin(
                request.getEmail(),
                request.getPassword(),
                request.getAdminName(),
                request.getAdminLastName(),
                request.getNumber()
        );
        adminRepository.save(admin);

        AdminResource response = mapper.toResource(admin);
        return Result.success(response);
    }

    @Override
    public Result<AdminResource, Notification> edit(Long adminId, EditAdminRequest request) {
        Notification notification = this.editAdminValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        String email = adminRepository.findById(adminId).get().getEmail();

        adminRepository.findById(adminId).map(admin -> {
            admin.setAdminName(request.getAdminName());
            admin.setAdminLastName(request.getAdminLastName());
            admin.setNumber(request.getNumber());
            admin.setPassword(request.getPassword());
            adminRepository.save(admin);
            return null;
        });

        Admin admin = new Admin(
                adminId,
                email,
                request.getPassword(),
                request.getAdminName(),
                request.getAdminLastName(),
                request.getNumber()
        );

        AdminResource response = mapper.toResource(admin);
        return Result.success(response);
    }
}
