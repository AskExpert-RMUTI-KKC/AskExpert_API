package rmuti.askexpert.model.response;

import lombok.Data;

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

    private String topicGroup;

    private int topicReportStatus;



    private ResTopicUserInfo userInfoData;
}
