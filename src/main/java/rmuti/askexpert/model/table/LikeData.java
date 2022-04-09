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
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid2")
    @Column(name = "like_id",length = 36,nullable = false,updatable = false)
    private String likeId;

    @Column(name = "like_contentId",updatable = false,nullable = false)
    private String likeContentId;

    @Column(name = "like_ownerId",updatable = false,nullable = false)
    private String likeOwnerId;

    @Column(name = "like_status",nullable = false)
    private byte likeStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column (name = "create_order"/*,nullable = false*/)
    private Date createdDateForOrder= new Date();;

    private Date createdDate = new Date();

    //TODO: ลบเลยถ้าเกิดไม่มีการ like หรือจะ ให้มันมีการนับการ like ตลอดเลยดีนะ


    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }

    public String getLikeContentId() {
        return likeContentId;
    }

    public void setLikeContentId(String likeContentId) {
        this.likeContentId = likeContentId;
    }

    public String getLikeOwnerId() {
        return likeOwnerId;
    }

    public void setLikeOwnerId(String likeOwnerId) {
        this.likeOwnerId = likeOwnerId;
    }

    public byte getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(byte likeStatus) {
        this.likeStatus = likeStatus;
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
