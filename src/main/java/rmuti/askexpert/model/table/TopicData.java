package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "topic_data")
public class TopicData {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "topic_id", length = 36, nullable = false, updatable = false)
    private String topicId;

    @Column(name = "topic_headline")
    private String topicHeadline;

    @Column(name = "topic_caption", length = 10240)
    private String topicCaption;

    @Column(name = "topic_userId", length = 36, nullable = false, updatable = false)
    private String topicUserId;

    @Column(name = "topic_like_count")
    private long topicLikeCount;

    @Column(name = "topic_comment_count")
    private long topicCommentCount;

    @Column(name = "topic_read_count")
    private long topicReadCount;

    @Column(name = "topic_donate_count")
    private long topicDonateCount;

    @Column(name = "topic_group")
    private String topicGroup;

    @Column(name = "topic_report_status",columnDefinition = "0 = ON,1 = OFF")
    private int topicReportStatus;


    @Column(name = "topic_report",columnDefinition = "R = report,N = no report")
    private char topicReport;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/* ,nullable = false */)
    private Date createdDateForOrder = new Date();;

    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    @Column(name = "topic_create_date")
    private String topicCreateDate;
    // private Timestamp topicCreateDate;

    private Date createdDate = new Date();

    // TODO: AFK
    /*
     * @ManyToOne
     * 
     * @JoinColumn(name = "user_info", nullable = false)
     * private UserInfoData userInfoData;
     */
}
