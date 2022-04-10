package rmuti.askexpert.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import rmuti.askexpert.model.table.TopicData;

import javax.persistence.Column;
import java.util.Date;

@Data
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


    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getToken() {
        return token;
    }

    public void setToken(Double token) {
        this.token = token;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

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
}
