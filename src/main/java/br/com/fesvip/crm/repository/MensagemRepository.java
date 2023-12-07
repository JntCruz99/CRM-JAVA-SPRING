package br.com.fesvip.crm.repository;

import br.com.fesvip.crm.entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}
