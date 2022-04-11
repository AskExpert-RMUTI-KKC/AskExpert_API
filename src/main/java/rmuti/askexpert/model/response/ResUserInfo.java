package rmuti.askexpert.model.response;

import lombok.Data;
@Data
public class ResUserInfo {
    private String userInfoId;
    private String userName;
    private String firstName;
    private String lastName;
    private Double token;
    private String profilePic;
    private boolean verifyStatus;
    private String expert;
}
