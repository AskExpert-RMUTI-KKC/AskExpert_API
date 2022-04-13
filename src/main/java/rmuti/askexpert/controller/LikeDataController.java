package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.LikeDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.CommentData;
import rmuti.askexpert.model.table.LikeData;
import rmuti.askexpert.model.table.TopicData;

import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeDataController {
    @Autowired
    LikeDataRepository likeDataRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    TopicDataRepository topicDataRepository;

    @Autowired
    CommentDataRepository commentDataRepository;

    @PostMapping("/setStatus")
    public Object setStatus(@RequestBody LikeData likeData, @RequestHeader String Authorization) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<LikeData> opt = likeDataRepository.findByLikeUserIdAndLikeContentId(userId,
                likeData.getLikeContentId());
        // update
        if (opt.isPresent()) {
            opt.get().setLikeStatus(likeData.getLikeStatus());
            likeDataRepository.save(opt.get());
        }
        // create
        else {
            likeData.setLikeUserId(userId);
            likeDataRepository.save(likeData);
        }

        // updateLike()
        Optional<TopicData> topicData = topicDataRepository.findById(likeData.getLikeContentId());
        Optional<CommentData> commentData = commentDataRepository.findById(likeData.getLikeContentId());
        if (topicData.isPresent()) {
            if (likeData.getLikeStatus() == 1) {
                topicData.get().setTopicLikeCount(topicData.get().getTopicLikeCount() + 1);
            } else {
                topicData.get().setTopicLikeCount(topicData.get().getTopicLikeCount() - 1);
            }
            topicDataRepository.save(topicData.get());
        }
        else if(commentData.isPresent())
        {
            if (likeData.getLikeStatus() == 1) {
                commentData.get().setCommentLikeCount(commentData.get().getCommentLikeCount() + 1);
            } else {
                commentData.get().setCommentLikeCount(commentData.get().getCommentLikeCount() - 1);
            }
            commentDataRepository.save(commentData.get());
        }
        res.setData(likeData);
        return res;
    }

    @PostMapping("/getStatus")
    public Object getStatus(@RequestParam String contentId, @RequestHeader String Authorization) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        System.out.printf("userId : " + userId);
        Optional<LikeData> opt = likeDataRepository.findByLikeUserIdAndLikeContentId(userId, contentId);
        res.setData(opt);
        return res;
    }

    @PostMapping("/count")
    public Object count(@RequestParam boolean status) throws BaseException {
        APIResponse res = new APIResponse();
        long count = 0;
        // if(status){
        // count = likeDataRepository.countAllByStatusIsTrue();
        // }else {
        // count = likeDataRepository.countAllByContentIsFalse();
        // }
        res.setData(count);
        return res;
    }

}
