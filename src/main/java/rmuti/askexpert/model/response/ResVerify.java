package rmuti.askexpert.model.response;

import lombok.Data;
import rmuti.askexpert.model.table.ExpertGroupListData;
import rmuti.askexpert.model.table.ImageData;
import rmuti.askexpert.model.table.UserInfoData;

@Data
public class ResVerify {
    private String verifyId;

    private String verifyFrom;// userId

    private String verifyExpert;

    private String verifyPassOf;// userIdAdmin

    private char verifyStatus;

    private UserInfoData userInfoData;

    private ExpertGroupListData expertGroupListData;

    private ImageData imageData;
}
