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

    @Column(name = "topic_report_status")
    private String topicReportStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column (name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    @Column(name = "topic_create_date")
    private String topicCreateDate;
    //private Timestamp topicCreateDate;

    private Date createdDate = new Date();


    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicHeadline() {
        return topicHeadline;
    }

    public void setTopicHeadline(String topicHeadline) {
        this.topicHeadline = topicHeadline;
    }

    public String getTopicCaption() {
        return topicCaption;
    }

    public void setTopicCaption(String topicCaption) {
        this.topicCaption = topicCaption;
    }

    public String getTopicOwnerId() {
        return topicOwnerId;
    }

    public void setTopicOwnerId(String topicOwnerId) {
        this.topicOwnerId = topicOwnerId;
    }

    public long getTopicLikeCount() {
        return topicLikeCount;
    }

    public void setTopicLikeCount(long topicLikeCount) {
        this.topicLikeCount = topicLikeCount;
    }

    public long getTopicCommentCount() {
        return topicCommentCount;
    }

    public void setTopicCommentCount(long topicCommentCount) {
        this.topicCommentCount = topicCommentCount;
    }

    public long getTopicReadCount() {
        return topicReadCount;
    }

    public void setTopicReadCount(long topicReadCount) {
        this.topicReadCount = topicReadCount;
    }

    public long getTopicDonateCount() {
        return topicDonateCount;
    }

    public void setTopicDonateCount(long topicDonateCount) {
        this.topicDonateCount = topicDonateCount;
    }

    public String getTopicGroup() {
        return topicGroup;
    }

    public void setTopicGroup(String topicGroup) {
        this.topicGroup = topicGroup;
    }

    public String getTopicReportStatus() {
        return topicReportStatus;
    }

    public void setTopicReportStatus(String topicReportStatus) {
        this.topicReportStatus = topicReportStatus;
    }

    public Date getCreatedDateForOrder() {
        return createdDateForOrder;
    }

    public void setCreatedDateForOrder(Date createdDateForOrder) {
        this.createdDateForOrder = createdDateForOrder;
    }

    public String getTopicCreateDate() {
        return topicCreateDate;
    }

    public void setTopicCreateDate(String topicCreateDate) {
        this.topicCreateDate = topicCreateDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
