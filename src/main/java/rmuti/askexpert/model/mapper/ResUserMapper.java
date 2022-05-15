package rmuti.askexpert.model.mapper;

import org.mapstruct.Mapper;
import rmuti.askexpert.model.response.ResUserExpertVerify;
import rmuti.askexpert.model.table.UserInfoData;
@Mapper(componentModel = "spring")
public interface ResUserMapper {
    ResUserExpertVerify toResUserExpertVerify(UserInfoData userInfoData);
}
