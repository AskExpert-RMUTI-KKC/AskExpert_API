package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.tomcat.jni.Address;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "topic_data")
public class TopicData {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid2")
    @Column(name = "topic_id",length = 36,nullable = false,updatable = false)
    private String topicId;

    @Column(name = "topic_headline")
    private String topicHeadline;

    @Column(name = "topic_caption")
    private String topicCaption;

    @Column(name = "topic_owner",length = 36,nullable = false,updatable = false)
    private String topicOwnerId; //userId

    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    @Column(name = "topic_create_date")
    private String topicCreateDate;
    //private Timestamp topicCreateDate;

    @Column(name = "topic_like")
    private long topicLike;

    @Column(name = "topic_unlike")
    private long topicUnlike;

    @Column(name = "topic_comment_count")
    private long topicCommentCount;



    @Column(name = "topic_pic")
    private String topicPic;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column (name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();

}
