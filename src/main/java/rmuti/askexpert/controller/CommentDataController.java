package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmuti.askexpert.model.services.CommentDataRepository;
import rmuti.askexpert.model.services.TopicDataRepository;
import rmuti.askexpert.model.services.UserNameRepository;
import rmuti.askexpert.model.table.CommentData;
import rmuti.askexpert.model.table.TopicData;

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
    public Object addComment(@RequestBody CommentData commentData){
        APIResponse res = new APIResponse();
        commentDataRepository.save(commentData);
        return res;
    }

    @PostMapping("/removeComment")
    public Object removeComment(){
        APIResponse res = new APIResponse();
        return res;
    }

    @PostMapping("/findAllComment")
    public Object findAllComment(){
        APIResponse res = new APIResponse();
        List<CommentData> data = commentDataRepository.findAll();
        System.out.println(data);

        return res;
    }
}
