package rmuti.askexpert.model.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comment_data")
public class CommentData {

    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid2")
    @Column(name = "comment_id",length = 36,nullable = false,updatable = false)
    private String commentId;

    @Column(name = "comment_topic_owner")
    private String commentTopicOwnerId; //userId

    @Column(name = "comment_ownerId")
    private String commentOwnerId; //userId

    @Column(name = "comment_like")
    private long commentLike;

    @Column(name = "comment_unlike")
    private long commentUnlike;

    @Column(name = "comment_caption")
    private String commentCaption;

//    @Column(name = "comment_reply_id")
//    private String commentReplyID;

    @Column(name = "comment_create_date")
    private String commentCreateDate;

    //:TODO
//    @ManyToOne
//    @JoinColumn(name = "topic_id")
//    private TopicData topic;
}
