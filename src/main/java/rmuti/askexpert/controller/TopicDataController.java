package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmuti.askexpert.model.services.CommentDataRepository;
import rmuti.askexpert.model.services.TopicDataRepository;
import rmuti.askexpert.model.services.UserNameRepository;
import rmuti.askexpert.model.table.TopicData;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicDataController {
    @Autowired
    private UserNameRepository userNameRepository;

    @Autowired
    private TopicDataRepository topicDataRepository;

    @Autowired
    private CommentDataRepository commentDataRepository;

    @PostMapping("/addTopic")
    public Object addTopic(@RequestBody TopicData topicData){
        APIResponse res = new APIResponse();
        topicDataRepository.save(topicData);
        return res;
    }

    @PostMapping("/removeTopic")
    public Object removeTopic(){
        APIResponse res = new APIResponse();
        return res;
    }

    @PostMapping("/findAllTopic")
    public Object findAllTopic(){
        APIResponse res = new APIResponse();
        List<TopicData> data = topicDataRepository.findAll();
        System.out.println(data);
        res.setData(data);
        return res;
    }
}
