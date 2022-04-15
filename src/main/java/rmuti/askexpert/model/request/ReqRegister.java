package rmuti.askexpert.model.request;

import lombok.Data;

@Data
public class ReqRegister {
    private String email;
    private String passWord;
    private String passWordForAdmin;
}
