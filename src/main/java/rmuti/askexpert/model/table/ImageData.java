package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "image_data")
public class ImageData {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @Column(length = 36,nullable = false,updatable = false, unique = true)
    private String imgId;

    @Column(name = "img_contentId",nullable = false, updatable = false)
    private String imgContentId;

    @Column(name = "img_path",nullable = false, updatable = false)
    private String imgPath;

    @Column(name = "img_name",nullable = false, updatable = false)
    private String imgName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/* ,nullable = false */)
    private Date createdDateForOrder = new Date();

    private Date createdDate = new Date();
}
