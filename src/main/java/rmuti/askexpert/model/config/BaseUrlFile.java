package rmuti.askexpert.model.config;

import lombok.Data;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Data
public class BaseUrlFile {


    private String domain = "http://localhost:8080";

    private String baseDir = "/uploads";

    private String imageTopicUrl = this.domain+this.baseDir+"/image/topic/";

    private String imageProfileUrl = this.baseDir+"/image/profile/";


    public String ipAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
