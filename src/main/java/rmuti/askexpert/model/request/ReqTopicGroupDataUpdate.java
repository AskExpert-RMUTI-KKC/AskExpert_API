package rmuti.askexpert.model.request;

import lombok.Data;

@Data
public class ReqTopicGroupDataUpdate {
    private String topicGroupId;
    private String topicGroupPath;
    private int topicGroupStatus;
}
