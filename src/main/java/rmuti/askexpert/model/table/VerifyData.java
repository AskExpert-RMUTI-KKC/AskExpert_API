package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data; 
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "verify_data")
public class VerifyData {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @Column(length = 36,nullable = false,updatable = false, unique = true)
    private String verifyId;

    @Column(name = "verify_from"/*,nullable = false*/)
    private String verifyFrom;//userId

    @Column(name = "verify_expert"/* ,nullable = false */)
    private String verifyExpert;

    @Column(name = "verify_pass_of"/*,nullable = false*/)
    private String verifyPassOf;//userIdAdmin

    @Column(name = "verify_status"/*,nullable = false*/)
    private char verifyStatus;

    // Null
    // Wait
    // Approve
    // Decline

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();

}
