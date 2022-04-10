package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.VerifyData;

public interface VerifyRepository  extends JpaRepository<VerifyData,String> {
}
