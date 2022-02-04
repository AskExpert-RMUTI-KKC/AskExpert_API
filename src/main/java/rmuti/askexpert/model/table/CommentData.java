package rmuti.askexpert.model.table;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Comment_data")
public class CommentData {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    @Column(name = "comment_topic_id")
    private String commentTopicId;

    @Column(name = "comment_owner")
    private String commentOwner; //userId

    @Column(name = "comment_like")
    private Date commentLike;

    @Column(name = "comment_unlike")
    private Date commentUnlike;

    @Column(name = "comment_reply")
    private Date commentReply;

    @Column(name = "comment_reply_id")
    private Date commentReplyID;
}
