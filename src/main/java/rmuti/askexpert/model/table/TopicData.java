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
    @Column(name = "topic_id", length = 36, nullable = false, updatable = false, unique = true)
    private String topicId;

    @Column(name = "topic_headline", nullable = false)
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

    @Column(name = "topic_group_id")
    private String topicGroupId;

    @Column(name = "topic_report_status")
    private int topicReportStatus;
    // ,columnDefinition = "0 = ON,1 = OFF"
    // ,columnDefinition = "R = report,N = no report"

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
