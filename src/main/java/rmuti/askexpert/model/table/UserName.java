package rmuti.askexpert.model.table;

import lombok.Data;

import javax.persistence.*;

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
}
