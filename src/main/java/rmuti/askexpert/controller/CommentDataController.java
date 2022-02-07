package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.repo.UserNameRepository;
import rmuti.askexpert.model.table.CommentData;

import java.util.List;

@RestController
@RequestMapping("/Comment")
public class CommentDataController {
    @Autowired
    private UserNameRepository userNameRepository;

    @Autowired
    private TopicDataRepository topicDataRepository;

    @Autowired
    private CommentDataRepository commentDataRepository;

    @PostMapping("/addComment")
    public Object addComment(@RequestBody CommentData commentData) throws BaseException {
        APIResponse res = new APIResponse();
        commentDataRepository.save(commentData);
        return res;
    }

    @PostMapping("/removeComment")
    public Object removeComment() throws BaseException {
        APIResponse res = new APIResponse();
        return res;
    }

    @PostMapping("/findAllComment")
    public Object findAllComment() throws BaseException {
        APIResponse res = new APIResponse();
        List<CommentData> data = commentDataRepository.findAll();
        System.out.println(data);

        return res;
    }
}
