package rmuti.askexpert.model.response;

import javax.persistence.Column;

public class ResComment {
    private String commentId;

    private String commentTopicOwnerId; //userId

    private String commentOwnerId; //userId

    private String commentCaption;

    private int commentIsSubComment;

    private int commentLikeCount;

    private int commentDonateCount;

    private int commentReportStatus;
}
