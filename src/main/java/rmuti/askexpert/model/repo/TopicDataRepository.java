package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.TopicData;

import java.util.List;

public interface TopicDataRepository extends JpaRepository<TopicData, Integer> {
    //List<TopicData> findAll();
}
