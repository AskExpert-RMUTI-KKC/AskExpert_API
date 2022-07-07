package rmuti.askexpert.model.mapper;

import org.mapstruct.Mapper;
import rmuti.askexpert.model.response.ResChatContact;
import rmuti.askexpert.model.response.ResChatMes;
import rmuti.askexpert.model.table.ChatContactData;
import rmuti.askexpert.model.table.ChatMesData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResChatMesMapper {
    ResChatMes toResChatMes(ChatMesData chatMesData);
    List<ResChatMes> toListResChatMes(List<ChatMesData> chatMesDataList);
}
