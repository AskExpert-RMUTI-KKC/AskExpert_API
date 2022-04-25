package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data; 
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data 
@Entity
@Table(name = "report_data")
public class ReportData {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @Column(length = 36,nullable = false,updatable = false, unique = true)
    private String reportId;

    @Column(name = "report_contentId",nullable = false, updatable = false,length = 36)
    private String reportContentId;//TOPIC_ID

    @Column(name = "report_content_type",nullable = false, updatable = false)
    private char reportContentType;

    @Column(name = "report_from",nullable = false, updatable = false,length = 36)
    private String reportFrom;

//    @Column(name = "report_type"/*,nullable = false*/)
//    private String reportType;

    @Column(name = "report_reason",nullable = false, updatable = false)
    private String reportReason;

    @Column(name = "report_who_decide"/*,nullable = false*/)
    private String reportWhoDecide;

//    @Column(name = "report_content_status"/*,nullable = false*/)
//    private String reportContentStatus;

    @Column(name = "report_status"/*,nullable = false*/)
    private char reportStatus;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();
}
