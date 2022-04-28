package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "topic_group_list")
public class TopicGroupListData {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "topic_group_id", length = 36, nullable = false, updatable = false, unique = true)
    private String topicGroupId;

    @Column(name = "topic_group_path", nullable = false)
    private String topicGroupPath;

    @Column(name = "topic_group_status", nullable = false)
    private int topicGroupStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/* ,nullable = false */)
    private Date createdDateForOrder = new Date();

    private Date createdDate = new Date();
}
