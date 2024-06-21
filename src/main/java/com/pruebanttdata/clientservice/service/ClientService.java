package com.pruebanttdata.clientservice.service;

import com.pruebanttdata.clientservice.dto.ClientResponseDTO;
import com.pruebanttdata.clientservice.model.Client;
import com.pruebanttdata.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientResponseDTO getClientInfo(String documentType, String documentNumber) {
        // Validación de parámetros
        if (!isValidDocumentType(documentType)) {
            throw new IllegalArgumentException("Tipo de documento inválido: " + documentType);
        }
        
        Optional<Client> optionalClient = clientRepository.findByDocumentTypeAndDocumentNumber(documentType, documentNumber);
        
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            
            // Construir el DTO de respuesta
            ClientResponseDTO responseDTO = new ClientResponseDTO();
            responseDTO.setFirstName(client.getFirstName());
            responseDTO.setSecondName(client.getSecondName());
            responseDTO.setFirstSurname(client.getFirstSurname());
            responseDTO.setSecondSurname(client.getSecondSurname());
            responseDTO.setPhone(client.getPhone());
            responseDTO.setAddress(client.getAddress());
            responseDTO.setCity(client.getCity());
            
            return responseDTO;
        } else {
            return null; // Opcional: lanzar una excepción o manejar de otra manera si el cliente no se encuentra
        }
    }

    private boolean isValidDocumentType(String documentType) {
        return "C".equals(documentType) || "P".equals(documentType);
    }
}
