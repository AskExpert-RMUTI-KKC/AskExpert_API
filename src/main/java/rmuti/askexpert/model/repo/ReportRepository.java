package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.ReportData;

public interface ReportRepository extends JpaRepository<ReportData,String> {
}
