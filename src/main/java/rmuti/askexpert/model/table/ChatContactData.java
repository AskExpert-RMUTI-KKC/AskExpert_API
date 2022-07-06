package rmuti.askexpert.model.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "chat_contact_data")
public class ChatContactData {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "chat_contact_id", length = 36, nullable = false, updatable = false, unique = true)
    private String chatContactId;

    @Column(name = "tran_tx" ,nullable = false )
    private String chatTx;

    @Column(name = "tran_rx",nullable = false )
    private String chatRx;

    @Column(name = "chat_tx_read_status",nullable = false )
    private boolean chatTxReadStatus;

    @Column(name = "chat_rx_read_status",nullable = false )
    private boolean chatRxReadStatus;

    private Date createdDate = new Date();
}
