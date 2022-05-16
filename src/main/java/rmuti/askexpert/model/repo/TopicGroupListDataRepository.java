package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.ExpertGroupListData;
import rmuti.askexpert.model.table.TopicGroupListData;

import java.util.List;

public interface TopicGroupListDataRepository extends JpaRepository<TopicGroupListData, String> {
    List<TopicGroupListData> findByTopicGroupStatus(int status);
}
