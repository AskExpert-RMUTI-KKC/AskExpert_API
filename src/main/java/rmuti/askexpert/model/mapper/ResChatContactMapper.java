package rmuti.askexpert.model.mapper;

import org.mapstruct.Mapper;
import rmuti.askexpert.model.response.ResChatContact;
import rmuti.askexpert.model.table.ChatContactData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResChatContactMapper {
    ResChatContact toResChatContact(ChatContactData chatContactData);
    List<ResChatContact> toListResChatContact(List<ChatContactData> chatContactDataList);
}
