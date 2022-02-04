package rmuti.askexpert.model.services;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.CommentData;

import java.util.List;

public interface CommentDataRepository extends JpaRepository<CommentData, Integer> {
    List<CommentData> FindAll();
}
