package br.com.gabrielcaiodev.manytoone_onetomany.model.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}