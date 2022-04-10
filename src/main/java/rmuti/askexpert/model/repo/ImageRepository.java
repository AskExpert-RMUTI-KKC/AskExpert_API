package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.ImageData;

public interface ImageRepository  extends JpaRepository<ImageData,String> {
}
