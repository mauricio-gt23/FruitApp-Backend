package back.fruitback.userProfile.service;

import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import back.fruitback.userProfile.domain.model.Client;
import back.fruitback.userProfile.domain.persistence.ClientRepository;
import back.fruitback.userProfile.domain.service.ClientService;
import back.fruitback.userProfile.mapping.ClientMapper;
import back.fruitback.userProfile.resource.ClientResource;
import back.fruitback.userProfile.resource.EditClientRequest;
import back.fruitback.userProfile.resource.RegisterClientRequest;
import back.fruitback.userProfile.validator.EditClientValidator;
import back.fruitback.userProfile.validator.RegisterClientValidator;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final RegisterClientValidator registerClientValidator;
    private final EditClientValidator editClientValidator;
    private final ClientMapper mapper;

    public ClientServiceImpl(ClientRepository clientRepository, RegisterClientValidator registerClientValidator, EditClientValidator editClientValidator, ClientMapper mapper) {
        this.clientRepository = clientRepository;
        this.registerClientValidator = registerClientValidator;
        this.editClientValidator = editClientValidator;
        this.mapper = mapper;
    }

    @Override
    public ClientResource getById(Long clientId) {
        return mapper.toResource(clientRepository.getReferenceById(clientId));
    }

    @Override
    public Result<ClientResource, Notification> register(RegisterClientRequest request) {

        Notification notification = this.registerClientValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        Client client = new Client(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                request.getLastName(),
                request.getNumber(),
                request.getAddress()
        );
        clientRepository.save(client);

        ClientResource response = mapper.toResource(client);
        return Result.success(response);
    }

    @Override
    public Result<ClientResource, Notification> edit(Long clientId, EditClientRequest request) {
        Notification notification = this.editClientValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        String email = clientRepository.findById(clientId).get().getEmail();

        clientRepository.findById(clientId).map(client -> {
            client.setName(request.getName());
            client.setLastName(request.getLastName());
            client.setNumber(request.getNumber());
            client.setPassword(request.getPassword());
            client.setAddress(request.getAddress());
            clientRepository.save(client);
            return null;
        });

        Client client = new Client(
                clientId,
                email,
                request.getPassword(),
                request.getName(),
                request.getLastName(),
                request.getNumber(),
                request.getAddress()
        );

        ClientResource response = mapper.toResource(client);
        return Result.success(response);
    }
}
