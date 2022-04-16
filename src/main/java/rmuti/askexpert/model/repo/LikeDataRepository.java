package rmuti.askexpert.model.repo; 
import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.LikeData;

import java.util.List;
import java.util.Optional;

public interface LikeDataRepository extends JpaRepository<LikeData, String> {
    // long countAllByStatusIsTrue();
    // long countAllByContentIsFalse();
    // Optional<LikeData> findByContentIdAndUserId(String content,String id);
    // Optional<LikeData> findByContentIdAndUserId(String content,String id);
    Optional<LikeData> findByLikeUserIdAndLikeContentId(String id, String content);
    List<LikeData> findByLikeUserId(String userId);


}
