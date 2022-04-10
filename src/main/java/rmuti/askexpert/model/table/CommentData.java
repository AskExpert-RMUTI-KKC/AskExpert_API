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
@Table(name = "comment_data")
public class CommentData {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "comment_id", length = 36, nullable = false, updatable = false)
    private String commentId;

    @Column(name = "comment_topic_owner")
    private String commentTopicOwnerId; //userId

    @Column(name = "comment_ownerId")
    private String commentOwnerId; //userId

    @Column(name = "comment_caption")
    private String commentCaption;

    @Column(name = "comment_is_sub_comment")
    private String commentIsSubComment;

    @Column(name = "comment_like_count")
    private String commentLikeCount;

    @Column(name = "comment_donate_count")
    private String commentDonateCount;

    @Column(name = "comment_report_status")
    private String commentReportStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column (name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();

}
