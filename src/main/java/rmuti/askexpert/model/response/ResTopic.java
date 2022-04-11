package rmuti.askexpert.model.response;

import lombok.Data;

@Data
public class ResTopic  {

    private ResUserInfo userInfoData;

    //user
    private String userInfoId;

    private String userName;

    private String firstName;

    private String lastName;

    private Double token;

    private String profilePic;

    //topic
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

    // TODO:Userlike
    // TODO:Usercomment
    // TODO:Userdonate


//    private Date createdDateForOrder;
//
//    private String topicCreateDate;
//
//    private Date createdDate ;
}
