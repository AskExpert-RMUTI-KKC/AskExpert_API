package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.ReportData;
import rmuti.askexpert.model.table.VerifyData;

import java.util.List;

public interface ReportDataRepository extends JpaRepository<ReportData,String> {
    List<ReportData> findByReportContentId(String contentId);
}
