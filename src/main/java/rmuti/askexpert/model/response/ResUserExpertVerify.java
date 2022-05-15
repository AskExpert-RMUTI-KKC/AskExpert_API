package rmuti.askexpert.model.response;


import lombok.Data;
import rmuti.askexpert.model.table.ExpertGroupListData;
import rmuti.askexpert.model.table.UserInfoData;
import rmuti.askexpert.model.table.VerifyData;

@Data
public class ResUserExpertVerify extends UserInfoData {
    private ExpertGroupListData expertGroupListData;
    private VerifyData verifyData;
}
