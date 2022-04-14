package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.VerifyData;

import java.util.List;

public interface VerifyDataRepository  extends JpaRepository<VerifyData,String> {
    List<VerifyData> findByVerifyPassOf(String id);

}
