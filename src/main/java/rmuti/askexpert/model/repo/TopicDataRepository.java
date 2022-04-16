package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rmuti.askexpert.model.table.TopicData;
 
import java.util.List;
import java.util.Optional;

public interface TopicDataRepository extends JpaRepository<TopicData, String> {
    // List<TopicData> findAll();
    Optional<TopicData> findByTopicId(String id);

    Optional<TopicData> findByTopicIdAndTopicUserId(String topicId,String userId);

    List<TopicData> findAllByTopicUserIdOrderByTopicCreateDateDesc(String id);

    @Query("SELECT t from TopicData t WHERE t.topicReportStatus = :report")
    List<TopicData> findNotReportTopic(@Param("report") int report);

    List<TopicData> findAllByTopicReportStatus(int Report);

}
