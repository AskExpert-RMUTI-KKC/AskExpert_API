package rmuti.askexpert.model.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "chat_mes_data")
public class ChatMesData {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "chat_mes_id", length = 36, nullable = false, updatable = false, unique = true)
    private String chatMesId;


    @Column(name = "chat_contact_id" ,nullable = false )
    private String chatContactId;

    @Column(name = "chat_mes_Tx",nullable = false )
    private String chatTx;

    @Column(name = "chat_Mes",nullable = false,length = 4096)
    private String chatMes;

    private Date createdDate = new Date();
}
