package rmuti.askexpert.model.mapper;

import org.mapstruct.Mapper;
import rmuti.askexpert.model.response.ResComment;
import rmuti.askexpert.model.response.ResTopicUserInfo;
import rmuti.askexpert.model.table.CommentData;
import rmuti.askexpert.model.table.UserInfoData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResCommentMapper {
    List<ResComment> toListResComment(List<CommentData> commentData);
    ResTopicUserInfo toResTopicUserInfo(UserInfoData userInfoData);
}
