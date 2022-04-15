package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.ExpertGroupListData;

public interface ExpertGroupDataRepository extends JpaRepository<ExpertGroupListData, String> {
}
