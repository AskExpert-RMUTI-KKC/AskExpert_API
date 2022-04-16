package rmuti.askexpert.model.config;

import lombok.Data;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Data
public class BaseUrlFile {


    private String domain = "http://localhost:8080";

    //!user before baseDir**


    //** must use first
    private String PathSet = "/";

    private String baseDir = "uploads";

    //private String imageTopicUrl = this.domain+this.baseDir+"/image/topic/";

    //if change update in main too
    private String imageTopicUrl = this.baseDir+"/imgTopic";
    private String imageTopic = "/imgTopic";
    private String imageProfileUrl = this.baseDir+"/imgProfile";
    private String imageProfile = "/imgProfile";
    private String imageCommentUrl = this.baseDir+"/imgComment";
    private String imageComment = "/imgComment";
    private String imageVerifyUrl = this.baseDir+"/imgVerify";
    private String imageVerify = "/imgVerify";
    public String ipAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
