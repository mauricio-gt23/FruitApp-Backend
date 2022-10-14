package back.fruitback.services.domain.service;

import back.fruitback.services.domain.model.Fruit;
import back.fruitback.services.resource.EditFruitRequest;
import back.fruitback.services.resource.FruitResource;
import back.fruitback.services.resource.RegisterFruitRequest;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;

import java.util.List;

public interface FruitService {
    List<Fruit> getAll();
    FruitResource getById(Long fruitId);
    List<Fruit> getByName(String name);
    Result<FruitResource, Notification> register(RegisterFruitRequest request);
    Result<FruitResource, Notification> edit(Long fruitId, EditFruitRequest request);
}
