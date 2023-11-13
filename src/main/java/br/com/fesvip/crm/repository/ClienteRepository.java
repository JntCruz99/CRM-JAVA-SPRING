package br.com.fesvip.crm.repository;

import br.com.fesvip.crm.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
