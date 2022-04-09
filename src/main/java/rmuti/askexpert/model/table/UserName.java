package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_name")
public class UserName {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @Column(length = 36,nullable = false,updatable = false)
    private String userId;

    @Column (name = "e_mail", unique = true,nullable = false)
    private String email;

    @Column (name = "pass_word")
    private String passWord;

    @Column (name = "pass_word_fb")
    private String passWordFb;

    @Column (name = "pass_word_google")
    private String passWordGoogle;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column (name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWordFb() {
        return passWordFb;
    }

    public void setPassWordFb(String passWordFb) {
        this.passWordFb = passWordFb;
    }

    public String getPassWordGoogle() {
        return passWordGoogle;
    }

    public void setPassWordGoogle(String passWordGoogle) {
        this.passWordGoogle = passWordGoogle;
    }

    public Date getCreatedDateForOrder() {
        return createdDateForOrder;
    }

    public void setCreatedDateForOrder(Date createdDateForOrder) {
        this.createdDateForOrder = createdDateForOrder;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    //@Column(length = 100)
    /*,nullable = false*/
}
