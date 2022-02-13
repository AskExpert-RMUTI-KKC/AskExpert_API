package rmuti.askexpert.model.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "like_data")
public class LikeData {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid2")
    @Column(name = "like_id",length = 36,nullable = false,updatable = false)
    private String likeId;

    @Column(name = "like_contentId",updatable = false,nullable = false)
    private String contentId;

    @Column(name = "like_ownerId",updatable = false,nullable = false)
    private String ownerId;

    @Column(name = "like_status",nullable = false)
    private byte status;

    //TODO: if not like delete rowData? or if use OneToOne will awaly have rowData
    //TODO: ลบเลยถ้าเกิดไม่มีการ like หรือจะ ให้มันมีการนับการ like ตลอดเลยดีนะ

}
