package rmuti.askexpert.model.response;

import lombok.Data; 

@Data
public class ResTopicUserInfo {
    private String userInfoId;

    private String userName;

    private String firstName;

    private String lastName;

    private String profilePic;

    private boolean verifyStatus;

    private String expert;

    private String userCaption;
}
