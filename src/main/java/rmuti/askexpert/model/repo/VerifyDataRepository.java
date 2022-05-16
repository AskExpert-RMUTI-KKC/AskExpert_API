package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.VerifyData;

import java.util.List;
import java.util.Optional;

public interface VerifyDataRepository  extends JpaRepository<VerifyData,String> {
    List<VerifyData> findByVerifyPassOf(String id);
    Optional<VerifyData> findByVerifyIdAndAndVerifyFrom(String verifyId,String userId);

    List<VerifyData> findByVerifyStatus(char status);

    Optional<VerifyData> findByVerifyFrom(String userId);

}
