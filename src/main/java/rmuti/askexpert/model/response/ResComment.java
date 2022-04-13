package rmuti.askexpert.model.response;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class ResComment {
    private byte likeStatus;

    private ResComment commentData;

    private String commentId;

    private String commentContentId; // userId

    private String commentUserId; // userId

    private String commentCaption;

    private int commentIsSubComment;

    private int commentLikeCount;

    private int commentDonateCount;

    private int commentReportStatus;

    private ResTopicUserInfo userInfoData;

    private List<ResComment> subComment;
}
