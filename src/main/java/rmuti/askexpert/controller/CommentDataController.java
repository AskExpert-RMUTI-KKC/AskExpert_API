package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.repo.UserNameRepository;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.CommentData;

import java.util.List;

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
    public Object addComment(@RequestBody CommentData commentData,@RequestHeader String Authorization) throws BaseException {
        APIResponse res = new APIResponse();
        commentData.setCommentId(tokenService.userId());
        System.out.printf("userid : "+ tokenService.userId());
        commentDataRepository.save(commentData);
        res.setData(commentData);
        return res;
    }

    @PostMapping("/remove")
    public Object removeComment(@RequestParam String commentIdRemove,@RequestHeader String Authorization) throws BaseException {
        APIResponse res = new APIResponse();
        return res;
    }

    @PostMapping("/findAll")
    public Object findAllComment() throws BaseException {
        APIResponse res = new APIResponse();
        List<CommentData> data = commentDataRepository.findAll();
        System.out.println(data);

        return res;
    }
}
