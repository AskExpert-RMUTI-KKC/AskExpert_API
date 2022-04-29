package rmuti.askexpert.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import rmuti.askexpert.model.table.CommentData;
import rmuti.askexpert.model.table.TopicData;
import rmuti.askexpert.model.table.TransactionData;

import javax.persistence.Column;
import java.util.Date;

@Data
public class ResTransaction extends TransactionData {
    private ResTopicUserInfo userInfoDataTx;
    private ResTopicUserInfo userInfoDataRx;
    private String topicContent;
    private String topicId;
    private String commentContent;
    private String commentId;

    private String tranContentId;

    private String tranStatus;

    private Date createdDateForOrder;

    private Date createdDate;

    private CommentData commentData;

    private TopicData topicData;

}



