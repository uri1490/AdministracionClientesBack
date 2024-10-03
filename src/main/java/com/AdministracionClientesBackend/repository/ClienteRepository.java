package com.AdministracionClientesBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.AdministracionClientesBackend.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
