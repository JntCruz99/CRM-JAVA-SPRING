package br.com.fesvip.crm.repository;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.entity.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAll(Pageable pageable);
}
