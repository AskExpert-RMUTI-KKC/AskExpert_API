package rmuti.askexpert.model.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "expert_group_list")
public class ExpertGroupListData {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "expert_group_id", length = 36, nullable = false, updatable = false, unique = true)
    private String expertGroupId;

    @Column(name = "expert_path",nullable = false)
    private String expertPath;

    @Column(name = "expert_status",nullable = false)
    private int expertStatus;
    //0 ON
    //1 OFF

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_order"/* ,nullable = false */)
    private Date createdDateForOrder = new Date();

    private Date createdDate = new Date();
    
}
