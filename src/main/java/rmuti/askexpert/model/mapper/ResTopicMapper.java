package rmuti.askexpert.model.mapper;

import org.mapstruct.Mapper;
import rmuti.askexpert.model.response.ResTopic;
import rmuti.askexpert.model.table.TopicData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResTopicMapper {
    List<ResTopic> toListResTopic(List<TopicData> topicData);
}
