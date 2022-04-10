package rmuti.askexpert.model.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReqLogin {
    private String email;
    private String password;
}
