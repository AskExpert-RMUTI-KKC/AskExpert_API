package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.ExpertGroupListData;

import java.util.List;

public interface ExpertGroupDataRepository extends JpaRepository<ExpertGroupListData, String> {
    List<ExpertGroupListData> findByExpertStatus(int status);
}
