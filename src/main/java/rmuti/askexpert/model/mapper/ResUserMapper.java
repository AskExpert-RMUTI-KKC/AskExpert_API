package rmuti.askexpert.model.mapper;

import org.mapstruct.Mapper;
import rmuti.askexpert.model.response.ResUserExpertVerify;
import rmuti.askexpert.model.table.UserInfoData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResUserMapper {
    ResUserExpertVerify toResUserExpertVerify(UserInfoData userInfoData);
    List<ResUserExpertVerify> toListResUserExpertVerify(List<UserInfoData> userInfoData);
}
