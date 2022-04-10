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
@Table(name = "tran_data")
public class TransactionData {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @Column(length = 36,nullable = false,updatable = false)
    private String tranId;

    @Column(name = "tran_from"/*,nullable = false*/)
    private String tranFrom;

    @Column(name = "tran_receive"/*,nullable = false*/)
    private String tranReceive;

    @Column(name = "tran_amount"/*,nullable = false*/)
    private String tranAmount;

    @Column(name = "tran_status"/*,nullable = false*/)
    private String tranStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getTranFrom() {
        return tranFrom;
    }

    public void setTranFrom(String tranFrom) {
        this.tranFrom = tranFrom;
    }

    public String getTranReceive() {
        return tranReceive;
    }

    public void setTranReceive(String tranReceive) {
        this.tranReceive = tranReceive;
    }

    public String getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(String tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
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
