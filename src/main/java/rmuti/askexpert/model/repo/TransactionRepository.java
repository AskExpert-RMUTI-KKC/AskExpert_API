package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.TransactionData;

public interface TransactionRepository  extends JpaRepository<TransactionData,String> {
}
