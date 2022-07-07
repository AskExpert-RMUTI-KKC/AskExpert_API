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

    @Column(name = "chat_tx" ,nullable = false )
    private String chatTx;

    @Column(name = "chat_rx",nullable = false )
    private String chatRx;

    @Column(name = "chat_tx_read_status",nullable = false )
    private boolean chatTxReadStatus;

    @Column(name = "chat_rx_read_status",nullable = false )
    private boolean chatRxReadStatus;

    @Column(name = "chat_tx_uread_count_status",nullable = false )
    private int chatTxUnReadCount;

    @Column(name = "chat_rx_uread_count_status",nullable = false )
    private int chatRxUnReadCount;

    private Date createdDate = new Date();
}
