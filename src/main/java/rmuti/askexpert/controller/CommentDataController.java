package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.TopicException;
import rmuti.askexpert.model.mapper.ResCommentMapper;
import rmuti.askexpert.model.repo.*;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.response.ResComment;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.CommentData;
import rmuti.askexpert.model.table.LikeData;
import rmuti.askexpert.model.table.UserInfoData;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentDataController {
    @Autowired
    private CommentDataRepository commentDataRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResCommentMapper resCommentMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private LikeDataRepository likeDataRepository;

    public List<ResComment> commentDisplayBuild(List<ResComment> data) {
        for (ResComment dataindex : data) {
            String userId = dataindex.getCommentUserId();
            Optional<UserInfoData> userInfoData = userInfoRepository.findById(userId);
            if (userInfoData.isPresent()) {
                dataindex.setUserInfoData(resCommentMapper.toResTopicUserInfo(userInfoData.get()));
            }
            String likeContentId = dataindex.getCommentId();
            Optional<LikeData> likeData = likeDataRepository.findByLikeUserIdAndLikeContentId(userId, likeContentId);
            if (likeData.isPresent()) {
                dataindex.setLikeStatus(likeData.get().getLikeStatus());
            }
            List<ResComment> subComment = resCommentMapper.toListResComment(
                    commentDataRepository.findAllByCommentContentIdAndCommentReportStatusAndCommentIsSubComment(
                            dataindex.getCommentId(), 0, 1
                    ));
            if (!subComment.isEmpty()) {

                for (ResComment subDataComment : subComment) {
                    String subUserId = subDataComment.getCommentUserId();
                    Optional<UserInfoData> subUserInfoData = userInfoRepository.findById(subUserId);
                    if (subUserInfoData.isPresent()) {
                        subDataComment.setUserInfoData(resCommentMapper.toResTopicUserInfo(subUserInfoData.get()));
                    }
                    String sublikeContentId = subDataComment.getCommentId();
                    Optional<LikeData> sublikeData = likeDataRepository.findByLikeUserIdAndLikeContentId(userId, sublikeContentId);
                    if (sublikeData.isPresent()) {
                        subDataComment.setLikeStatus(sublikeData.get().getLikeStatus());
                    }
                }
                dataindex.setSubComment(subComment);
            }
        }
        return data;
    }

    @PostMapping("/add")
    public Object addComment(
            @RequestBody CommentData commentData,
            @RequestHeader String Authorization)
            throws BaseException {
        APIResponse res = new APIResponse();
        //String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        commentData.setCommentReportStatus(0);
        commentData.setCommentDonateCount(0);
        commentData.setCommentLikeCount(0);
        commentData.setCommentReport('N');
        commentData.setCommentReportStatus(0);
        commentData.setCommentUserId(tokenService.userId());
        commentDataRepository.save(commentData);
        res.setData(commentData);
        return res;
    }

    @PostMapping("/remove")
    public Object removeComment(
            @RequestParam String commentIdRemove,
            @RequestHeader String Authorization)
            throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<CommentData> opt = commentDataRepository.findByCommentIdAndCommentUserId(
                commentIdRemove,
                userId);
        if (userId.equals(opt.get().getCommentUserId())) {
            commentDataRepository.deleteById(commentIdRemove);
        } else {
            throw TopicException.notyourtopic();
        }
        res.setData(opt);
        return res;
    }

    @PostMapping("/findAll")
    public Object findAllComment() throws BaseException {
        APIResponse res = new APIResponse();
        List<ResComment> data = resCommentMapper.toListResComment(
                commentDataRepository.findAll());
        data = commentDisplayBuild(data);
        res.setData(data);
        return res;
    }

    @PostMapping("/findByCommentId")
    public Object findByTopicId(@RequestBody String CommentId) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<CommentData> commentData = commentDataRepository
                .findByCommentIdAndCommentReport(
                        CommentId,
                        'N'
        );
        if (commentData.isPresent()) {
            ResComment data = resCommentMapper.toResComment(commentData.get());
            res.setData(data);
        }
        return res;
    }

    @PostMapping("/findByContentId") // topic
    public Object findByContentId(@RequestBody String conetentId) throws BaseException{
        APIResponse res = new APIResponse();
        List<ResComment> data = resCommentMapper.toListResComment(
                commentDataRepository
                        .findByCommentContentIdAndCommentReportStatus(
                            conetentId,
                            0
                        )
        );
        return res;
    }
    @PostMapping("/findMyComment")
    public Object findMyComment(@RequestHeader String Authorization) {
        APIResponse res = new APIResponse();
        List<ResComment> data = resCommentMapper.toListResComment(
                commentDataRepository
                        .findByCommentUserIdAndCommentReportStatus(
                                tokenService.userId(),
                                '0'
                        )
        );
        data = commentDisplayBuild(data);
        res.setData(data);
        return res;
    }
}
