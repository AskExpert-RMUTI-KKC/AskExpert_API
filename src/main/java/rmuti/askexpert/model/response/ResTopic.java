package rmuti.askexpert.model.response;

import lombok.Data;
import rmuti.askexpert.model.table.TopicData;

@Data
public class ResTopic extends TopicData {
    String userName;
    String profileImg;

}
