package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
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

    public String getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(String verifyId) {
        this.verifyId = verifyId;
    }

    public String getVerifyFrom() {
        return verifyFrom;
    }

    public void setVerifyFrom(String verifyFrom) {
        this.verifyFrom = verifyFrom;
    }

    public String getVerifyReceive() {
        return verifyReceive;
    }

    public void setVerifyReceive(String verifyReceive) {
        this.verifyReceive = verifyReceive;
    }

    public String getVerifyPass() {
        return verifyPass;
    }

    public void setVerifyPass(String verifyPass) {
        this.verifyPass = verifyPass;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
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
}
