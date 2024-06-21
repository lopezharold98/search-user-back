package com.pruebanttdata.clientservice.controller;

import com.pruebanttdata.clientservice.dto.ClientResponseDTO;
import com.pruebanttdata.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/api/clients")
    public ResponseEntity<ClientResponseDTO> getClientInfo(
            @RequestParam("documentType") String documentType,
            @RequestParam("documentNumber") String documentNumber) {

        ClientResponseDTO clientInfo = clientService.getClientInfo(documentType, documentNumber);

        if (clientInfo != null) {
            return ResponseEntity.ok(clientInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
