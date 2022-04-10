//package rmuti.askexpert.model.response;
//
//import org.springframework.stereotype.Component;
//import rmuti.askexpert.model.table.TopicData;
//
//import javax.annotation.processing.Generated;
//import java.util.ArrayList;
//import java.util.List;
//
//@Generated(
//        value = "org.mapstruct.ap.MappingProcessor",
//        date = "2022-04-09T18:49:39+0700",
//        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
//)
//@Component
//public class ResTopicMapperImpl implements ResTopicMapper {
//
//    private final ResTopicMapper resTopicMapper;
//
//    public ResTopicMapperImpl(ResTopicMapper resTopicMapper) {
//        this.resTopicMapper = resTopicMapper;
//    }
//
//    @Override
//    public List<ResTopic> toListTopicResponse(List<TopicData> topicList) {
//        if (topicList == null) {
//            return null;
//        }
//
//        List<ResTopic> list = new ArrayList<ResTopic>(topicList.size());
//        for (TopicData topicData : topicList) {
//            list.add(toTopicResponse(topicData));
//        }
//
//        return list;
//    }
//
//    @Override
//    public ResTopic toTopicResponse(TopicData topicData) {
//        if (topicData == null) {
//            return null;
//        }
//
//        ResTopic resTopic = new ResTopic();
//
//        resTopic.setTopicId(resTopic.getTopicId());
//        resTopic.setTopicHeadline(resTopic.getTopicHeadline());
//        resTopic.setTopicCaption(resTopic.getTopicCaption());
//        resTopic.setTopicOwnerId(resTopic.getTopicOwnerId());
//        resTopic.setTopicLikeCount(resTopic.getTopicLikeCount());
//        resTopic.setTopicCommentCount(resTopic.getTopicCommentCount());
//        resTopic.setTopicReadCount(resTopic.getTopicReadCount());
//        resTopic.setTopicDonateCount(resTopic.getTopicDonateCount());
//        resTopic.setTopicGroup(resTopic.getTopicGroup());
//        resTopic.setTopicReportStatus(resTopic.getTopicReportStatus());
//
//        return resTopic;
//
//    }
//
//    @Override
//    public List<ResTopic> map(List<TopicData> topicList) {
//        return resTopicMapper.map(topicList);
//    }
//}
