package rmuti.askexpert.model.response;

import lombok.Data;
import rmuti.askexpert.model.table.TransactionData;

@Data
public class ResTransaction extends TransactionData {
    private ResTopicUserInfo userInfoDataTx;
    private ResTopicUserInfo userInfoDataRx;
}
