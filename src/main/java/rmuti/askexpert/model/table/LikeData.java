package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "like_data")
public class LikeData {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "like_id", length = 36, nullable = false, updatable = false, unique = true)
    private String likeId;

    @Column(name = "like_contentId", updatable = false, nullable = false)
    private String likeContentId;



    @Column(name = "like_UserId", updatable = false, nullable = false)
    private String likeUserId;

    @Column(name = "like_status", nullable = false)
    private byte likeStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/* ,nullable = false */)
    private Date createdDateForOrder = new Date();

    private Date createdDate = new Date();

    // TODO: ลบเลยถ้าเกิดไม่มีการ like หรือจะ ให้มันมีการนับการ like ตลอดเลยดีนะ
}
