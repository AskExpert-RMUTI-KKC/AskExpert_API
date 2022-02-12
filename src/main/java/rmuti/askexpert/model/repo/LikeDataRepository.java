package rmuti.askexpert.model.repo;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.LikeData;

import java.util.Optional;

public interface LikeDataRepository extends JpaRepository<LikeData,String> {
    long countAllByContentIsTrue();
    long countAllByContentIsFalse();
    Optional<LikeData> findByLikeIdAndLikeOwner(String content,String id);

}
