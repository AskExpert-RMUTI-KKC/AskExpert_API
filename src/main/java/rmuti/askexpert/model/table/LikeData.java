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

    //content_id
    @Column(name = "like_content")
    private String content;

    @Column(name = "like_status",nullable = true)
    private boolean status;

    @Column(name = "like_OwnerId")
    private String likeOwner;
}
