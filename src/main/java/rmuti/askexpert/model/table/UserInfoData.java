package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "user_info")
public class UserInfoData {
    @Id
    @Column(length = 36,nullable = false,updatable = false)
    private String userInfoId;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "first_name"/*,nullable = false*/)
    private String firstName;

    @Column(name = "last_name"/*,nullable = false*/)
    private String lastName;

    @Column(name = "token"/*,nullable = false*/)
    private Double token;

    @Column(name = "profile_pic")
    private String profilePic;


//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Column (name = "join_date"/*,nullable = false*/)
//    private Date joinDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column (name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();


}
