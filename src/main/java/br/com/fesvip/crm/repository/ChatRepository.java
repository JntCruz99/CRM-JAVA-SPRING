package br.com.fesvip.crm.repository;

import br.com.fesvip.crm.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
