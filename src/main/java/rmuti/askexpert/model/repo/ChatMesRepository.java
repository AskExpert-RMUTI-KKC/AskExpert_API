package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.ChatMesData;

import java.util.List;

public interface ChatMesRepository extends JpaRepository<ChatMesData, String> {
}
