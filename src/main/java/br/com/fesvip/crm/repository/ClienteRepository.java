package br.com.fesvip.crm.repository;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.entity.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAll(Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(c.numeroTelefone1) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(c.numeroTelefone2) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Cliente> findByNomeOrNumeroTelefoneOrEmail(String searchTerm, Pageable pageable);
}
