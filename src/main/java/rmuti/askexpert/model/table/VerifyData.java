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
    @Column(length = 36,nullable = false,updatable = false)
    private String verifyId;

    @Column(name = "verify_from"/*,nullable = false*/)
    private String verifyFrom;//userId

    @Column(name = "verify_expert"/*,nullable = false*/)
    private String verifyReceive;

    @Column(name = "verify_pass"/*,nullable = false*/)
    private String verifyPass;

    @Column(name = "verify_status"/*,nullable = false*/)
    private String verifyStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();

}
