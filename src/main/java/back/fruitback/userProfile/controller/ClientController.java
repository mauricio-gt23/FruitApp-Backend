package back.fruitback.userProfile.controller;

import back.fruitback.shared.api.ApiController;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import back.fruitback.userProfile.domain.service.ClientService;
import back.fruitback.userProfile.resource.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Object> getByIdClient(@PathVariable("clientId") Long clientId) {
        try {
            ClientResource client = clientService.getById(clientId);
            return ApiController.ok(client);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> registerClient(@RequestBody RegisterClientRequest request) {
        try {
            Result<ClientResource, Notification> result = clientService.register(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<Object> editClient(@PathVariable("clientId") Long clientId, @RequestBody EditClientRequest request) {
        try {
            Result<ClientResource, Notification> result = clientService.edit(clientId, request);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

}
