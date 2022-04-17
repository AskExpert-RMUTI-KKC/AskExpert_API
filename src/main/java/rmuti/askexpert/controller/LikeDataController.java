package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.LikeDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.CommentData;
import rmuti.askexpert.model.table.LikeData;
import rmuti.askexpert.model.table.TopicData;

import java.util.ArrayList;
import java.util.List;
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

    //add-remove
    @PostMapping("/setStatus")
    public Object setStatus(@RequestBody LikeData likeData, @RequestHeader String Authorization) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();

        //find
        Optional<LikeData> opt = likeDataRepository.findByLikeUserIdAndLikeContentId(
                userId,
                likeData.getLikeContentId()
        );

        // update
        if (opt.isPresent()) {
            if(likeData.getLikeStatus() == 0){
                likeDataRepository.delete(opt.get());
            }
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
            if (likeData.getLikeStatus() == 1 && opt.isEmpty()) {
                topicData.get().setTopicLikeCount(topicData.get().getTopicLikeCount() + 1);
            } else if(likeData.getLikeStatus() == 0 && opt.isPresent()) {
                topicData.get().setTopicLikeCount(topicData.get().getTopicLikeCount() - 1);
            }
            topicDataRepository.save(topicData.get());
        }
        else if(commentData.isPresent())
        {
            if (likeData.getLikeStatus() == 1 && opt.isEmpty()) {
                commentData.get().setCommentLikeCount(commentData.get().getCommentLikeCount() + 1);
            } else if (likeData.getLikeStatus() == 0 && opt.isPresent()) {
                commentData.get().setCommentLikeCount(commentData.get().getCommentLikeCount() - 1);
            }
            commentDataRepository.save(commentData.get());
        }
        res.setData(likeData);
        return res;
    }

    @PostMapping("/getStatus")
    public Object getStatus(
            @RequestParam String contentId,
            @RequestHeader String Authorization)
            throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<LikeData> opt = likeDataRepository.findByLikeUserIdAndLikeContentId(userId, contentId);
        res.setData(opt);
        return res;
    }

    @PostMapping("/findMyLike")
    public Object findMyLike(@RequestBody char contentType)throws BaseException{
        APIResponse res = new APIResponse();
        List<LikeData> likeData = likeDataRepository.findByLikeUserId(tokenService.userId());
        if(contentType == 'T'){
            List<Optional<TopicData>> topicDataList = new ArrayList<>();
            for(LikeData data:likeData){
                topicDataList.add(topicDataRepository.findById(data.getLikeContentId()));
            }
            topicDataList.removeIf(null);
            res.setData(topicDataList);
        }
        if(contentType == 'C'){
            List<Optional<CommentData>> commentDataList = new ArrayList<>();
            for(LikeData data:likeData){
                commentDataList.add(commentDataRepository.findById(data.getLikeContentId()));
            }
            commentDataList.removeIf(null);
            res.setData(commentDataList);
        }
        //res.setData(likeData);
        return res;
    }

}
