package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.CommentData;

import java.util.List;
import java.util.Optional;

public interface CommentDataRepository extends JpaRepository<CommentData, String> {
    // List<CommentData> findAll();
    Optional<CommentData> findByCommentIdAndCommentUserId(String commentId, String UserId);

    List<CommentData> findByCommentReport(char report);

    List<CommentData> findAllByCommentUserIdOrderByCreatedDateForOrderDesc(String Id);

    Optional<CommentData> findByCommentContentId(String Id);

    List<CommentData> findAllByCommentReportStatusAndCommentIsSubComment(int report, int SubComent);

    List<CommentData> findAllByCommentContentIdAndCommentReportStatusAndCommentIsSubComment(String connentId, int report, int subComent);

    Optional<CommentData> findByCommentIdAndCommentReportStatus(
            String commentId,
           int report
    );

    List<CommentData> findByCommentUserIdAndCommentReportStatus(
            String userId,
            int reportStatus
    );
    List<CommentData> findByCommentContentIdAndCommentReportStatus(
            String contentId,
            int reportStatus
    );
}
