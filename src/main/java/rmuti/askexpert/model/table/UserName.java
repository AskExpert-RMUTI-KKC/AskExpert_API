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

    @Column(name = "role")
    private String role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column (name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();
}
