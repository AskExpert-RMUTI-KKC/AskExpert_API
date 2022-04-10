package rmuti.askexpert.model.response.mapperdiy;

import rmuti.askexpert.model.response.ResTopic;
import rmuti.askexpert.model.table.TopicData;

import java.util.ArrayList;
import java.util.List;

public class ResTopicMap {
    public List<ResTopic> toResTopicListMapper(List<TopicData> topicList){
        if (topicList == null) {
            return null;
        }

        List<ResTopic> res = new ArrayList<ResTopic>(topicList.size());
        for (TopicData topicData : topicList) {
            res.add(toResTopic(topicData));
        }
        return res;
    }
    public  ResTopic toResTopic(TopicData topicData){
        ResTopic res = new ResTopic();


        res.setTopicId(topicData.getTopicId());
        res.setTopicHeadline(topicData.getTopicHeadline());
        res.setTopicCaption(topicData.getTopicCaption());
        res.setTopicOwnerId(topicData.getTopicOwnerId());
        res.setTopicLikeCount(topicData.getTopicLikeCount());
        res.setTopicCommentCount(topicData.getTopicCommentCount());
        res.setTopicReadCount(topicData.getTopicReadCount());
        res.setTopicDonateCount(topicData.getTopicDonateCount());
        res.setTopicGroup(topicData.getTopicGroup());
        res.setTopicReportStatus(topicData.getTopicReportStatus());
        return res;
    }
}
