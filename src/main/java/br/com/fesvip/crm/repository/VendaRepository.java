package br.com.fesvip.crm.repository;

import br.com.fesvip.crm.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
