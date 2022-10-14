package back.fruitback.userProfile.domain.service;

import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import back.fruitback.userProfile.resource.*;

public interface ClientService {
    ClientResource getById(Long clientId);
    Result<ClientResource, Notification> register(RegisterClientRequest request);
    Result<ClientResource, Notification> edit(Long clientId, EditClientRequest request);
}
