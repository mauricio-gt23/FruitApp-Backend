package back.fruitback.userProfile.domain.service;

import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import back.fruitback.userProfile.resource.AdminResource;
import back.fruitback.userProfile.resource.EditAdminRequest;
import back.fruitback.userProfile.resource.RegisterAdminRequest;

public interface AdminService {
    AdminResource getById(Long adminId);
    Result<AdminResource, Notification> register(RegisterAdminRequest request);
    Result<AdminResource, Notification> edit(Long adminId, EditAdminRequest request);
}
