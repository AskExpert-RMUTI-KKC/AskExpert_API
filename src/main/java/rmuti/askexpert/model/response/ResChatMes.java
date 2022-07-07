package rmuti.askexpert.model.response;

import lombok.Data;
import rmuti.askexpert.model.table.ChatMesData;

@Data
public class ResChatMes extends ChatMesData {
    private ResUserExpertVerify userInfoDataTx;
    //private ResUserExpertVerify userInfoDataRx;
}
