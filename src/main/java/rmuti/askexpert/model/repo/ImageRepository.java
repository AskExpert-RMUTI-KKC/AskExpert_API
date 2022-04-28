package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.ImageData;

import java.util.List;

public interface ImageRepository  extends JpaRepository<ImageData,String> {
    List<ImageData> findByImgContentId(String id);
}
