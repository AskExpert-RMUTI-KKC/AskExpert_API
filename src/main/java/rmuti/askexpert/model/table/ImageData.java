package rmuti.askexpert.model.table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@Table(name = "image_data")
public class ImageData {
    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @Column(length = 36,nullable = false,updatable = false)
    private String imgId;

    @Column(name = "img_path"/*,nullable = false*/)
    private String imgPath;

    @Column(name = "img_name"/*,nullable = false*/)
    private String imgName;
}
