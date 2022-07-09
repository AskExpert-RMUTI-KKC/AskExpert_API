package rmuti.askexpert.model.response;

import lombok.Data;
import rmuti.askexpert.model.table.UserInfoData;

import javax.persistence.Column;

@Data
public class ResTopicUserInfo extends UserInfoData {

    private String expert;

}
