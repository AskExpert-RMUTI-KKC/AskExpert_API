package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comment_data")
public class CommentData {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "comment_id", length = 36, nullable = false, updatable = false, unique = true)
    private String commentId;

    @Column(name = "comment_topicId",length = 36,nullable = false, updatable = false)
    private String commentContentId; // TopicId // CommentId

    @Column(name = "comment_userId",length = 36,nullable = false, updatable = false)
    private String commentUserId; // userId

    @Column(name = "comment_caption", length = 10240,nullable = false)
    private String commentCaption;

    @Column(name = "comment_is_sub_comment")
    private int commentIsSubComment;

    @Column(name = "comment_like_count")
    private int commentLikeCount;

    @Column(name = "comment_donate_count")
    private int commentDonateCount;

    @Column(name = "comment_report_status")
    private int commentReportStatus;
    // ,columnDefinition = "0 = ON,1 = OFF"

//    @Column(name = "comment_report")
//    private char commentReport;
    // ,columnDefinition = "R = report,N = no report"

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/* ,nullable = false */)
    private Date createdDateForOrder = new Date();;

    private Date createdDate = new Date();
}
