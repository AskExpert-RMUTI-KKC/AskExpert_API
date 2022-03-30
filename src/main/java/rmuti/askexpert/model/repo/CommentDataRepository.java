package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.CommentData;

import java.util.List;
import java.util.Optional;

public interface CommentDataRepository extends JpaRepository<CommentData, String> {
    //List<CommentData> findAll();
    Optional<CommentData> findByCommentIdAndCommentOwnerId(String commentId,String OwnerId);
    List<CommentData> findAllByCommentOwnerIdOrderByCreatedDateForOrder(String Id);
    List<CommentData> findByCommentTopicOwnerId(String Id);
}
