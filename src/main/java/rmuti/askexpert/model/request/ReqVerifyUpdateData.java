package rmuti.askexpert.model.request;

import lombok.Data;

@Data
public class ReqVerifyUpdateData {
    private String verifyId;
    private boolean isVerified;
    private String verifyUserId;
}
