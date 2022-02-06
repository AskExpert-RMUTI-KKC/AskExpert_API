package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_name")
public class UserName {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType. AUTO)
    private int userId;


    @Column(name = "user_name"/*,nullable = false*/)
    private String userName;

    @Column (name = "pass_word"/*,nullable = false*/)
    private String passWord;

    @Column(name = "first_name"/*,nullable = false*/)
    private String firstName;

    @Column(name = "last_name"/*,nullable = false*/)
    private String lastName;

    @Column (name = "e_mail", unique = true)
    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column (name = "join_date"/*,nullable = false*/)
    private Date joinDate;

    //@Column(length = 100)


    @Column (name = "profile_pic")
    private String profilePic;
}
