package rmuti.askexpert.model.request;

import lombok.Data; 

@Data 
public class ReqLogin {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    } 
}
