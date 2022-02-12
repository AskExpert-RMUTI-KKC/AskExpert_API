package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.TopicException;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.repo.UserNameRepository;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.CommentData;
import rmuti.askexpert.model.table.TopicData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentDataController {
    @Autowired
    private UserNameRepository userNameRepository;

    @Autowired
    private TopicDataRepository topicDataRepository;

    @Autowired
    private CommentDataRepository commentDataRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public Object addComment(@RequestBody CommentData commentData, @RequestHeader String Authorization) throws BaseException {
        APIResponse res = new APIResponse();
        System.out.printf("userid : " + tokenService.userId());
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        commentData.setCommentCreateDate(timeStamp);
        commentData.setCommentOwnerId(tokenService.userId());
        commentDataRepository.save(commentData);
        res.setData(commentData);
        return res;
    }

    @PostMapping("/remove")
    public Object removeComment(@RequestParam String commentIdRemove, @RequestHeader String Authorization) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<CommentData> opt = commentDataRepository.findByCommentIdAndCommentOwnerId(commentIdRemove, userId);
        System.out.println("topicIdRemove : " + commentIdRemove);
        System.out.println("UserId : " + userId);
        System.out.println("OwnerId : " + opt.get().getCommentOwnerId());
        if (userId.equals(opt.get().getCommentOwnerId())) {
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
        List<CommentData> data = commentDataRepository.findAll();
        System.out.println(data);
        res.setData(data);
        return res;
    }
    @PostMapping("/findMyComment")
    public Object findMyComment(@RequestHeader String Authorization) {
        APIResponse res = new APIResponse();
        System.out.println("userID : " + tokenService.userId());
        List<CommentData> data = commentDataRepository.findAllByCommentOwnerIdOrderByCommentCreateDate(tokenService.userId());
        res.setData(data);
        return res;
    }
}
