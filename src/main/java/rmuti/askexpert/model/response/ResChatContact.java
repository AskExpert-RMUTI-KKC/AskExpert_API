package rmuti.askexpert.model.response;

import lombok.Data;
import rmuti.askexpert.model.table.ChatContactData;

@Data
public class ResChatContact extends ChatContactData {
    //private ResUserExpertVerify userInfoDataTx;
    private ResUserExpertVerify userInfoDataRx;

    //TODO constructor
//    public ResChatContact(){
//
//    }
}
