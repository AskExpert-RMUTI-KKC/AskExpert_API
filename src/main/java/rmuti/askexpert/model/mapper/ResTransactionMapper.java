package rmuti.askexpert.model.mapper;

import org.mapstruct.Mapper;
import rmuti.askexpert.model.response.ResTopic;
import rmuti.askexpert.model.response.ResTopicUserInfo;
import rmuti.askexpert.model.response.ResTransaction;
import rmuti.askexpert.model.response.ResTransactionUserInfo;
import rmuti.askexpert.model.table.TopicData;
import rmuti.askexpert.model.table.TransactionData;
import rmuti.askexpert.model.table.UserInfoData;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ResTransactionMapper {
    List<ResTransaction> toListResTransaction(List<TransactionData> topicData);
    ResTransactionUserInfo toResTransactionUserInfo(UserInfoData userInfoData);
}
