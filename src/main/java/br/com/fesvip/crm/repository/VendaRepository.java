package br.com.fesvip.crm.repository;

import br.com.fesvip.crm.entity.Venda;
import br.com.fesvip.crm.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    Page<Venda> findAll(Pageable pageable);

    Page<Venda> findByDataAfter(LocalDateTime startDate, Pageable pageable);

    Page<Venda> findByUsuarioUsernameAndDataAfter(String username, LocalDateTime startDate, Pageable pageable);

    Page<Venda> findByStatus(Status status, Pageable pageable);
}
