package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.tomcat.jni.Address;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "topic_data")
public class TopicData {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @Column(name = "topic_id",length = 36,nullable = false,updatable = false)
    private String topicId;

    @Column(name = "topic_headline")
    private String topicHeadline;

    @Column(name = "topic_caption")
    private String topicCaption;

    @Column(name = "topic_owner")
    private String topicOwner; //userId

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "topic_start_date")
    private Date topicStartDate;

    @Column(name = "topic_comment_count")
    private String topicCommentCount;

    @Column(name = "topic_like")
    private long topicLike;

    @Column(name = "topic_unlike")
    private long topicUnlike;

    @Column(name = "topic_group")
    private String topicGroup;

    //:TODO
//   @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<CommentData> comments;
}
