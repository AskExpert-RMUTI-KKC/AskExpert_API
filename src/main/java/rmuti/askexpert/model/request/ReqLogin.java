package rmuti.askexpert.model.request;

import lombok.Data; 

@Data 
public class ReqLogin {
    private String email;
    private String passWord;
    private char mode;
    /*
        mode N = normal
        mode G = Google
        mode F = Facebook
    */
}
