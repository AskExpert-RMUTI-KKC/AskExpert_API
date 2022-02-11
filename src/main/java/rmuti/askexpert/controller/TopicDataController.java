package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.TopicException;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.repo.UserNameRepository;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.TopicData;
import rmuti.askexpert.model.table.UserName;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicDataController {
    @Autowired
    private UserNameRepository userNameRepository;

    @Autowired
    private TopicDataRepository topicDataRepository;

    @Autowired
    private CommentDataRepository commentDataRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public Object addTopic(@RequestBody TopicData topicData){
        APIResponse res = new APIResponse();
        System.out.printf("userid : "+ tokenService.userId());
        topicData.setTopicOwnerId(tokenService.userId());
        topicDataRepository.save(topicData);
        res.setData(topicData);
        return res;
    }

    @PostMapping("/remove")
    public Object removeTopic(@RequestParam String topicIdRemove,@RequestHeader String Authorization) throws Exception{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<TopicData> opt = topicDataRepository.findByTopicId(topicIdRemove);
        System.out.println("topicIdRemove : "+topicIdRemove);
        System.out.println("userId : " + userId);
        System.out.println("OwnerId : " + opt.get().getTopicOwnerId());
        if(userId.equals(opt.get().getTopicOwnerId())){

            topicDataRepository.deleteById(topicIdRemove);
        }
        else
        {
            throw TopicException.notyourtopic();
        }
        res.setData(opt);
        return res;
    }

    @PostMapping("/findall")
    public Object findAllTopic(){
        APIResponse res = new APIResponse();
        List<TopicData> data = topicDataRepository.findAll();
        res.setData(data);
        return res;
    }
}
