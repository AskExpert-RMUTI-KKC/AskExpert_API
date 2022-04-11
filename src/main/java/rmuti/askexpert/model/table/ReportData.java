package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "report_data")
public class ReportData {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @Column(length = 36,nullable = false,updatable = false)
    private String reportId;

    @Column(name = "report_contentId"/*,nullable = false*/)
    private String reportContentId;

    @Column(name = "report_from"/*,nullable = false*/)
    private String reportFrom;

    @Column(name = "report_type"/*,nullable = false*/)
    private String reportType;

    @Column(name = "report_reason"/*,nullable = false*/)
    private String reportReason;

    @Column(name = "report_decide"/*,nullable = false*/)
    private String reportDecide;

    @Column(name = "report_content_status"/*,nullable = false*/)
    private String reportContentStatus;

    @Column(name = "report_status"/*,nullable = false*/)
    private String report_status;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();
}
