package rmuti.askexpert.model.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import rmuti.askexpert.model.table.ImageData;
import lombok.Data;
import rmuti.askexpert.model.table.TopicGroupListData;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;


@Data
public class ResTopic {

    private int likeStatus;
    // topic
    private String topicId;

    private String topicHeadline;

    private String topicCaption;

    private String topicUserId;

    private long topicLikeCount;

    private long topicCommentCount;

    private long topicReadCount;

    private long topicDonateCount;

    private String topicGroupId;

    private String topicGroupName;

    private int topicReportStatus;

    private List<ImageData> topicImg;

    private ResTopicUserInfo userInfoData;

    private Date createdDateForOrder;

    private String topicCreateDate;

    private Date createdDate;
}
