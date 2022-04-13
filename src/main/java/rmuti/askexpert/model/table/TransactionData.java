package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tran_data")
public class TransactionData {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String tranId;

    @Column(name = "tran_tx"/* ,nullable = false */)
    private String tranTx;

    @Column(name = "tran_rx"/* ,nullable = false */)
    private String tranRx;

    @Column(name = "tran_amount"/* ,nullable = false */)
    private int tranAmount;

    @Column(name = "tran_contentId"/* ,nullable = false */)
    private String tranContentId;

    @Column(name = "tran_status"/* ,nullable = false */)
    private String tranStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/* ,nullable = false */)
    private Date createdDateForOrder = new Date();

    private Date createdDate = new Date();
}
