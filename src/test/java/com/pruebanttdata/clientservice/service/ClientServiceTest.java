package com.pruebanttdata.clientservice.service;

import com.pruebanttdata.clientservice.dto.ClientResponseDTO;
import com.pruebanttdata.clientservice.model.Client;
import com.pruebanttdata.clientservice.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClientInfo_Success() {
        // Arrange
        String documentType = "C";
        String documentNumber = "12345678";

        Client client = new Client();
        client.setDocumentType(documentType);
        client.setDocumentNumber(documentNumber);
        client.setFirstName("John");
        client.setFirstSurname("Doe");
    
        when(clientRepository.findByDocumentTypeAndDocumentNumber(documentType, documentNumber))
                .thenReturn(Optional.of(client));

        ClientResponseDTO responseDTO = clientService.getClientInfo(documentType, documentNumber);

        assertNotNull(responseDTO);
        assertEquals(client.getFirstName(), responseDTO.getFirstName());
        assertEquals(client.getFirstSurname(), responseDTO.getFirstSurname());
    }

    @Test
    void testGetClientInfo_InvalidDocumentType() {
        // Arrange
        String documentType = "InvalidType";
        String documentNumber = "12345678";

        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> clientService.getClientInfo(documentType, documentNumber));

        assertEquals("Tipo de documento inválido: " + documentType, exception.getMessage());

        
        verify(clientRepository, never()).findByDocumentTypeAndDocumentNumber(anyString(), anyString());
    }

}
