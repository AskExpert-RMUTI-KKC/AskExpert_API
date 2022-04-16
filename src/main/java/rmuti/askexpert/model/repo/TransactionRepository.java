package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.TransactionData;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<TransactionData,String> {
    List<TransactionData> findByTranTxOrTranRxOrderByCreatedDateDesc(String tx,String rx);
    List<TransactionData> findByTranTx(String idTx);
}
