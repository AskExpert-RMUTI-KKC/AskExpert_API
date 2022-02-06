package rmuti.askexpert.model.table;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comment_data")
public class CommentData {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    @Column(name = "comment_owner")
    private String commentOwner; //userId

    @Column(name = "comment_like")
    private long commentLike;

    @Column(name = "comment_unlike")
    private long commentUnlike;

    @Column(name = "comment_reply_id")
    private String commentReplyID;

    //:TODO
//    @ManyToOne
//    @JoinColumn(name = "topic_id")
//    private TopicData topic;
}
