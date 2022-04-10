package rmuti.askexpert.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import rmuti.askexpert.model.table.TopicData;

import javax.persistence.Column;
import java.util.Date;

@Data
@Getter
@Setter
public class ResTopic  {
    private String userInfoId;

    private String userName;

    private String firstName;

    private String lastName;

    private Double token;

    private String profilePic;

    private String topicId;

    private String topicHeadline;

    private String topicCaption;

    private String topicOwnerId;

    private long topicLikeCount;

    private long topicCommentCount;

    private long topicReadCount;

    private long topicDonateCount;

    private String topicGroup;

    private String topicReportStatus;

//    private Date createdDateForOrder;
//
//    private String topicCreateDate;
//
//    private Date createdDate ;

}
