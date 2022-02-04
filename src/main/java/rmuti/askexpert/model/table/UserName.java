package rmuti.askexpert.model.table;

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

    @Column(name = "user_name")
    private String userName;

    @Column (name = "pass_word")
    private String passWord;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column (name = "e_mail")
    private String eMail;

    @Column (name = "join_date")
    private Date joinDate;

    @Column (name = "profile_pic")
    private String profilePic;
}
