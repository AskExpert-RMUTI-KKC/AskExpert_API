package rmuti.askexpert.model.table;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "topic_data")
public class TopicData {
    @Id
    @Column(name = "topic_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int topicId;

    @Column(name = "topic_headline")
    private String topicHeadline;

    @Column(name = "topic_caption")
    private String topicCaption;

    @Column(name = "topic_owner")
    private String topicOwner; //userId

    @Column(name = "topic_start_date")
    private Date topicStartDate;

    @Column(name = "topic_comment_count")
    private Date topicCommentCount;

    @Column(name = "topic_like")
    private Date topicLike;

    @Column(name = "topic_unlike")
    private Date topicUnlike;

    @Column(name = "topic_group")
    private Date topicGroup;
}
