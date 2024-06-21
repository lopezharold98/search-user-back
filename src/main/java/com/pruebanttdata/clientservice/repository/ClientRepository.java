package com.pruebanttdata.clientservice.repository;

import com.pruebanttdata.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
}
