package back.fruitback.services.domain.service;

import back.fruitback.services.domain.model.Juice;
import back.fruitback.services.resource.EditJuiceRequest;
import back.fruitback.services.resource.JuiceResource;
import back.fruitback.services.resource.RegisterJuiceRequest;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;

import java.util.List;

public interface JuiceService {
    List<Juice> getAll();
    JuiceResource getById(Long juiceId);
    List<Juice> getByName(String name);
    Result<JuiceResource, Notification> register(RegisterJuiceRequest request);
    Result<JuiceResource, Notification> edit(Long juiceId, EditJuiceRequest request);
}
